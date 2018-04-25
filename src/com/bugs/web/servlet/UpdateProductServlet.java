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

import com.bugs.domain.Book;
import com.bugs.exception.BookException;
import com.bugs.service.BookService;
import com.bugs.util.UploadUtils;

public class UpdateProductServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateProductServlet() {
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

		Map<String,String> map = UploadUtils.HandleFormByUpload(request, getServletContext());
		
		Book book =new Book();
		try {
			BeanUtils.populate(book, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.处理业务逻辑(修改图书信息)
		BookService bookService =new BookService();
		try {
			bookService.updateBookById(book);
			//修改成功
			//3.分发转向(查询图书列表的servlet： /queryBookServlet)
			request.getRequestDispatcher("queryBookServlet").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//修改失败，刷新当前页，显示错误信息
			request.setAttribute("error_update_msg", e.getMessage());
			request.getRequestDispatcher("product/edit.jsp").forward(request, response);
		} catch (BookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//修改失败，刷新当前页，显示错误信息
			request.setAttribute("error_update_msg", e.getMessage());
			request.getRequestDispatcher("product/edit.jsp").forward(request, response);
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
