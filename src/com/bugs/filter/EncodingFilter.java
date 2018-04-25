package com.bugs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class EncodingFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//把ServletRequest转化成我们处理的HttpServletRequest
		  HttpServletRequest request1=(HttpServletRequest)request;
		  HttpServletResponse response1=(HttpServletResponse)response;
		  request1.setCharacterEncoding("utf-8");
		  response1.setContentType("text/html;charset=utf-8");
		  //放行(所有的servlet访问都需要先经过EncodingFilter，他处理后放行后servlet才可以执行)
		  System.out.println("this=========================");
		  chain.doFilter(request1, response1);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
