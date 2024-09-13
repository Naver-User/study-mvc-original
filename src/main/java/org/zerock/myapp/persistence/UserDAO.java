package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.zerock.myapp.annotation.DAO;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.util.SharedAttributes;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@DAO

@Log4j2
@ToString
public class UserDAO {	// POJO (Plain Old Java Object) 기반으로 선언
//	@Resource(name = "java:comp/env/jdbc/h2/test")	// XX, Injection
	private DataSource dataSource;
	
	
	// Important: 
	//		The `@PostConstruct` & `@PreDestroy` annotations do work *Only 
	//		in the java *Servlet class.	
	public UserDAO() throws Exception {	// Default Constructor
		 log.trace("<init> () invoked.");
		 
		 // JNDI lookup 을 통해서, 데이터소스 객체에 주소(참조)를 획득
		 Context ctx = new InitialContext();
		 Objects.requireNonNull(ctx);
		 
		 Object obj = ctx.lookup(SharedAttributes.JNDI_PREFIX + SharedAttributes.DATA_SOURCE);
		 
		 if(obj instanceof DataSource dataSource) {
			 this.dataSource = dataSource;
		 } // if
		 
		 log.info("\t+ this.dataSource: {}", this.dataSource);
	} // Default Constructor

	public UserVO selectByUsername(UserDTO dto) throws Exception {
		log.trace("selectByUsername({}) invoked.", dto);
		
		Objects.requireNonNull(dto);
		
		if(dto.getUsername() == null || "".equals(dto.getUsername())) {
			throw new IllegalArgumentException("username is null or empty.");
		} // if
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t+ conn: {}", conn);

		String sql = "SELECT * FROM t_user WHERE username = ? and password = ?";
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getUsername());
		pstmt.setString(2, dto.getPassword());
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {	// if found.
			String username = rs.getString("username");
			String password = rs.getString("password");
			String role = rs.getString("role");
			
			return new UserVO(username, password, role);
		} else {				// if Not found.
			return null;
		} // if-else
	} // selectByUsername

} // end class
