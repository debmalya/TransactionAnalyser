package org.deb.model;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9212684911639538160L;
	/**
	 * Transaction ID.
	 */
	private String ID;

	/**
	 * Transaction date
	 */
	private Date date;
	/**
	 * Transaction amount
	 */
	private double amount;
	
	/**
	 * Merchant name.
	 */
	private String merchant;

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	/**
	 * Transaction type, currently PAYMENT and REVERSAL.
	 */
	private TransactionType transactionType;

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
