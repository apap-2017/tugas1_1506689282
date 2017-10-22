package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.service.KeluargaService;
import com.example.service.PendudukService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	private int idKeluarga;
	private String nkk;
	private String alamat;
	private String rt;
	private String rw;
	private int idKelurahan;
	private int isTidakBerlaku;
	
	public String resetNkk(KecamatanModel kecamatan, PendudukService pendudukDAO, KeluargaService keluargaDAO) {		
		String nkkMirip;
		String tmp;
		String nkk = "";
		String kodeKecamatan = kecamatan.getKodeKecamatan();
		nkk += kodeKecamatan.substring(0, kodeKecamatan.length() - 1);
		nkk += LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy"));
		
		nkkMirip = keluargaDAO.selectKeluargaMiripNKK(nkk + '%', getIdKeluarga());
		
		if(nkkMirip == null) {
			nkk += "0001";
		} else {
			tmp = (Integer.parseInt(nkkMirip.substring(12)) + 1) + "";
			if(tmp.length() == 1) {
				nkk += "000" + tmp;
			} else if(tmp.length() == 2) {
				nkk += "00" + tmp;
			} else if(tmp.length() == 3) {
				nkk += "0" + tmp;
			} else {
				nkk += tmp;
			}
		}

		resetNikPenduduk(kecamatan, pendudukDAO);
		setNkk(nkk);
		return nkk;
	}
	
	public void resetNikPenduduk(KecamatanModel kecamatan, PendudukService pendudukDAO) {
		for(PendudukModel penduduk : pendudukDAO.selectListPendudukByKeluarga(getIdKeluarga())) {
			penduduk.resetNik(kecamatan, pendudukDAO);
			pendudukDAO.updatePenduduk(penduduk);
		}
	}
	
	public boolean checkBerlaku(PendudukService pendudukDAO, KeluargaService keluargaDAO) {

		for(PendudukModel penduduk : pendudukDAO.selectListPendudukByKeluarga(getIdKeluarga())) {
			if(penduduk.getIsWafat() == 0) {
				setIsTidakBerlaku(0);
				keluargaDAO.setBerlaku(isTidakBerlaku, idKeluarga);
				return true;
			}
		}
		
		setIsTidakBerlaku(1);
		return false;
	}
}
