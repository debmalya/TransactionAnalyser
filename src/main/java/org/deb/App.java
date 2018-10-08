package org.deb;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;

import org.deb.analysis.Analysis;
import org.deb.loader.CSVLoader;
import org.deb.loader.CSVLoaderImpl;
import org.deb.model.Transaction;

public class App {

	public static void main(String[] args) throws IOException, ParseException {
		CSVLoader loader = new CSVLoaderImpl();
		Analysis transactionAnalyser = new Analysis();

		String inputFile = "./src/main/resources/transactions.csv";
		String merchant = "Kwik-E-Mart";
		String startDateTime = "20/08/2018 12:00:00";
		String endDateTime = "20/08/2018 13:00:00";

		if (args.length == 4) {
			// use command line inputs
			inputFile = args[0];
			merchant = args[1];
			startDateTime = args[2];
			endDateTime = args[3];

		} else {
			// accept argument from command line
			/*
			try (Scanner in = new Scanner(System.in)) {
				System.out.println("Enter input file with complete path:");
				inputFile = in.nextLine();
				System.out.println("Enter merchant name:");
				merchant = in.nextLine();
				System.out.println("Enter start date & time :");
				startDateTime = in.nextLine();
				System.out.println("Enter end date & time :");
				endDateTime = in.nextLine();
			}
			*/
		}

		Collection<Transaction> transactions = loader.loadTransactions(inputFile);
		DoubleSummaryStatistics dss = transactionAnalyser.analyzeTransactions(transactions, merchant, startDateTime,
				endDateTime);
		System.out.println(String.format("Number of transactions    = %d",dss.getCount()));
		System.out.println(String.format("Average Transaction Value = %.2f",dss.getAverage()));
		System.exit(0);

	}

}
