package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.PendudukModel;

@Service
public interface PendudukService {
	void addPenduduk(PendudukModel penduduk);
	
	void updatePenduduk(PendudukModel penduduk);
	
	PendudukModel selectPenduduk(String nik);
	
	String selectPendudukMiripNIK(String nikPart, int idPenduduk);
	
	void wafatPenduduk(int isWafat, int idPenduduk);
	
	List<PendudukModel> selectListPendudukByKeluarga(int idKeluarga);
	
	List<PendudukModel> selectListPendudukByKelurahan(int idKelurahan);
}
