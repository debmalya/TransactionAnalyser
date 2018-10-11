package org.deb.analysis.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.DoubleSummaryStatistics;

import org.deb.analysis.TransactionAnalysis;
import org.deb.loader.CSVLoader;
import org.deb.loader.impl.CSVLoaderImpl;
import org.junit.Assert;
import org.junit.Test;

public class TransactionAnalysisImplTest {

	/**
	 * To check how does it behave with all null parameters.S
	 */
	@Test
	public void testAnalyzeTransactionAllNull() {
		TransactionAnalysis transactionAnalysis = new TransactionAnalysisImpl();
		DoubleSummaryStatistics dss = transactionAnalysis.analyzeTransaction(null, null);
		Assert.assertNotNull(dss);
		Assert.assertEquals(0, dss.getCount());
		Assert.assertEquals(0.00, dss.getAverage(), 0.00);
		Assert.assertEquals(Double.NEGATIVE_INFINITY, dss.getMax(), 0.00);
		Assert.assertEquals(Double.POSITIVE_INFINITY, dss.getMin(), 0.00);
		Assert.assertEquals(0.00, dss.getSum(), 0.00);
	}

	/**
	 * To check how does it behave with all null parameters.S
	 */
	@Test
	public void testAnalyzeTransactionNullPredicate() {
		TransactionAnalysis transactionAnalysis = new TransactionAnalysisImpl();
		CSVLoader loader = new CSVLoaderImpl();
		DoubleSummaryStatistics dss;
		try {
			dss = transactionAnalysis
					.analyzeTransaction(loader.loadTransactions("./src/main/resources/transactions.csv"), null);
			Assert.assertNotNull(dss);
			Assert.assertEquals(0, dss.getCount());
			Assert.assertEquals(0.00, dss.getAverage(), 0.00);
			Assert.assertEquals(Double.NEGATIVE_INFINITY, dss.getMax(), 0.00);
			Assert.assertEquals(Double.POSITIVE_INFINITY, dss.getMin(), 0.00);
			Assert.assertEquals(0.00, dss.getSum(), 0.00);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		}

	}

	/**
	 * To check how does it behave with all null parameters.S
	 */
	@Test
	public void testAnalyzeTransactionOnlyMerchant() {
		TransactionAnalysis transactionAnalysis = new TransactionAnalysisImpl();
		CSVLoader loader = new CSVLoaderImpl();
		DoubleSummaryStatistics dss;
		try {
			dss = transactionAnalysis.analyzeTransaction(
					loader.loadTransactions("./src/main/resources/transactions.csv"),
					p -> p.getMerchant().equals("Kwik-E-Mart"));
			Assert.assertNotNull(dss);
			Assert.assertEquals(2, dss.getCount());

			Assert.assertEquals(32.495, Math.round(dss.getAverage() * 1000) / 1000.00, 0.00);
			Assert.assertEquals(59.99, dss.getMax(), 0.00);
			Assert.assertEquals(5.00, dss.getMin(), 0.00);
			Assert.assertEquals(64.99, Math.round(dss.getSum() * 1000) / 1000.00, 0.00);

		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		}

	}

	/**
	 * To check how does it behave with all null parameters.S
	 */
	@Test
	public void testAnalyzeTransactionOnlyDate() {
		TransactionAnalysis transactionAnalysis = new TransactionAnalysisImpl();
		CSVLoader loader = new CSVLoaderImpl();
		DoubleSummaryStatistics dss;
		try {
			long startTime = CSVLoader.ddMMyyyyhhmmss.parse("20/08/2018 12:45:33").getTime() - 1;
			long endTime = CSVLoader.ddMMyyyyhhmmss.parse("20/08/2018 14:07:10").getTime() + 1;
			dss = transactionAnalysis.analyzeTransaction(
					loader.loadTransactions("./src/main/resources/transactions.csv"),
					p -> p.getDate().getTime() >= startTime && p.getDate().getTime() <= endTime);
			Assert.assertNotNull(dss);
			Assert.assertEquals(4, dss.getCount());

			Assert.assertEquals(42.373, Math.round(dss.getAverage() * 1000) / 1000.00, 0.00);
			Assert.assertEquals(99.50, dss.getMax(), 0.00);
			Assert.assertEquals(5.00, dss.getMin(), 0.00);
			Assert.assertEquals(169.49, Math.round(dss.getSum() * 1000) / 1000.00, 0.00);

		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.assertFalse(e.getMessage(), true);
		}

	}

}
