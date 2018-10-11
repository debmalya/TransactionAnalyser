package org.deb.analysis.impl;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.function.Predicate;

import org.deb.analysis.TransactionAnalysis;
import org.deb.model.Transaction;

public class TransactionAnalysisImpl implements TransactionAnalysis {

	/**
	 * 
	 */
	@Override
	public DoubleSummaryStatistics analyzeTransaction(Collection<Transaction> transactions,
			Predicate<Transaction> analyticBehaviour) {

		if (transactions != null && analyticBehaviour != null) {
			return transactions.parallelStream().filter(analyticBehaviour).mapToDouble(t -> t.getAmount())
					.summaryStatistics();
		}
		return new DoubleSummaryStatistics();

	}

}
