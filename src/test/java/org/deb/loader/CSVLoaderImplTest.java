package org.deb.loader;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

import org.deb.loader.impl.CSVLoaderImpl;
import org.deb.model.Transaction;
import org.junit.Assert;
import org.junit.Test;



public class CSVLoaderImplTest {

	@Test
	public void testLoadTransactions() {
		CSVLoader loader = new CSVLoaderImpl();
		try {
			Collection<Transaction> transactions = loader.loadTransactions("./src/main/resources/transactions.csv");
			Assert.assertNotNull(transactions);
			// There is a reverse transaction, that transaction and related payment transaction will not be loaded.
			Assert.assertEquals(4, transactions.size());
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(),true);
		}
	}

}
