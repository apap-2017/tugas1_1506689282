package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.model.KecamatanModel;

@Service
public interface KecamatanService {
	KecamatanModel selectKecamatanId(@Param("idKecamatan") int idKecamatan);
	
	List<KecamatanModel> selectListKecamatanByKota(int idKota);
}
