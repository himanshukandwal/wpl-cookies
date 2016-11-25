package edu.utdallas.wpl.cookies.spring.dao.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
//@Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
public class Car {

	@Id
	
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(name = "address_seq", sequenceName = "address_sequence")
	private Long id;

	@Column
	@Field(store = Store.YES)
	private String make;

	@Column
	@Field(store = Store.YES)
	private String model;

	@Column
	@Field(store = Store.YES)
	private String year;

	@Column
	@Field(store = Store.NO)
	private String description;

	public Car() {
	}

	public Car(String make, String model, String year, String description) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.description = description;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
