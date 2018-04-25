package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bugs.domain.User;
import com.bugs.service.UserService;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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
		User user =new User();
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
		System.out.println("=dddd==="+user.getUsername());
		
		String checkcode =  (String) request.getSession().getAttribute("checkcode_session");
		
		String ckCode =request.getParameter("ckCode");
		
		if(checkcode.equals(ckCode)){
			
			//�ж����������
			//1.��ȡȷ������������
			String confirmPwd=request.getParameter("password1");
			String pwd = user.getPassword();
			if("".equals(pwd)||null==pwd){
				request.setAttribute("error_msg", "���벻��Ϊ��");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else{
				 //�ж����������Ƿ�һ��
				if(user.getPassword().equals(confirmPwd)){
					//2.����ҵ���߼�(�����������ȥ����õ���Ҫ�Ľ��)	  �û�ҵ��(ע�ᣬ��¼)
					UserService userService =new UserService();
					try {
					 boolean isSuccess = 	userService.registerUser(user);  
					//3.�ַ�ת��(ҳ����ת)
					 if (isSuccess) {
						//��ת��ע��ɹ�ҳ��
						 request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);
					}else{
						//������ת��ע��ҳ����������
						request.setAttribute("error_msg", "ע��ʧ��");
						request.getRequestDispatcher("register.jsp").forward(request, response);
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//������ת��ע��ҳ����������
						request.setAttribute("error_msg", "ע��ʧ��");
						request.getRequestDispatcher("register.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("error_msg", "�������벻һ��");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
			
		}else{
			System.out.println("==��֤��У��ʧ��=="+ckCode);
			request.setAttribute("code_msg", "��֤���������");
			request.getRequestDispatcher("register.jsp").forward(request, response);
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
