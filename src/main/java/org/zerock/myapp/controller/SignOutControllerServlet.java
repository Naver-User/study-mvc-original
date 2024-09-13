package org.zerock.myapp.controller;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.service.AuthService;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.SIGNOUT_URI)
public class SignOutControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	// MVC 패턴에서, C(Controller)가 무엇인지, 무슨 역할을 하는 건지,
	// 무슨 로직을 수행해야 하는지를 잘 보시기 바랍니다.
	// 잊지마세요!! 지금 우리가 구현하는 서비스는 일단 "인증"(=로그인)입니다!!!!
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try { // 로그아웃(Sign Out) 처리 수행
			
			// Step1. 파괴시킬 세션객체를 얻어냅니다.
			//        가정: 로그아웃 요청은, 이미 로그인된 상태(=즉, 인증된 상태 => 세션이 있는 상태)
			//              에서만 보낼 수 있는 겁니다.
			HttpSession session = req.getSession(false);
			
			// Step2. 세션파괴
			AuthService authService = new AuthService();
			authService.destroySession(session);
			
			// Step3. 그 다음화면으로 이동(리다이렉션)
			authService.redirectTo(res, SharedAttributes.LOGIN_URI);
			
			log.info("Done.");
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

