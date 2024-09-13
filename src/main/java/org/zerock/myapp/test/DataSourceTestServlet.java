package org.zerock.myapp.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/* Servlet Spec 4.0.x 에서는, 아래의 패키지에 있는 어노테이션을 사용하면 정상작동안합니다. (***)
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
*/

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@Slf4j

@NoArgsConstructor

@WebServlet("/dataSourceTest")
public class DataSourceTestServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	// name 속성은, JNDI tree 에 바인딩한 데이터소스 자원객체의 이름을
	// 규약(java:comp/env/ 접두사를 붙여야 함)에 맞게 지정해야 합니다. (***)

	@Resource(name = "java:comp/env/jdbc/h2/test")
//	@Resource(name = "java:comp/env/jdbc/log4jdbc/h2/test")
//	@Resource(name = "java:comp/env/jdbc/oracle/xepdb1")
//	@Resource(name = "java:comp/env/jdbc/log4jdbc/oracle/xepdb1")
	private DataSource dataSource;
	
	
	@PostConstruct
	void postConstruct() {	// 전처리(Pre-processing) 메소드
		log.trace("postConstruct() invoked.");
		
		Objects.requireNonNull(this.dataSource);
		log.info("\t+ this.dataSource: {}", this.dataSource);

		try {
			@Cleanup Connection conn = this.dataSource.getConnection();
			log.info("\t+ conn: {}", conn);
		} catch(Exception e) {
			e.printStackTrace();
		} // try-catch
	} // postConstruct
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace("---------------------------------------");
		log.trace("service(req, res) invoked.");
		log.trace("---------------------------------------");
		
		try {
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<h3>DataSourceTest</h3>");
			out.println("<hr>");
			out.flush();
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service



} // end class

