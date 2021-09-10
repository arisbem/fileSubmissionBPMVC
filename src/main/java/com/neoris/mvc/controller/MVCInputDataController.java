package com.neoris.mvc.controller;



import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.neoris.mvc.models.MVCInputData;
import com.neoris.mvc.services.IMVCInputDataServices;
import com.neoris.mvc.utils.ViewScope;


@Controller(value = "InputDataControllerBean")
@Scope(ViewScope.VIEW)
public class MVCInputDataController {

private final static Logger LOG = LoggerFactory.getLogger(MVCInputDataController.class);
	
	@Autowired
	private IMVCInputDataServices service;
	private List<MVCInputData> lstData;
	private MVCInputData currentData;
	private String zyear;
	private String zmonth;
	private String vcountry;
	
	@Value("${mvc.iphost}")
	private String iphost;
	
	@Value("${mvc.username}")
	private String mvcusername;
	
	@Value("${mvc.password}")
	private String mvcpassword;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostConstruct
	public void init() {
		LOG.info("Initializing Data");
		this.currentData = new MVCInputData(); 
	    this.lstData = service.findAll();
		
	}
	
	public String getTitle() {
		return "File submission";
	}

	public String getDialogName() {
		return "manageCodeDialog";
	}

	public String getDataTableName() {
		return "dt-codes";
	}

	public List<MVCInputData> getLstData() {
		return lstData;
	}

	public void setLstData(List<MVCInputData> lstData) {
		this.lstData = lstData;
	}

	public MVCInputData getCurrentData() {
		return currentData;
	}

	public void setCurrentData(MVCInputData currentData) {
		this.currentData = currentData;
		LOG.info("*country "+currentData.getCode());
	}
	
	
	public String getZyear() {
		return zyear;
	}

	public void setZyear(String zyear) {
		this.zyear = zyear;
		LOG.info("*year "+zyear);
	}

	public String getZmonth() {
		return zmonth;
	}

	public void setZmonth(String zmonth) {
		this.zmonth = zmonth;
		LOG.info("*zmonth "+zmonth);
	}

	
	public String getVcountry() {
		return vcountry;
	}

	public void setVcountry(String vcountry) {
		this.vcountry = vcountry;
		LOG.info("*vcountry "+vcountry);
	}

	public void submit() {
		
		String plainCreds = this.mvcusername+":"+this.mvcpassword;
		 
		 LOG.info("usernm "+this.mvcusername);
		 byte[] plainCredsBytes = plainCreds.getBytes();
		 Base64 base64 = new Base64();
		 byte[] base64CredsBytes = base64.encodeBase64(plainCredsBytes);
		 String base64Creds = new String(base64CredsBytes);
		
		 LOG.info("base64Creds = {}", base64Creds);		 
		 HttpHeaders headers = new HttpHeaders();
		 ResponseEntity<String> response = null;		
		 HttpEntity<String> request;
		 

		 try {
			 
			 headers.add("Authorization", "Basic " + base64Creds);
			 String url =this.iphost+"?CompanyCode= "+this.vcountry+"&Year="+this.zyear+"&Month="+this.zmonth+"&GroupLedger=Z1" ;
			 LOG.info("url = {}", url);
			 request = new HttpEntity<String>(headers);
			 response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			
			LOG.info(" getStatusCode = {}" , response.getStatusCode());
			LOG.info(" getStatusCodeValue = {}" , response.getStatusCodeValue());
			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succefull! "));
			 
			 
		 } catch (RestClientException e) {
			 
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+response.getStatusCode()));
			
				LOG.info("er getStatusCode = {}" , response.getStatusCode());
				LOG.info("er getStatusCodeValue = {}" , response.getStatusCodeValue());
				
				LOG.error("Submit ERRor  -> {}", e.getMessage(), e);
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error! "+response.getStatusCode()));
				 
			}

	
       
    }
	
		
	
}
