package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.KeluargaModel;

@Service
public interface KeluargaService {
	
	void addKeluarga(KeluargaModel keluarga);
	
	void updateKeluarga(KeluargaModel keluarga);
	
	KeluargaModel selectKeluarga(String nomorKk);
	
	KeluargaModel selectKeluargaId(int idKeluarga);
	
	String selectKeluargaMiripNKK(String nkkPart, int idKeluarga);
	
	void setBerlaku(int isTidakBerlaku, int idKeluarga);
	
	List<KeluargaModel> selectListKeluargaByKelurahan(int idKelurahan); 
}
