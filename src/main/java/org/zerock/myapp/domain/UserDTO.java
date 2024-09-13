package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;

import org.zerock.myapp.annotation.DTO;

import lombok.Data;


@DTO

@Data
public class UserDTO implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	

} // end class

