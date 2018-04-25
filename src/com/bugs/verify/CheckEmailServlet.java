package com.bugs.verify;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugs.service.UserService;

public class CheckEmailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckEmailServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		//校验邮箱ajax(1.邮箱是否为空, 2.邮箱格式是否正确，,3.邮箱是否已经被注册)
		//1.获取请求参数
		String email = request.getParameter("email");
		System.out.println("==email="+email);
		//2.业务逻辑处理
		String result="";
		//2.1.邮箱是否为空,
		if("".equals(email)||null==email){
			result="邮箱不能为空";
		}else if(!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
			//2.2 邮箱格式是否正确，使用正则表达式
			result="邮箱格式不正确";
		 }else {
			//2.3.邮箱是否已经被注册
			 UserService userService=new UserService();
			 try {
				result = userService.checkEmail(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		//3.处理结果，响应给页面
		response.getWriter().write(result);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
