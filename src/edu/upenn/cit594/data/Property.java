package edu.upenn.cit594.data;

public class Property {
	private Integer marketValue;
	private Integer totalLivableArea;
	private String zipCode; //zip code shouldn't be int....
	
	public Property(Integer marketValue, Integer totalLiverableArea, String zipCode) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLiverableArea;
		this.zipCode = zipCode;
		
	}
	
	
	public Integer getMarketValue() {
		return marketValue;
	}
	
	public Integer getTotalLiverableArea() {
		return totalLivableArea;
	}
	
	public String getZipCode() {
		return zipCode;
	}

}