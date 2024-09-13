package org.zerock.myapp.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.persistence.UserDAO;

// 최고한 Apache Tomcat WAS 의 버전이 v10.1.x 이어야 사용가능
//import jakarta.annotation.PostConstruct;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/userDaoTest")
public class UserDAOTestServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		try { this.userDAO = new UserDAO(); } catch(Exception _ignored) {}
		
		Objects.requireNonNull(this.userDAO);
		log.info("\t+ this.userDAO: {}", this.userDAO);
	} // postConstruct

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("---------------------------------------");
		log.trace("service(req, res) invoked.");
		log.trace("---------------------------------------");
		
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			// ------------------
			UserDTO dto = new UserDTO();
			dto.setUsername(username);
			dto.setPassword(password);
			
			log.info("\t+ dto: {}", dto);
			
			// ------------------
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<h3>UserDAOTest</h3>");
			out.println("<hr>");
			
			UserVO vo = this.userDAO.selectByUsername(dto);
			log.info("\t+ vo: {}", vo);
			
			out.println("<p>%s</p>".formatted(vo));
			
			out.flush();
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service



} // end class

