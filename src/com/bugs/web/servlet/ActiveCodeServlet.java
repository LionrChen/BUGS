package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugs.service.UserService;

public class ActiveCodeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ActiveCodeServlet() {
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

		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		//�õ������룬
		//1.��ȡ�������
		String activeCode = request.getParameter("activeCode");
		//2.����ҵ���߼���������֤��
		 UserService userService=new UserService();
		 try {
			boolean isFlag =  userService.activeCode(activeCode);
			//3.�ַ�ת��
			if (isFlag) {
				//���伤��ɹ�,��ת����¼ҳ��
				request.setAttribute("msg", "����ɹ������¼");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				//���伤��ʧ��
				printWriter.write("���伤��ʧ��,�����¼���");
				System.out.println("���伤��ʧ��,�����¼���");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//���伤��ʧ��
			printWriter.write("���伤��ʧ��,�����¼���");
			System.out.println("���伤��ʧ��,�����¼���");
		}
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
