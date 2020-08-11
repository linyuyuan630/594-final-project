package edu.upenn.cit594.data;

/**
 * 
 * @author yuyuan.lin
 * Each property entry is an instance of PropertyData
 */
public class PropertyData {
	private String marketValue;
	private String totalLivableArea;
	private String zipCode;
	
	public PropertyData(String marketValue, String totalLivableArea, String zipCode) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLivableArea;
		this.zipCode = zipCode;
	}
	
	
	public String getMarketValue() {
		return marketValue;
		
	}
	
	public String getTotalLiverableArea() {
		return totalLivableArea;
		
	}
	
	public String getZipCode() {
		return zipCode;
	}

}