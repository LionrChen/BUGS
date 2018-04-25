package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bugs.domain.User;
import com.bugs.exception.UserException;
import com.bugs.service.UserService;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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

		Map<String, String[]> map = request.getParameterMap(); 
		User user =new User();
		try {
			//��װ���������ֵ��User������
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.����ҵ���߼�
		  UserService userService =new UserService();
		  try {
			 //��¼�ɹ�
			 User user2 = userService.LoginUser(user);
			//��¼�ɹ�����ת��������׽���
			//��¼״̬��
			request.getSession().setAttribute("user", user2);
			//request.setAttribute("user", user);���÷�Χ�Ƚ�С
			//�ض��򣺵�ַ�������仯��������2��
			response.sendRedirect("home/home.jsp");
			//����ת������ַ�����䣬������һ��
			//request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (SQLException e) {
			//��¼ʧ��,ˢ�µ�¼ҳ������û���¼ʧ�ܵ���Ϣ
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_msg", e.getMessage());
			request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			//��¼ʧ��,ˢ�µ�¼ҳ������û���¼ʧ�ܵ���Ϣ
			e.printStackTrace();
			request.setAttribute("error_msg", e.getMessage());
			request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
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
