package org.deb.suite;

import org.deb.analysis.TransactionAnalysisTest;
import org.deb.analysis.impl.TransactionAnalysisImplTest;
import org.deb.loader.CSVLoaderImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TransactionAnalysisTest.class,
	TransactionAnalysisImplTest.class,
	CSVLoaderImplTest.class
})
public class AllTests {

}
