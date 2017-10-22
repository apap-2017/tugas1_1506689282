package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

@Service
public class PendudukServiceDatabase implements PendudukService {
	@Autowired
    private PendudukMapper pendudukMapper;
	
	@Override
	public void addPenduduk(PendudukModel penduduk) {
		pendudukMapper.addPenduduk(penduduk);
	}
	
	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		pendudukMapper.updatePenduduk(penduduk);
	}
	
	@Override
	public PendudukModel selectPenduduk(String nik) {
		return pendudukMapper.selectPenduduk(nik);
	}
	
	@Override
	public String selectPendudukMiripNIK(String nikPart, int idPenduduk) {
		return pendudukMapper.selectPendudukMiripNIK(nikPart, idPenduduk);
	}
	
	@Override
	public void wafatPenduduk(int isWafat, int idPenduduk) {
		pendudukMapper.wafatPenduduk(isWafat, idPenduduk);
	}
	
	@Override
	public List<PendudukModel> selectListPendudukByKeluarga(int idKeluarga) {
		return pendudukMapper.selectListPendudukByKeluarga(idKeluarga);
	}
	
	@Override
	public List<PendudukModel> selectListPendudukByKelurahan(int idKelurahan) {
		return pendudukMapper.selectListPendudukByKelurahan(idKelurahan);
	}
}
