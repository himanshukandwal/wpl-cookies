package edu.utdallas.wpl.cookies.spring.common.enums;

public enum ApartmentType {

	BHK_1 ("1 BHK"),
	BHK_2 ("2 BHK"),
    BHK_3 ("3 BHK"),
    HOUSE("house");
	private String apartment;
	private ApartmentType(String apartmentType){
		this.apartment=apartmentType;
	}
	public String getApartment() {
		return apartment;
	}
	
	
	
	

}

