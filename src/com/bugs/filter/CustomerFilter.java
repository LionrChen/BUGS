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

import com.bugs.domain.Customer;

public class CustomerFilter implements Filter{
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		  HttpServletRequest request1=(HttpServletRequest)request;
		  HttpServletResponse response1=(HttpServletResponse)response;
		 
		  //�жϵ�¼
		  Customer customer = (Customer)request1.getSession().getAttribute("customer");
		  if(customer!=null){
			  //�Ѿ���¼
			  chain.doFilter(request1, response1);
		  }else{
			  //û�е�¼
			  response1.sendRedirect(request1.getContextPath()+"/userLogin.jsp");
		  }
		  
	}

	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
