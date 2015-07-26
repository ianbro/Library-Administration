/**
 * 
 */
package backend;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Ian
 * @date Jul 26, 2015
 * @project library_system
 * @todo TODO
 */
public abstract class Utils {

	public static long getDateDiff(Date date1, Date date2) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return TimeUnit.MILLISECONDS.toDays(diffInMillies);
	}
}
