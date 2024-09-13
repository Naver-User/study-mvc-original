package org.zerock.myapp.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.service.UserService;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/userServiceTest")
public class UserServiceTestServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("---------------------------------------");
		log.trace("service(req, res) invoked.");
		log.trace("---------------------------------------");
		
		try {
			// Step1. 전송파라미터 수집
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			// Step2. 수집된 모든 전송파라미터를 DTO 로 변환
			UserDTO dto = new UserDTO();
			dto.setUsername(username);
			dto.setPassword(password);
			
			log.info("\t+ dto: {}", dto);
			
			// Step3. DTO를 매개변수로 가지는 비지니스 계층의 서비스의 메소드
			//        호출해서, 기대한 결과데이터(=즉, MVC 패턴의 Model(M))가
			//        정확히 발생하는지 확인
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<h3>UserServiceTest</h3>");
			out.println("<hr>");
			
			UserVO vo = this.userService.findByUsername(dto);
			log.info("\t+ vo: {}", vo);
			
			out.println("<p>%s</p>".formatted(vo));
			
			out.flush();
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service



} // end class

