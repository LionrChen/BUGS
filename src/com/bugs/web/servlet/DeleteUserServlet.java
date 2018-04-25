package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugs.domain.User;
import com.bugs.exception.UserException;
import com.bugs.service.UserService;

public class DeleteUserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteUserServlet() {
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

		System.out.println("===doGet==========");
		//1.获取请求参数
		String id = request.getParameter("id");
		System.out.println("=id=="+id);
		//2.处理业务逻辑
		UserService userService=new UserService();
		List<User> list=null;
		try {
			userService.deleteUserById(id);
			//3.分发转向
			//删除成功
			
			System.out.println("===1111111111111==========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("===22222222222222222==========");
			e.printStackTrace();
			request.setAttribute("error_msg01", e.getMessage());
			System.out.println("===3333333333333333==========");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_msg01", e.getMessage());
			
		}finally {
			System.out.println("=============");
			try {
				list = userService.queryUserList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("home/showuserlist.jsp").forward(request, response);
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
