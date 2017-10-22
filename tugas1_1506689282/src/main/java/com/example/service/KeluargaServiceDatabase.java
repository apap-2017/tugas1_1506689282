package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
	private KeluargaMapper keluargaMapper;
	
	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		keluargaMapper.addKeluarga(keluarga);
	}
	
	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		keluargaMapper.updateKeluarga(keluarga);
	}
	
	@Override
	public KeluargaModel selectKeluarga(String nomorKk) {
		return keluargaMapper.selectKeluarga(nomorKk);
	}
	
	@Override
	public KeluargaModel selectKeluargaId(int idKeluarga) {
		return keluargaMapper.selectKeluargaId(idKeluarga);
	}
	
	@Override
	public String selectKeluargaMiripNKK(String nkkPart, int idKeluarga) {
		return keluargaMapper.selectKeluargaMiripNKK(nkkPart, idKeluarga);
	}
	
	@Override
	public void setBerlaku(int isTidakBerlaku, int idKeluarga) {
		keluargaMapper.setBerlaku(isTidakBerlaku, idKeluarga);
	}
	
	@Override
	public List<KeluargaModel> selectListKeluargaByKelurahan(int idKelurahan) {
		return keluargaMapper.selectListKeluargaByKelurahan(idKelurahan);
	}
}
