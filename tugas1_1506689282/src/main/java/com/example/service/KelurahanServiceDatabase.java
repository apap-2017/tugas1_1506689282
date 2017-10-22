package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

@Service
public class KelurahanServiceDatabase implements KelurahanService {
	@Autowired
	private KelurahanMapper kelurahanMapper;
	
	@Override
	public KelurahanModel selectKelurahanId(int idKelurahan) {
		return kelurahanMapper.selectKelurahanId(idKelurahan);
	}
	
	@Override
	public List<KelurahanModel> selectListKelurahanByKecamatan(@Param("idKecamatan") int idKecamatan) {
		return kelurahanMapper.selectListKelurahanByKecamatan(idKecamatan);
	}
}
