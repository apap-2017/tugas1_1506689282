package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.KelurahanModel;

@Service
public interface KelurahanService {
	KelurahanModel selectKelurahanId(int idKelurahan);
	
	List<KelurahanModel> selectListKelurahanByKecamatan(int idKecamatan); 
}
