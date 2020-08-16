package edu.upenn.cit594.data;

/**
 * Property data object
 * @author zhongliu
 *
 */
public class Property {
	private Double marketValue;
	private Double totalLivableArea;
	private String zipCode; 
	
	public Property(Double marketValue, Double totalLiverableArea, String zipCode) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLiverableArea;
		this.zipCode = zipCode;
	}
	
	
	public Double getMarketValue() {
		return marketValue;
	}
	
	public Double getTotalLiverableArea() {
		return totalLivableArea;
	}
	
	/**
	 * ZIP Code, you should only consider the first five digits of the field, 
	 * e.g. a value of “191047632” or “191042” should be considered the ZIP Code “19104”.
	 * @return
	 */
	public String getZipCode() {
		if(zipCode==null) return null;
		
		if (zipCode.length()<5) {
			zipCode = null;
		}
		else {
			zipCode = zipCode.substring(0, 5);
		}
		return zipCode;
	}

}