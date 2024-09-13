package org.zerock.myapp.view;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.ADMIN_URI)
public class AdminControllerServlet extends HttpServlet {	// View Controller
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// Step1.
			final String viewName = "admin";
			req.setAttribute(SharedAttributes.VIEW_NAME, viewName);			
			
			// Step2. 인증체크 컨트롤러로 포워드 수행
			boolean doAuthencate = true;	// 인증수행하라!!
			boolean doAuthorize = true;		// 인가수행하라!!
			
			req.setAttribute(SharedAttributes.DO_AUTHENCATE, doAuthencate);
			req.setAttribute(SharedAttributes.DO_AUTHORIZE, doAuthorize);
			
			// Step3. 나를 호출하는데 필요한 권한정보도 설정해서 넘겨줍니다.
			Set<String> requiredRoles = Set.<String>of("ROLE_ADMIN");			
			req.setAttribute(SharedAttributes.REQUIRED_ROLES, requiredRoles);
			
			// Step4.
			RequestDispatcher dispatcher;
			
			if(doAuthencate) {	// 1. 인증체크가 필요하면...
				dispatcher = req.getRequestDispatcher(SharedAttributes.AUTHENTICATE_URI);				
			} else {	// 2. 인증체크마저도 필요없다면...
				dispatcher = req.getRequestDispatcher(SharedAttributes.VIEW_RESOLVER_URI);
			} // if-else
			
			dispatcher.forward(req, res);
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
	} // service

} // end class

