package edu.disease.midterm;

import java.util.Objects;

public class BomEntry {
	
	private String partNumber;
	
	private int quantity;
	
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(partNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BomEntry other = (BomEntry) obj;
		return Objects.equals(partNumber, other.partNumber);
	}

	@Override
	public String toString() {
		return "BomEntry [partNumber=" + partNumber + ", quantity=" + quantity + "]";
	}
	
	
}
