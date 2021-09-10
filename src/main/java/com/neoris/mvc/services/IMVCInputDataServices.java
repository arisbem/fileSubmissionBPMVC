package com.neoris.mvc.services;

import java.util.List;
import java.util.Optional;

import com.neoris.mvc.models.MVCInputData;

public interface IMVCInputDataServices {

	List<MVCInputData> findAll();
	Optional<MVCInputData> findById(String id);
}
