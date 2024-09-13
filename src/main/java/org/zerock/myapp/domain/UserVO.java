package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;

import org.zerock.myapp.annotation.VO;

import lombok.Value;


@VO

@Value
public class UserVO implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String role;
	
	

} // end class

