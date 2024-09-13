package org.zerock.myapp.filter;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebFilter(
	dispatcherTypes = {DispatcherType.REQUEST }, 
	urlPatterns = { "/1" })
public class MyFilter2 extends HttpFilter implements Filter {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		// (1) 전처리 코드
		log.info("(1) 여기는 전처리 코드가 수행되는 부분입니다.");
		
		chain.doFilter(request, response);
		
		// (2) 후처리 코드
		log.info("(2) 여기는 후처리 코드가 수행되는 부분입니다.");
	}

}
