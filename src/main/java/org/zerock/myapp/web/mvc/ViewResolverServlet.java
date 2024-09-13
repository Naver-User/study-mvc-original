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

@WebServlet(SharedAttributes.VIEW_RESOLVER_URI)
public class ViewResolverServlet extends HttpServlet {	// View Controller
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service({}, {}) invoked.", req, res);
		
		try {
			// Step1. Request Scope 에서, 뷰이름을 획득
			String viewName = (String) req.getAttribute(SharedAttributes.VIEW_NAME);
			
			// Step2. 최종 View 경로를 만들어 냅니다.
			//        응답화면을 만들어 낼 책임이 있는 View의 경로를 접두사와 접미사를
			//        이용해서, 최종 view 경로를 만들어 냅니다.
			
			// 최종View경로명 = 접두사 + 전달된뷰이름 + 접미사
			String resolvedViewPath = 
				SharedAttributes.VIEW_PREFIX +
				viewName + 
				SharedAttributes.VIEW_SUFFIEX;
			
			//                      접두사       전달뷰명 접미사
			//                   --------------- ------   -----
			// resolvedViewPath: /WEB-INF/views/ member   .jsp
			log.info("resolvedViewPath: {}", resolvedViewPath);
			
			// Step3. 최종경로를 Request Scope공유영역에 저장하고
			//        View 컴포넌트에게 JSP파일의 실행을 요청
			req.setAttribute(SharedAttributes.RESOLVED_VIEW_PATH, resolvedViewPath);
			
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher(SharedAttributes.VIEW_URI);
			
			dispatcher.forward(req, res);
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
	} // service

} // end class

