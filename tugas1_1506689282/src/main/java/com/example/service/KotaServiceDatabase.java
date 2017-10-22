package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KotaMapper;
import com.example.model.KotaModel;

@Service
public class KotaServiceDatabase implements KotaService {
	@Autowired
	KotaMapper kotaMapper;
	
	@Override
	public List<KotaModel> selectKotaAll() {
		return kotaMapper.selectKotaAll();
	}
	
	@Override
	public KotaModel selectKotaId(@Param("idKota") int idKota) {
		return kotaMapper.selectKotaId(idKota);
	}
	
}
