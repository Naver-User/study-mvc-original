package org.zerock.myapp.view;

import java.io.IOException;

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

@WebServlet(SharedAttributes.HOME_URI)
public class HomeControllerServlet extends HttpServlet {	// View Controller
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// Step1. 비지니스 로직 수행
			Object model = null;
			String viewName = "login";			// 결정된 뷰이름
			
			// Step2. Request Scope 공유영역에 모델과 뷰이름 저장
			req.setAttribute(SharedAttributes.VIEW_NAME, viewName);	
			req.setAttribute(SharedAttributes.MODEL, model);		
			
			// Step3. 인증체크 컨트롤러로 포워드 수행
			boolean doAuthencate = false;	// 인증수행하라!!
			boolean doAuthorize = false;	// 인가는 수행하지 마라!!!
			
			req.setAttribute(SharedAttributes.DO_AUTHENCATE, doAuthencate);
			req.setAttribute(SharedAttributes.DO_AUTHORIZE, doAuthorize);
			
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

