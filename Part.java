package edu.disease.midterm;

import java.util.List;
import java.util.Objects;

/**
 * The purpose of classes BomEntry and Part are to
 * identify and reference parts and their characteristics
 * in both in the BoM and in the Part class. This is the 
 * Bill of Materials list in the Part class will hold this
 * information, while the BomEntry class will keep the number
 * and the quantity.
 * 
 */
public class Part {
	
	private String partNumber;
	
	private String name;
	
	private String partType;
	
	private float price;
	
	private List<BomEntry> billOfMaterial;

	/**
	 * Getter for partNumber
	 * 
	 * @return
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * Setter for part number
	 * 
	 * @param partNumber
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * Getter for name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for partType
	 * 
	 * @return
	 */
	public String getPartType() {
		return partType;
	}

	/**
	 * Setter for partType
	 * 
	 * @param partType
	 */
	public void setPartType(String partType) {
		this.partType = partType;
	}

	/**
	 * Getter for price
	 * 
	 * @return
	 */
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * Get for BOM List
	 * 
	 * @return
	 */
	public List<BomEntry> getBillOfMaterial() {
		return billOfMaterial;
	}

	/**
	 * Setter for BOM list
	 * 
	 * @param billOfMaterial
	 */
	public void setBillOfMaterial(List<BomEntry> billOfMaterial) {
		this.billOfMaterial = billOfMaterial;
	}

	/**
	 * Hashcode method as specified in
	 * assignment
	 */
	@Override
	public int hashCode() {
		return Objects.hash(partNumber);
	}

	/**
	 * Equals method as specified in
	 * assignment
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		return Objects.equals(partNumber, other.partNumber);
	}

	/**
	 * toString method as specified in
	 * assignment
	 * 
	 */
	@Override
	public String toString() {
		return "Part [partNumber=" + partNumber + ", name=" + name + ", partType=" + partType + ", price=" + price
				+ ", billOfMaterial=" + billOfMaterial + "]";
	}
	
}
