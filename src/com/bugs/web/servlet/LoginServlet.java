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
			//封装请求参数的值到User对象中
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.处理业务逻辑
		  UserService userService =new UserService();
		  try {
			 //登录成功
			 User user2 = userService.LoginUser(user);
			//登录成功，跳转到程序的首界面
			//登录状态：
			request.getSession().setAttribute("user", user2);
			//request.setAttribute("user", user);作用范围比较小
			//重定向：地址栏发生变化，请求了2次
			response.sendRedirect("home/home.jsp");
			//请求转发：地址栏不变，请求了一次
			//request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (SQLException e) {
			//登录失败,刷新登录页面告诉用户登录失败的信息
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_msg", e.getMessage());
			request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			//登录失败,刷新登录页面告诉用户登录失败的信息
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
