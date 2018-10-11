package org.deb.analysis;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.function.Predicate;

import org.deb.model.Transaction;

public interface TransactionAnalysis {
	/**
	 * 
	 * @param transactions collection of transactions.
	 * @param analyticBehaviour to filter transactions.
	 * @return summary statistics.
	 */
	DoubleSummaryStatistics analyzeTransaction(Collection<Transaction> transactions,Predicate<Transaction> analyticBehaviour);

}
