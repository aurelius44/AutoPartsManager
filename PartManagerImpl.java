package edu.disease.midterm;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * The purpose of this class is into implement the
 * methods defined in the interface. THey will read
 * the parts list from the "bom.json" file, convert it
 * to a new gson format, indentify the cost of parts,
 * gather part cost information for part made of 
 * subcomponents, get final assemblies, get purchased
 * parts by their price, and retrieve parts.
 * 
 */
public class PartManagerImpl implements PartManager {

	private Map<String, Part> partStore;
	
	public PartManagerImpl() {
		partStore = new HashMap<>();
	}
	
	/**
	 * This method reads the data from the
	 * bom.json file into a string, converts
	 * the data into an array, scans the array
	 * and saves it into a key/value collection,
	 * and return the number of parts imported
	 * from the file.
	 */
	@Override
	public int importPartStore(String filePath) {
		
		
		int imported = 0;
		
		try {
			String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
			Gson gson = new Gson();
			Part[] parts = gson.fromJson(jsonData, Part[].class);
			
			for (Part part : parts) {
				partStore.put(part.getPartNumber(), part);
				imported++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imported;
	}
	
	/** This method takes the information from
	 * the next two methods and uses it to find the
	 * cost of the part.
	 * 
	 */
	@Override
	public Part costPart(String partNumber) {
		Part part = partStore.get(partNumber);
		
		if (part == null) {
			return null;
		}
		
		float cost = calculatePartCost(part);
		
		cost = roundForMoney(cost);
		
		part.setPrice(cost);
		
		return part;
	}
	
	/**
	 * This method calculates the manufacturing
	 * cost of a part by identifying it by its
	 * part number, while also calculating the 
	 * cost of subcomponents.
	 * 
	 */
	private float calculatePartCost(Part part) {
	    if (part.getPrice() > 0) {
	        return part.getPrice();
	    } else {
	        float cost = 0.0f;
	        if (part.getBillOfMaterial() != null) {
	            for (BomEntry bomEntry : part.getBillOfMaterial()) {
	                Part subPart = partStore.get(bomEntry.getPartNumber());
	                if (subPart != null) {
	                    float subPartCost = calculatePartCost(subPart);
	                    cost += subPartCost * bomEntry.getQuantity();
	                }
	            }
	        }

	        return cost;
	    }
	}
	
	/**
	 * This method rounds the cost to
	 * two decimal places.
	 * 
	 * @param value
	 * @return
	 */
	private float roundForMoney(Float value) {
	    return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).floatValue();
	}
	
	/**
	 * This method retruns a part after indentifying
	 * it by its part number.
	 */
	@Override
	public Part retrievePart(String partNumber) {
		return partStore.get(partNumber);
	}
	
	/**
	 * This method cans all the parts from the
	 * "bom.json" file and returns only parts
	 * that have been assembled.
	 * 
	 */
	@Override
	public List<Part> getFinalAssemblies(){
		List<Part> finalAssemblies = partStore.values().stream()
				.filter(part -> "ASSEMBLY".equals(part.getPartType()))
				.sorted((part1, part2) -> part1.getPartNumber().compareTo(part2.getPartNumber()))
				.collect(Collectors.toList());
		
		return finalAssemblies;
	}
	
	/**
	 * This method scan the parts from the
	 * "bom.jon" file that have the type of
	 * "PURCHASE."
	 * 
	 */
	@Override
	public List<Part> getPurchasedPartsByPrice(){
		List<Part> purchasedParts = partStore.values().stream()
				.filter(part -> "PURCHASE".equals(part.getPartType()))
				.sorted((part1, part2) -> Float.compare(part2.getPrice(), part1.getPrice()))
				.collect(Collectors.toList());
		
		return purchasedParts;
	}
}
