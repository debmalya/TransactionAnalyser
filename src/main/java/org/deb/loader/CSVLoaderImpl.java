package org.deb.loader;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.deb.model.Transaction;
import org.deb.model.TransactionType;

import au.com.bytecode.opencsv.CSVReader;

public class CSVLoaderImpl implements CSVLoader {

	/**
	 * To log error and debug messages.
	 */
	private static final Logger logger = Logger.getLogger("CSVLoaderImpl");

	@Override
	public Collection<Transaction> loadTransactions(String csvFile) throws IOException, ParseException {
		Collection<Transaction> transactions = new ArrayList<>();

		// Here I am maintaining transaction id as key and transaction as value.
		// Each PAYMENT transaction will be added here.
		// Later If I encounter any REVERSAL transaction
		// Retrieve the original transaction from this map.
		// Remove the original transaction from the collection of transactions.
		// So that transaction collection will not have any reverse transaction
		// irrespective of date.
		Map<String, Transaction> transactionMap = new HashMap<>();

		try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
			boolean isFirstLine = true;
			String[] eachLine = reader.readNext();

			while (eachLine != null) {
				if (isFirstLine) {
					isFirstLine = false;
				} else {

					Transaction transaction = new Transaction();
					transaction.setID(eachLine[0]);
					transaction.setDate(CSVLoader.ddMMyyyyhhmmss.parse(eachLine[1]));
					transaction.setAmount(Double.parseDouble(eachLine[2]));
					transaction.setMerchant(eachLine[3].trim());

					String paymentType = eachLine[4].trim();
					if ("PAYMENT".equalsIgnoreCase(paymentType)) {
						transaction.setTransactionType(TransactionType.PAYMENT);
						transactions.add(transaction);
						transactionMap.put(transaction.getID(), transaction);
					} else if ("REVERSAL".equalsIgnoreCase(paymentType)) {
						transaction.setTransactionType(TransactionType.REVERSAL);
						if (eachLine.length > 5) {
							Transaction reverseTransaction = transactionMap.get(eachLine[5].trim());
							if (reverseTransaction != null) {
								boolean isRemoved = transactions.remove(reverseTransaction);
								if (isRemoved) {
									logger.log(Level.INFO, String.format(
											"Original transaction with id '%s' removed from collection, REVERSAL transaction id is '%s' ",
											reverseTransaction.getID(), eachLine[0]));
								}
							} else {
								logger.log(Level.SEVERE,
										String.format("Original transaction not found for the REVERSAL transaction '%s'",
												eachLine[0]));
							}
						} else {
							logger.log(Level.SEVERE, String.format(
									"Original transaction id is missing in the REVERSAL transaction '%s'", eachLine[0]));
						}
					}

				}
				eachLine = reader.readNext();
			}

		}

		return transactions;
	}

}
