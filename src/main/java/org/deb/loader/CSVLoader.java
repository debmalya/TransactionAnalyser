package org.deb.loader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.deb.model.Transaction;

public interface CSVLoader {
	/**
	 * Transaction date format.
	 * All implementor of this interface can use this one.
	 */
	public static final SimpleDateFormat ddMMyyyyhhmmss = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	/** 
	 * @param csvFile to load. First line of the csvFile will contain column names.
	 * @return a collection of all the transactions in csvFile.
	 * @throws IOException  - If specified file does not exist, or any other IO exception.
	 * @throws ParseException - if transaction date is not in valid format.
	 */
	Collection<Transaction> loadTransactions(String csvFile) throws IOException, ParseException;
}
