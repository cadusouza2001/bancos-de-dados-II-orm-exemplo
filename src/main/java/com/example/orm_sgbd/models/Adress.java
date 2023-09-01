package com.example.orm_sgbd.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adress")
public class Adress implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idAdress;
	
	@Column(nullable = false)
    private String streetName;
	
	@Column(nullable = false)
	private int adressNumber;
	
	private String complement;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String country;

	public UUID getIdAdress() {
		return idAdress;
	}

	public void setIdAdress(UUID idAdress) {
		this.idAdress = idAdress;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getAdressNumber() {
		return adressNumber;
	}

	public void setAdressNumber(int adressNumber) {
		this.adressNumber = adressNumber;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
