package org.zerock.myapp.controller;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.domain.Credential;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.service.AuthService;
import org.zerock.myapp.service.UserService;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(SharedAttributes.SIGNIN_URI)
public class SignInControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	// MVC 패턴에서, C(Controller)가 무엇인지, 무슨 역할을 하는 건지,
	// 무슨 로직을 수행해야 하는지를 잘 보시기 바랍니다.
	// 잊지마세요!! 지금 우리가 구현하는 서비스는 일단 "인증"(=로그인)입니다!!!!
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace(">>> service(req, res) invoked.");
		
		// Step1. request 객체로부터 매핑정보(?????)를 추출합니다.
		HttpServletMapping mapping = req.getHttpServletMapping();
		log.info("\t+ matchValue: {}, pattern: {}, servletName: {}, mappingMatch: {}", 
				mapping.getMatchValue(), 
				mapping.getPattern(), 
				mapping.getServletName(), 
				mapping.getMappingMatch());
		
		// Step2. 여기서부터가 진짜 컨트롤러가 수행하는 로직입니다.
		try {
			// 2-1. 비지니스 계층에서 로그인 구현에 필요한 서비스 객체 생성
			UserService userService = new UserService();
			AuthService authService = new AuthService();	// 이건뭐지???
			
			// 2-2. 현재 로그인 요청을 보낸 웹브라우저에 대한 HttpSession 객체를 획득
			//      (주의사항: 이 싯점에는, 요청을 보낸 웹브라우저에 아직 세션ID도 없고
			//        금고상자도 없다면, 새롭게 세션ID와 금고상자를 만들어주어야 할까?
			//        아니면, 없으면 없는대로 있으면 있는대로 세션ID와 금고상자를
			//		  사용해야 할까!?..즉, request.getSession(boolean) 메소드임.
			HttpSession newSession = authService.createSession(req);
			
			// 2-3. 로그인(=인증)처리에 필요한 모든 전송파라미터 수집
			final String username = req.getParameter("username");
			final String password = req.getParameter("password");
			
			// 2-4. 수집된 모든 전송파라미터를 DTO로 저장
			UserDTO dto = new UserDTO();			
			dto.setUsername(username);
			dto.setPassword(password);			
			log.info("\t+ dto: {}", dto);
			
			// 2-5. DTO로 전송된 정보로 사용자를 찾아냈다면,
			//      (1) 인증(=로그인)에 성공한 것이고
			//      (2) 인증된 사용자에 대한 신원정보(=신분증)=> Credential을 생성
			//      (3) 생성한 Credential(=신분증)객체를, Session Scope(=각 브라우저마다
			//          생성되는 금고상자)에 저장합니다.
			
		  // Model	
		  //------
			UserVO vo = userService.findByUsername(dto);	// 사용자정보조회 수행	
			
			Credential credential = authService.createCredential(vo);
			newSession.setAttribute(SharedAttributes.CREDENTIAL, credential);

			// Step3. 인증성공여부에 따라, 당연히 가야할 화면으로 이동(=리다이렉션)
			if(vo == null) {	// 인증에 실패했다면...
				// (1) Re-direct to the "SharedAttributes.LOGIN_URI".
				authService.redirectTo(res, SharedAttributes.LOGIN_URI);
			} else {			// 인증에 성공했다면....
				// (2) Re-direct to the "SharedAttributes.MEMBER_URI".
				authService.redirectTo(res, SharedAttributes.MEMBER_URI);
			} // if-else
						
			log.info("*** Done ***");
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class

