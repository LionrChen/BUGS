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
		//У������ajax(1.�����Ƿ�Ϊ��, 2.�����ʽ�Ƿ���ȷ��,3.�����Ƿ��Ѿ���ע��)
		//1.��ȡ�������
		String email = request.getParameter("email");
		System.out.println("==email="+email);
		//2.ҵ���߼�����
		String result="";
		//2.1.�����Ƿ�Ϊ��,
		if("".equals(email)||null==email){
			result="���䲻��Ϊ��";
		}else if(!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
			//2.2 �����ʽ�Ƿ���ȷ��ʹ��������ʽ
			result="�����ʽ����ȷ";
		 }else {
			//2.3.�����Ƿ��Ѿ���ע��
			 UserService userService=new UserService();
			 try {
				result = userService.checkEmail(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		//3.����������Ӧ��ҳ��
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
