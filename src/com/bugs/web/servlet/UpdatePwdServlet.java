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
	    	//user��װ���û���������
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
	     //2.����ҵ���߼� ��
	       //�������벻һ�� 
	    if(!confirmpassword.equals(user.getPassword())){
	    	  request.setAttribute("user", user);
	    	  request.setAttribute("error_msg", "�������벻һ��");
	    	  request.setAttribute("confirmpassword", confirmpassword);
	    	  request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
	    	return;
	    }
	    
	    //�ж������ʽ
	    if(!user.getPassword().matches("^\\d{6,}$")){
	    	request.setAttribute("user", user);
    		request.setAttribute("confirmpassword", confirmpassword);
    		request.setAttribute("error_msg", "�����ʽ����ȷ");
    		request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
    		return;
	    }
	   
		try {
			 //�����û�����ѯԭ��������
			String  pwd = userService.queryPwdByUserName(user.getUsername());
			 if (pwd.equals(confirmpassword)) {
				    request.setAttribute("user", user);
		    		request.setAttribute("confirmpassword", confirmpassword);
		    		request.setAttribute("error_msg", "�����벻�ܺ�ԭ����һ��");
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
				//�޸�����ɹ�.���µ�¼
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
			//�޸�����ʧ��
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
