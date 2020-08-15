package edu.upenn.cit594.data;

public class Property {
	private Double marketValue;
	private Integer totalLivableArea;
	private String zipCode; 
	
	public Property(Double marketValue, Integer totalLiverableArea, String zipCode) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLiverableArea;
		this.zipCode = zipCode;
		
	}
	
	
	public Double getMarketValue() {
		return marketValue;
	}
	
	public Integer getTotalLiverableArea() {
		return totalLivableArea;
	}
	
	public String getZipCode() {
		return zipCode;
	}

}