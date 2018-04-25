package com.bugs.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugs.domain.Address;
import com.bugs.domain.Book;
import com.bugs.domain.Customer;
import com.bugs.domain.order;
import com.bugs.service.AddressService;
import com.bugs.service.BookService;
import com.bugs.service.OrderService;
import com.bugs.util.ProductID;

public class DirectPurchaseServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DirectPurchaseServlet() {
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
		String bookid = request.getParameter("bookId");
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		AddressService addressService = new AddressService();
		BookService bookService = new BookService();
		OrderService orderService = new OrderService();
		order order = new order();
		if (customer == null) {
			response.sendRedirect("userLogin.jsp");
		}else {
			try {
				List<Address> addresses = addressService.QueryAddressItemByCustomerId(customer);
				Book toBuyBook = bookService.queryBookById(bookid);
				
				order.setOrdernumber(ProductID.getOrderIdByTime());
				order.setUserid(customer.id);
				order.setBookid(toBuyBook.id);
				order.setNum(pnum);
				order.setPayment(pnum*toBuyBook.price);
				orderService.InsertOrderItem(order);
				
				order newOrder = orderService.QueryOrderItemByOrderNumber(order.ordernumber);
				
				request.getSession().setAttribute("newOrder", newOrder);
				request.getSession().setAttribute("addresses", addresses);
				request.getSession().setAttribute("toBuyBook", toBuyBook);
				response.sendRedirect("confirmOrder.jsp");
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
