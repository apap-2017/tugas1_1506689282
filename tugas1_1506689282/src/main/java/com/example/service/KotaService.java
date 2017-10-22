package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.model.KotaModel;

@Service
public interface KotaService {
	List<KotaModel> selectKotaAll();
	
	KotaModel selectKotaId(@Param("idKota") int idKota);
	
}
