package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugs.domain.Customer;
import com.bugs.domain.ShoppingCart;
import com.bugs.service.BookService;
import com.bugs.service.ShoppingCartService;

public class GetCartInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCartInfoServlet() {
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

		PrintWriter out = response.getWriter();
		
		int cartItemsNum = 0;	
		double cartItemsPayment = 0;
		String responseText = "0,0";

		Customer customer = (Customer)request.getSession().getAttribute("customer");
		
		if (customer == null) {
			out.print(responseText);
		}else {
			ShoppingCartService shoppingCartService = new ShoppingCartService();
			BookService bookService = new BookService();
			
			try {
				List<ShoppingCart> shoppingCarts = shoppingCartService.QueryAllShoppingCartItemByCustomerId(customer.getId());
				cartItemsNum = shoppingCarts.size();
				
				for (ShoppingCart shoppingCart : shoppingCarts) {
					cartItemsPayment += bookService.queryBookById(shoppingCart.getBookid()).getPrice();
				}
				
				responseText = cartItemsPayment+","+cartItemsNum;
				
				out.print(responseText);
				
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
