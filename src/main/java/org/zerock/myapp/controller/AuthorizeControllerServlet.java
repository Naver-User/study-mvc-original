package org.zerock.myapp.controller;

import java.io.IOException;
import java.io.Serial;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.annotation.Controller;
import org.zerock.myapp.service.AuthService;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller

@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.AUTHORIZE_URI)
public class AuthorizeControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	
	// 인가 = 인증을 기반으로 + 권한이 적절한지를 체크
	// 원리 => (1) 현재 요청을 보내느 브라우저에 할당된 세션을 얻어내고(req.getSession(false))
	//         (2) 얻어낸 세션으로 Session Scope(=금고상자)에서 Credential를 얻음
	// 		      * 중요: Credential 객체안에 필드로, 인증성공한 아이디 + 권한명이 포함됨
	//		   (3) Credential 에 저장되어 있는 권한명을 추출
	//		   (4) (3)에서 얻어낸 권한명과 요청을 보낸 보호된 자원이 필요로 하는 권한들과
	//			   매칭되는지를 확인(if 매칭되면 -> 인가성공, else -> 인가실패)
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace("service({}, {}) invoked.", req, res);
		
		try {
			// Step1. 현재 요청 보낸 브라우저에 할당된 세션 획득
			HttpSession session = req.getSession(false);
			log.info("\t+ session: {}", session);
			
			// Step2. Request Scope에 저장된, 각 보호된 자원이 넘겨준 권한목록을 추출
			@SuppressWarnings("unchecked")
			Set<String> requiredRoles = 
				(Set<String>)req.getAttribute(SharedAttributes.REQUIRED_ROLES);
			log.info("\t+ requiredRoles: {}", requiredRoles);
			
			// Step3. AuthService 의 isAuthrorized 메소드 호출로 처리 위임
			AuthService service = new AuthService();
			boolean isAuthorized = service.isAuthroized(session, requiredRoles);
			log.info("\t+ isAuthorized: {}", isAuthorized);
			
			// Step4. 인가에 성공하면,View Resolver로 포워드 수행
			RequestDispatcher dispatcher;
			
			if(isAuthorized) {	// 인가체크에 성공하면...
				dispatcher = req.getRequestDispatcher(SharedAttributes.VIEW_RESOLVER_URI);				
			} else {			// 인가체크에 실패하면...
				dispatcher = req.getRequestDispatcher(SharedAttributes.FORBIDDEN_URI);
			} // if-else
			
			dispatcher.forward(req, res);
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

