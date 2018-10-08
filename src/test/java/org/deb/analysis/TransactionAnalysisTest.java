package org.deb.analysis;

import java.io.IOException;
import java.text.ParseException;
import java.util.DoubleSummaryStatistics;

import org.deb.loader.CSVLoader;
import org.deb.loader.CSVLoaderImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author debmalyajash
 *
 */
public class TransactionAnalysisTest {

	/**
	 * To check whether null is handled properly or not.
	 */
	@Test
	public void testAnalyzeTransactionsAllNull() {
		Analysis analysis = new Analysis();

		try {
			DoubleSummaryStatistics dss = analysis.analyzeTransactions(null, null, null, null);
			Assert.assertNull(dss);
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		}

	}

	/**
	 * To check whether null is handled properly or not.
	 */
	@Test
	public void testAnalyzeTransactionsPartialNull() {
		Analysis analysis = new Analysis();

		try {
			DoubleSummaryStatistics dss = analysis.analyzeTransactions(null, "Kwik-E-Mart", "20/08/2018 12:00:00",
					"20/08/2018 13:00:00");
			Assert.assertNull(dss);
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		}

	}

	/**
	 * To check whether null is handled properly or not.
	 */
	@Test
	public void testAnalyzeTransactions() {
		Analysis analysis = new Analysis();
		CSVLoader loader = new CSVLoaderImpl();

		try {
			DoubleSummaryStatistics dss = analysis.analyzeTransactions(
					loader.loadTransactions("./src/main/resources/transactions.csv"), "Kwik-E-Mart",
					"20/08/2018 12:00:00", "20/08/2018 13:00:00");
			Assert.assertNotNull(dss);
			Assert.assertEquals(1, dss.getCount());
			Assert.assertEquals(59.99, dss.getAverage(), 0.00);

			dss = analysis.analyzeTransactions(loader.loadTransactions("./src/main/resources/transactions.csv"),
					"MacLaren", "20/08/2018 12:00:00", "20/08/2018 13:00:00");
			Assert.assertNotNull(dss);
			Assert.assertEquals(1, dss.getCount());
			Assert.assertEquals(5.00, dss.getAverage(), 0.00);
			
			dss = analysis.analyzeTransactions(loader.loadTransactions("./src/main/resources/transactions.csv"),
					"MacLaren", "20/08/2018 12:00:00", "20/08/2018 15:00:00");
			Assert.assertNotNull(dss);
			Assert.assertEquals(2, dss.getCount());
			Assert.assertEquals(52.25, dss.getAverage(), 0.00);
			
			dss = analysis.analyzeTransactions(loader.loadTransactions("./src/main/resources/transactions.csv"),
					"MacLaren", "20/08/2018 12:52:00", "20/08/2018 15:00:00");
			Assert.assertNotNull(dss);
			Assert.assertEquals(1, dss.getCount());
			Assert.assertEquals(99.50, dss.getAverage(), 0.00);
			
			dss = analysis.analyzeTransactions(loader.loadTransactions("./src/main/resources/transactions.csv"),
					"MacLaren", "20/08/2018 12:50:02", "20/08/2018 14:07:10");
			Assert.assertNotNull(dss);
			Assert.assertEquals(2, dss.getCount());
			Assert.assertEquals(52.25, dss.getAverage(), 0.00);

		} catch (ParseException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		}

	}
}
