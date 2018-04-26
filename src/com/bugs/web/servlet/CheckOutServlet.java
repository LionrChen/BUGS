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

import com.bugs.domain.Address;
import com.bugs.domain.Book;
import com.bugs.domain.Customer;
import com.bugs.domain.ShoppingCart;
import com.bugs.domain.order;
import com.bugs.service.AddressService;
import com.bugs.service.BookService;
import com.bugs.service.OrderService;
import com.bugs.service.ShoppingCartService;

public class CheckOutServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckOutServlet() {
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
		String ids = request.getParameter("ordernumbers");
		String[] shoppingCartItemIds = ids.split(",");
		
		AddressService addressService = new AddressService();
		BookService bookService = new BookService();
		OrderService orderService = new OrderService();
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		
		if(customer == null){
			response.sendRedirect("userLogin.jsp");
		}else {
			try {
				List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
				List<Book> books = new ArrayList<Book>();
				List<order> orders = new ArrayList<order>();
				List<Address> addresses = addressService.QueryAddressItemByCustomerId(customer);
				
				for (String id : shoppingCartItemIds) {
					int intid = Integer.parseInt(id);
					shoppingCarts.add(shoppingCartService.QueryShoppingCartItemById(intid));
				}
				
				for (ShoppingCart shoppingCart : shoppingCarts) {
					Book book = bookService.queryBookById(shoppingCart.getBookid());
					books.add(book);
					orders.add(orderService.creatOrder(customer, book, shoppingCart.getNum()));
					shoppingCartService.DeleteShoppingCartItem(shoppingCart);
				}
				
				request.getSession().setAttribute("orders", orders);
				request.getSession().setAttribute("books", books);
				request.getSession().setAttribute("addresses", addresses);
				response.sendRedirect("confirmOrders.jsp");
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
