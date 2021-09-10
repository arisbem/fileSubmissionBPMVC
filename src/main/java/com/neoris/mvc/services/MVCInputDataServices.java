package com.neoris.mvc.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.mvc.dao.IMVCInputDataDao;
import com.neoris.mvc.models.MVCInputData;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class MVCInputDataServices implements IMVCInputDataServices{

	
	@Autowired
	private IMVCInputDataDao data;
	
	@Override
	public List<com.neoris.mvc.models.MVCInputData> findAll() {
		return (List<MVCInputData>) data.findAll();
	}

	@Override
	public Optional<com.neoris.mvc.models.MVCInputData> findById(String id) {
		
		return data.findById(id);
	}

}
