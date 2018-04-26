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

import com.bugs.domain.Customer;
import com.bugs.domain.order;
import com.bugs.service.OrderService;
import com.bugs.service.WalletService;

public class ConfirmPaymentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ConfirmPaymentServlet() {
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
		
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		String payfun = request.getParameter("payfun");
		String action = request.getParameter("action");
		
		OrderService orderService = new OrderService();
		WalletService walletService = new WalletService();
		
		if (action.equals("moreOrder")) {
			String numbers = request.getParameter("ordernumber");
			String[] ordernumbers = numbers.split(",");
			
			List<order> needOrders = new ArrayList<order>();
			double consum = 0;
			
			try {
				if (payfun.equals("wallet")) {
					for (String ordernumber : ordernumbers) {
						order needPayOrder = orderService.QueryOrderItemByOrderNumber(ordernumber);
						needOrders.add(needPayOrder);
						consum += needPayOrder.getPayment();
						if(walletService.pay(customer, needPayOrder.getPayment())){
							needPayOrder.setPaymentState(1);
							needPayOrder.setPosition(addressId);
							orderService.UpdateOrderItemById(needPayOrder);
							String payInfo = "支付成功！";
							request.getSession().setAttribute("payInfo", payInfo);
						}else {
							String payInfo = "支付失败！";
							request.getSession().setAttribute("payInfo", payInfo);
						}
						
					}
				}
				
				request.getSession().setAttribute("consum", consum);
				response.sendRedirect("successPay.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}else {
			try {
				String ordernumber = request.getParameter("ordernumber");
				order needPayOrder = orderService.QueryOrderItemByOrderNumber(ordernumber);
				if (payfun.equals("wallet")) {
					if(walletService.pay(customer, needPayOrder.getPayment())){
						needPayOrder.setPaymentState(1);
						needPayOrder.setPosition(addressId);
						orderService.UpdateOrderItemById(needPayOrder);
						String payInfo = "支付成功！";
						request.getSession().setAttribute("payInfo", payInfo);
					}else {
						String payInfo = "支付失败！";
						request.getSession().setAttribute("payInfo", payInfo);
					}
				}
				double consum = needPayOrder.payment;
				request.getSession().setAttribute("consum", consum);
				response.sendRedirect("successPay.jsp");
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
