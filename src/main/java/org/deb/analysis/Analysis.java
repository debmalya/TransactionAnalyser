package org.deb.analysis;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

import org.deb.loader.CSVLoader;
import org.deb.model.Transaction;

/**
 * 
 * @author debmalyajash
 *
 */
public class Analysis {

	/**
	 * 
	 * @param transactions
	 * @param merchant
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 * @throws ParseException
	 */
	public DoubleSummaryStatistics analyzeTransactions(Collection<Transaction> transactions, String merchant,
			String startDateTime, String endDateTime) throws ParseException {
		DoubleSummaryStatistics dss = new DoubleSummaryStatistics();

		if (transactions != null && merchant != null && startDateTime != null && endDateTime != null) {
			Date start = CSVLoader.ddMMyyyyhhmmss.parse(startDateTime);
			long startTime = start.getTime()-1;
			start.setTime(startTime);
			
			Date end = CSVLoader.ddMMyyyyhhmmss.parse(endDateTime);
			long endTime = end.getTime()+1;
			end.setTime(endTime);
			
			// parallel to take processing advantage of multiple processors.
			// predicate to filter out only relevant one.
			// summaryStatistics is terminal method.
			dss = transactions
					.stream().parallel().filter(p -> p.getMerchant().equalsIgnoreCase(merchant)
							&& p.getDate().after(start) && p.getDate().before(end))
					.mapToDouble(t -> t.getAmount()).summaryStatistics();

		}
		return dss;
	}
	
	/**
	 * 
	 * @param transactions
	 * @param merchant
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 * @throws ParseException
	 */
	public boolean isThereMatchingTransaction(Collection<Transaction> transactions, String merchant,
			String startDateTime, String endDateTime) throws ParseException {
		boolean isThereMatchingTransactions = false;

		if (transactions != null && merchant != null && startDateTime != null && endDateTime != null) {
			Date start = CSVLoader.ddMMyyyyhhmmss.parse(startDateTime);
			long startTime = start.getTime()-1;
			start.setTime(startTime);
			
			Date end = CSVLoader.ddMMyyyyhhmmss.parse(endDateTime);
			long endTime = end.getTime()+1;
			end.setTime(endTime);
			
			// parallel to take processing advantage of multiple processors.
			// predicate to filter out only relevant one.
			// anyMatch is terminal method.
			isThereMatchingTransactions = transactions
					.stream().parallel().anyMatch(p -> p.getMerchant().equalsIgnoreCase(merchant)
							&& p.getDate().after(start) && p.getDate().before(end));

		}
		return isThereMatchingTransactions;
	}

}
