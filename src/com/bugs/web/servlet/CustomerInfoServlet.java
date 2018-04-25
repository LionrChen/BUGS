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
import com.bugs.domain.Wallet;
import com.bugs.domain.order;
import com.bugs.service.BookService;
import com.bugs.service.OrderService;
import com.bugs.service.ShoppingCartService;
import com.bugs.service.WalletService;

public class CustomerInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CustomerInfoServlet() {
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

		Customer customer = new Customer();
		customer = (Customer)request.getSession().getAttribute("customer");
		
		List<order> toBePaidOrders = new ArrayList<order>();
		List<order> paidOrders = new ArrayList<order>();
		List<Book> toBePaidOrderBooks = new ArrayList<Book>();
		List<Book> paidOrderBooks = new ArrayList<Book>();
		List<Book> cartBooks = new ArrayList<Book>();
		
		WalletService walletService = new WalletService();
		BookService bookService = new BookService();
		OrderService orderService = new OrderService();
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		
		try {
			Wallet wallet = walletService.QueryWalletItemByCustomerId(customer.id);
			List<order> orders = orderService.QueryOrdersItemByCustomerId(customer);
			for (order order : orders) {
				if(order.paymentState == 0){
					toBePaidOrders.add(order);
					toBePaidOrderBooks.add(bookService.queryBookById(order.getBookid()));
				}
				if(order.paymentState == 1){
					paidOrders.add(order);
					paidOrderBooks.add(bookService.queryBookById(order.getBookid()));
				}
			}
			List<ShoppingCart> shoppingCarts = shoppingCartService.QueryAllShoppingCartItemByCustomerId(customer.id);
			
			
			for (ShoppingCart shoppingCart : shoppingCarts) {
				Book book2 = bookService.queryBookById(shoppingCart.bookid);
				cartBooks.add(book2);
			}
			request.getSession().setAttribute("wallet", wallet);
			request.getSession().setAttribute("toBePaidOrders", toBePaidOrders);
			request.getSession().setAttribute("paidOrders", paidOrders);
			request.getSession().setAttribute("shoppingCarts", shoppingCarts);
			request.getSession().setAttribute("cartBooks", cartBooks);
			request.getSession().setAttribute("toBePaidOrderBooks", toBePaidOrderBooks);
			request.getSession().setAttribute("paidOrderBooks", paidOrderBooks);
			response.sendRedirect("userInfo.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
