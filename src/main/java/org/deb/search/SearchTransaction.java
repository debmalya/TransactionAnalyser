/**
 * 
 */
package org.deb.search;

import org.deb.model.Transaction;

/**
 * @author debmalyajash
 *
 */
public interface SearchTransaction {
	/**
	 * 
	 * @param t transaction criteria to search.
	 * @return true if matched, false otherwise.
	 */
	 boolean search(Transaction t);
}
