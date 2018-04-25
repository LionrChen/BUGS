package com.bugs.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bugs.domain.Customer;
import com.bugs.domain.User;
import com.bugs.service.UserService;

public class CustomerRegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CustomerRegisterServlet() {
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
		//PrintWriter out = response.getWriter();
		Map<String,String[]> map  =  request.getParameterMap();
		Customer user =new Customer();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setActiveCode(UUID.randomUUID().toString());
		String checkcode =  (String) request.getSession().getAttribute("checkcode_session");
		System.out.println(checkcode);
		String ckCode =request.getParameter("ckCode");
		System.out.println(ckCode);
		if(checkcode.equals(ckCode)){
			String confirmPwd=request.getParameter("password1");
			String pwd = user.getPassword();
			if("".equals(pwd)||null==pwd){
				request.setAttribute("error_msg", "密码不能为空");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else{
				 //判断两次密码是否一致
				if(user.getPassword().equals(confirmPwd)){
					//2.处理业务逻辑(根据请求参数去处理得到想要的结果)	  用户业务(注册，登录)
					UserService userService =new UserService();
					try {
					 boolean isSuccess = 	userService.registerCustomer(user);  
					//3.分发转向(页面跳转)
					 if (isSuccess) {
						//跳转到注册成功页面
						 request.getRequestDispatcher("userLogin.jsp").forward(request, response);
					}else{
						//重新跳转到注册页面重新输入
						request.setAttribute("error_msg", "注册失败");
						request.getRequestDispatcher("register01.jsp").forward(request, response);
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//重新跳转到注册页面重新输入
						request.setAttribute("error_msg", "注册失败");
						request.getRequestDispatcher("register01.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("error_msg", "两次密码不一致");
					request.getRequestDispatcher("register01.jsp").forward(request, response);
				}
			}
			
		}else{
			System.out.println("==验证码校验失败=="+ckCode);
			request.setAttribute("code_msg", "验证码输入错误");
			request.getRequestDispatcher("register01.jsp").forward(request, response);
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
