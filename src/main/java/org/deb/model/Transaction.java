package org.deb.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.deb.loader.CSVLoader;

public class Transaction implements Serializable {

	private static final Logger logger = Logger.getLogger("Transaction");
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

	/**
	 * Reverse transaction id.
	 */
	private String reverseTranId;

	public String getReverseTranId() {
		return reverseTranId;
	}

	public void setReverseTranId(String reverseTranId) {
		this.reverseTranId = reverseTranId;
	}

	public Transaction(String line) {
		String[] values = line.split(",");
		this.ID = values[0].trim();
		try {
			this.date = CSVLoader.ddMMyyyyhhmmss.parse(values[1]);
		} catch (ParseException e) {
			logger.log(Level.SEVERE, String.format("Not able to parse %s", values[1]), e);
		}
		this.amount = Double.parseDouble(values[2]);
		this.merchant = values[3].trim();
		String type = values[4].trim();
		if ("PAYMENT".equals(type)) {
			this.transactionType = TransactionType.PAYMENT;
		} else if ("REVERSAL".equals(type)) {
			this.transactionType = TransactionType.REVERSAL;
			this.reverseTranId = values[5];
			this.amount *= -1;
		}

	}

	public Transaction() {

	}

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
