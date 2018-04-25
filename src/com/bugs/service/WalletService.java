package com.bugs.service;

import java.sql.SQLException;
import java.util.List;

import com.bugs.dao.WalletDao;
import com.bugs.domain.Customer;
import com.bugs.domain.Wallet;

public class WalletService {
	WalletDao walletDao = new WalletDao();
	public  boolean insertWalletItem(Customer customer,Wallet wallet) throws SQLException{
		int rowCount = walletDao.InsertWallet(customer, wallet);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean DeleteWalletItem(Wallet wallet) throws SQLException{
		int rowCount = walletDao.DeleteWallet(wallet.id);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	
	public  boolean UpdateWalletItemNumById(Wallet wallet) throws SQLException{
		int rowCount = walletDao.UpdateWallet(wallet.id,wallet.balance);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
	public List<Wallet> QueryWalletItem() throws SQLException {
		return walletDao.queryWallet();
	}
	public Wallet QueryWalletItemByCustomerId(int id) throws SQLException {
		return walletDao.queryWalletById(id);
	}
	public boolean pay(Customer customer, double payment) throws SQLException {
		int rowCount = walletDao.UpdateWallet(walletDao.queryWalletById(customer.id).id, walletDao.queryWalletById(customer.id).balance-payment);
		if(rowCount>0){
			return true;
		}else {
			return false;
		}
	}
}
