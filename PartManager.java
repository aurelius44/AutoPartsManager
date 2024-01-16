package edu.disease.midterm;

import java.util.List;

/**
 * The purpose of this interface is to contain
 * methods that will be implemented elsewhere.
 */
public interface PartManager {

	/**
	 * Method imports the part store from external file,
	 * accept the file path to the correct file, and then
	 * return the number of parts imported.
	 * 
	 * @param filePath
	 * @return
	 */
	int importPartStore(String filePath);
	
	/**
	 * Method computes manufacturing cost of the part
	 * and accepts a art number to find the cost.
	 * 
	 * @param partNumber
	 * @return
	 */
	Part costPart(String partNumber);
	
	/**
	 * Method retrieves part from the part store, accepts
	 * a part number, and returns the Part instance or null
	 * if not found.
	 * 
	 * @param partNumber
	 * @return
	 */
	Part retrievePart(String partNumber);
	
	/**
	 * Method returns all final assembly parts
	 * alphabetically sorted by part number.
	 * 
	 * @return
	 */
	List<Part> getFinalAssemblies();
	
	/**
	 * Method returns all purchased parts with
	 * the type "PURCHASE" from highest to 
	 * lowest price.
	 * 
	 * @return
	 */
	List<Part> getPurchasedPartsByPrice();
}
