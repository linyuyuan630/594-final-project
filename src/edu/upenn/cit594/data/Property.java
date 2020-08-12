package edu.upenn.cit594.data;

public class Property {
	private int marketValue;
	private int totalLivableArea;
	private String zipCode; //zip code shouldn't be int....
	
	public Property(int marketValue, int totalLiverableArea, String zipCode) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLiverableArea;
		this.zipCode = zipCode;
		
	}
	
	
	public int getMarketValue() {
		return -1;
	}
	
	public int getTotalLiverableArea() {
		return -1;
	}
	
	public String getZipCode() {
		return null;
	}

}
