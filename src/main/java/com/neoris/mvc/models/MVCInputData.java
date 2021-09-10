package com.neoris.mvc.models;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Subselect;


@Entity
@Subselect(value = "select code,name " + 
		"from SCHOPERATIONS.TCFGACCOUNTINGCOMPANY " + 
		"where countryid in (16,237,238,239)")
public class MVCInputData implements Serializable {

	private static final long serialVersionUID = 1717875553545677091L;
	
	@Id
	private String code;
	
	private String name;
	@Transient
	private String vyear;
	@Transient
	private String vmonth;
	@Transient
	private String subledger;

	
	public MVCInputData(){
		
	}
	



	public MVCInputData(String code, String name, String vyear, String vmonth, String subledger) {
		
		this.code = code;
		this.name = name;
		this.vyear = vyear;
		this.vmonth = vmonth;
		this.subledger = subledger;
	}




	public String getSubledger() {
		return subledger;
	}




	public void setSubledger(String subledger) {
		this.subledger = subledger;
	}




	public String getVyear() {
		return vyear;
	}




	public void setVyear(String vyear) {
		this.vyear = vyear;
	}




	public String getVmonth() {
		return vmonth;
	}




	public void setVmonth(String vmonth) {
		this.vmonth = vmonth;
	}




	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}




	@Override
	public String toString() {
		return "MVCInputData [code=" + code + ", name=" + name + ", vyear=" + vyear + ", vmonth=" + vmonth
				+ ", subledger=" + subledger + "]";
	}


	
	
	
}
