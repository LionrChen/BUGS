package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugs.domain.Book;
import com.bugs.domain.Customer;
import com.bugs.exception.BookException;
import com.bugs.service.BookService;
import com.bugs.service.CustomerService;

public class DeleteCustomerByBatchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteCustomerByBatchServlet() {
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
		String ids =  request.getParameter("ids");
		//ids处理成数组 1
		String[] idArray= ids.split(",");
		
		
		//处理业务逻辑，调用service
		CustomerService cService=new CustomerService();
		try {
			//获取用户类型
			Customer tempCustomer= new Customer();
			tempCustomer=cService.queryCustomer(Integer.parseInt(idArray[0]));
			
			//批量删除
			cService.DelBybatch(idArray);
		    //删除成功！继续查询剩余数据并在列表页面显示
			
			List<Customer> list = cService.queryCustomersList(tempCustomer.type);
			request.setAttribute("customer",list );
			request.getRequestDispatcher("home/commonCustomerList.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//删除失败
			request.setAttribute("del_msg", e.getMessage());
			request.getRequestDispatcher("product/list.jsp").forward(request, response);
		} catch (BookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//删除失败
			request.setAttribute("del_msg", e.getMessage());
			request.getRequestDispatcher("product/list.jsp").forward(request, response);
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
