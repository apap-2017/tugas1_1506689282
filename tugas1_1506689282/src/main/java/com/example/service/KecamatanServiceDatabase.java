package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.model.KecamatanModel;

@Service
public class KecamatanServiceDatabase implements KecamatanService {
	@Autowired
	KecamatanMapper kecamatanMapper;
	
	@Override
	public KecamatanModel selectKecamatanId(@Param("idKecamatan") int idKecamatan) {
		return kecamatanMapper.selectKecamatanId(idKecamatan);
	}
	
	@Override
	public List<KecamatanModel> selectListKecamatanByKota(int idKota) {
		return kecamatanMapper.selectListKecamatanByKota(idKota);
	}
}
