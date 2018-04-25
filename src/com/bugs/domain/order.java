package com.bugs.domain;

public class order {
	public int id;
	public String ordernumber;
	public int userid;
	public String bookid;
	public int num;
	public double payment;
	public int paymentState;
	public String telephone;
	public int position;
	public String reason;
	public int reviewState;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public int getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(int paymentState) {
		this.paymentState = paymentState;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getReviewState() {
		return reviewState;
	}
	public void setReviewState(int reviewState) {
		this.reviewState = reviewState;
	}
}
