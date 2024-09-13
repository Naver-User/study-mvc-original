package org.zerock.myapp.util;


public interface SharedAttributes {
	
	// 1. Related All Controller URIs with Security. ( Access Control )
	public static final String CREDENTIAL = "_CREDENTIAL_";
	public static final String AUTHENTICATED = "_AUTHENTICATED_";
	public static final String AUTHORIZED = "_AUTHORIZED_";
	public static final String MODEL = "_MODEL_";
	public static final String DO_AUTHENCATE = "_DO_AUTHENCATE_";
	public static final String DO_AUTHORIZE = "_DO_AUTHORIZE_";
	public static final String REQUIRED_ROLES = "_REQUIRED_ROLES_";
	
	// 2. 컨트롤러와 관련된 매핑 URI를 저장한 공용상수 정의
	public static final String SIGNIN_URI 		= "/signIn";
	public static final String SIGNOUT_URI 		= "/signOut";
	public static final String AUTHENTICATE_URI	= "/authenticate";	// 인증수행요청 URI
	public static final String AUTHORIZE_URI	= "/authorize";		// 인가수행요청 URI
	
	public static final String LOGIN_URI 		= "/login";
	public static final String HOME_URI 		= "/home";
	public static final String MEMBER_URI 		= "/member";
	public static final String USER_URI 		= "/user";
	public static final String ADMIN_URI 		= "/admin";
	public static final String FORBIDDEN_URI 	= "/forbidden";
	
	// 3. Related with Shared Resouces.
	public static final String JNDI_PREFIX		= "java:comp/env/";
	
	/*
	public static final String H2_DATA_SOURCE 				= "jdbc/h2/test";
	public static final String H2_LOG4JDBC_DATA_SOURCE 		= "jdbc/log4jdbc/h2/test";
	public static final String ORACLE_DATA_SOURCE 			= "jdbc/oracle/osan";
	public static final String ORACLE_LOG4JDBC_DATA_SOURCE 	= "jdbc/log4jdbc/oracle/osan";
	*/
	
	public static final String DATA_SOURCE		= "jdbc/h2/test";
	
	// 4. ViewResolver 와 관련된 상수 선언
	public static final String VIEW_RESOLVER_URI = "/viewResolver";
	public static final String VIEW_PREFIX = "/WEB-INF/views/";
	public static final String VIEW_SUFFIEX = ".jsp";
	
	public static final String VIEW_NAME = "_VIEW_NAME_";
	
	// 5. ViewResolver가 최종확정한 경로의 JSP파일을 호출하는 View의 매핑URI
	public static final String VIEW_URI = "/view";
	public static final String RESOLVED_VIEW_PATH = "_RESOLVED_VIEW_PATH_";
	

} // end interface
