package com.neoris.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.mvc.models.MVCInputData;

public interface IMVCInputDataDao extends JpaRepository<MVCInputData, String>{

}
