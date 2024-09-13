package org.zerock.myapp.web.mvc;

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

@WebServlet(SharedAttributes.VIEW_URI)
public class ViewServlet extends HttpServlet {	// View Controller
	private static final long serialVersionUID = 1L;

	
	// ViewResolver 가 최종확정한 JSP파일의 경로대로, JSP를 호출책임!!
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service({}, {}) invoked.", req, res);
		
		try {
			// Step1. ViewResolver 가 공유영역인 Request Scope을 통해 전달한
			//        resolvedViewPath를 획득
			String resolvedViewPath = (String)
				req.getAttribute(SharedAttributes.RESOLVED_VIEW_PATH);
			
			log.info("\t+ resolvedViewPath: {}", resolvedViewPath);
			
			// Step2. resolvedViewPath 에 있는 JSP파일 수행
			RequestDispatcher dispatcher =
					req.getRequestDispatcher(resolvedViewPath);
			dispatcher.forward(req, res);
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
	} // service

} // end class

