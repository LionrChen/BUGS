package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugs.domain.Book;
import com.bugs.domain.Customer;
import com.bugs.domain.ShoppingCart;
import com.bugs.service.BookService;
import com.bugs.service.ShoppingCartService;

public class ViewMyCartServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ViewMyCartServlet() {
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

		Customer customer = (Customer)request.getSession().getAttribute("customer");
		
		if (customer == null) {
			response.sendRedirect("userLogin.jsp");
		}else {
			
			ShoppingCartService shoppingCartService = new ShoppingCartService();
			BookService bookService = new BookService();
			
			try {
				List<ShoppingCart> shoppingCartList = shoppingCartService.QueryAllShoppingCartItemByCustomerId(customer.getId());
				
				List<Book> books = new ArrayList<Book>();
				
				for (ShoppingCart shoppingCart : shoppingCartList) {
					books.add(bookService.queryBookById(shoppingCart.getBookid()));
				}
				
				request.getSession().setAttribute("shoppingCarts", shoppingCartList);
				request.getSession().setAttribute("books", books);
				response.sendRedirect("shoppingCart.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
