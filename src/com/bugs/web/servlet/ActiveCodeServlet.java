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
		//得到激活码，
		//1.获取请求参数
		String activeCode = request.getParameter("activeCode");
		//2.处理业务逻辑，激活验证码
		 UserService userService=new UserService();
		 try {
			boolean isFlag =  userService.activeCode(activeCode);
			//3.分发转向
			if (isFlag) {
				//邮箱激活成功,跳转到登录页面
				request.setAttribute("msg", "激活成功，请登录");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				//邮箱激活失败
				printWriter.write("邮箱激活失败,请重新激活");
				System.out.println("邮箱激活失败,请重新激活");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//邮箱激活失败
			printWriter.write("邮箱激活失败,请重新激活");
			System.out.println("邮箱激活失败,请重新激活");
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
