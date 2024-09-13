package org.zerock.myapp.controller;

import java.io.IOException;
import java.io.Serial;

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


// 접근통제를 위한 인증체크/인가체크 컨트롤러는, 브라우저에서 직접요청을 받지 않고
// 다른 컨틀로러에서 forward로 요청을 받아서 수행하는 컴포넌트

// 인증체크 수행
@WebServlet(SharedAttributes.AUTHENTICATE_URI)
public class AuthenticateControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	
	// 인증체크방법?
	// 현재 요청을 보낸 웹브라우저가 로그인된 상태인가!???를 확인
	// 방법: 세션금고상자(=Session Scope)안에, 로그인처리시에 만들어서 넣은
	//		 신분증(=Credential)이 있느냐!? 없느냐!?를 보는 것이다!!!
	// 알고리즘:
	//	Step1. 현재 요청을 보낸 웹브라우저의 세션을 얻어냄(req.getSession(false))
	//  Step2-1. Step1 에서 얻어낸 세션이 null 값이라면, 인증실패 (결론)
	//  Step2-2. Step1 에서 얻어낸 세션이 유효하다면(=널이 아니라면),
	//		     Session Scope(금고상자)안에 Credential 존재여부 확인
 	//           if 신분증 존재 =>   인증성공(결론)
	//			 if 신분증 미존재 => 인증실패(결론)
	//  Step3-1. if 인증실패 => 로그인 화면으로 강제 리다이렉션
	//  Step3-2. if 인증성공 & 요청URI /member => 인증실패(로그인), 인증성공(doXXX값에 따라 이동) 
	//						   요청URI /user   => 상동
	//						   요청URI /admin  => 상동.
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace("service({}, {}) invoked.", req, res);
		
		try {
			AuthService service = new AuthService();
			
			// Step1.
			HttpSession session = req.getSession(false);
			
			// Step2. Model로 boolean 값을 서비스에서 반환받음
			boolean isAuthencated = service.isAuthenticated(session);
			log.trace("\t+ isAuthencated: {}", isAuthencated);
			
//			req.setAttribute(SharedAttributes.MODEL, isAuthencated);
			
			// Step3. 인증체크 결과에 따라, 어디로 흐름을 전개시킬지를 구현
			if(!isAuthencated) { // 인증실패
				service.redirectTo(res, SharedAttributes.LOGIN_URI);
				return;
			} else {	// 인증성공
				// 보호된 자원에 대한 요청이, 어디까지(인증/인가)를 요구하는지
				// 에 따라, 그 다음 흐름이 결정
				Boolean doAuthorize = 
					(Boolean) req.getAttribute(SharedAttributes.DO_AUTHORIZE);
				
				RequestDispatcher dispatcher;
				
				if(doAuthorize)	{	// 인가체크까지 수행하라!!!
					dispatcher = req.getRequestDispatcher(SharedAttributes.AUTHORIZE_URI);
				} else {	// 인가체크는 수행하지 마라!!
					dispatcher = req.getRequestDispatcher(SharedAttributes.VIEW_RESOLVER_URI);;
				} // if-else
				
				dispatcher.forward(req, res);
			} // if-else
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

