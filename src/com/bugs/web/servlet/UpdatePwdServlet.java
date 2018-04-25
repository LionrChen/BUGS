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

public class UpdatePwdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdatePwdServlet() {
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
	    	//user封装了用户名和密码
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    UserService  userService =new UserService();
	    String confirmpassword = request.getParameter("confirmpassword");
	     //2.处理业务逻辑 ，
	       //两次密码不一致 
	    if(!confirmpassword.equals(user.getPassword())){
	    	  request.setAttribute("user", user);
	    	  request.setAttribute("error_msg", "两次密码不一致");
	    	  request.setAttribute("confirmpassword", confirmpassword);
	    	  request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
	    	return;
	    }
	    
	    //判断密码格式
	    if(!user.getPassword().matches("^\\d{6,}$")){
	    	request.setAttribute("user", user);
    		request.setAttribute("confirmpassword", confirmpassword);
    		request.setAttribute("error_msg", "密码格式不正确");
    		request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
    		return;
	    }
	   
		try {
			 //根据用户名查询原来的密码
			String  pwd = userService.queryPwdByUserName(user.getUsername());
			 if (pwd.equals(confirmpassword)) {
				    request.setAttribute("user", user);
		    		request.setAttribute("confirmpassword", confirmpassword);
		    		request.setAttribute("error_msg", "新密码不能和原密码一样");
		    		request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
		    		return;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
		try {
			try {
				userService.updatePwd(user);
				//修改密码成功.重新登录
				response.getWriter().write("<script type='text/javascript'>"
						+ "window.top.location.href='login.jsp'</script>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("user", user);
	    		request.setAttribute("confirmpassword", confirmpassword);
	    		request.setAttribute("error_msg", e.getMessage());
	    		request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
			}
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//修改密码失败
			request.setAttribute("user", user);
    		request.setAttribute("confirmpassword", confirmpassword);
    		request.setAttribute("error_msg", e.getMessage());
    		request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
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
