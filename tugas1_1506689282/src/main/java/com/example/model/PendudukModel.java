package com.example.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.service.PendudukService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel implements Comparable<PendudukModel> {
	private int idPenduduk;
	private String nik;
	private String namaPenduduk;
	private String tempatLahir;
	private String tanggalLahir;
	private int jenisKelamin;
	private int isWni;
	private String agama;
	private String pekerjaan;
	private String statusPerkawinan;
	private String statusDalamKeluarga;
	private String golonganDarah;
	private int isWafat;
	private int idKeluarga;
	
	public String getKewarganegaraan() {
		if(isWni == 1) {
			return "WNI";
		} return "WNA";
	}
	
	public String getKematian() {
		if(isWafat == 1) {
			return "Wafat";
		} return "Hidup";
	}
	
	public String getGender() {
		if(jenisKelamin == 1) {
			return "Perempuan";
		} return "Laki-laki";
	}
	
	@Override
	public int compareTo(PendudukModel otherPenduduk) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date thisDate = format.parse(getTanggalLahir());
			Date otherDate = format.parse(otherPenduduk.getTanggalLahir());
			return thisDate.compareTo(otherDate);
		} catch (Exception e) {
			
		}
		return 0;
	}
	
	public String resetNik(KecamatanModel kecamatan, PendudukService pendudukDAO) {		
		String nikMirip;
		String[] tanggalSplit = getTanggalLahir().split("-");
		String tanggalLahir = "" + tanggalSplit[2];
		String bulanLahir = "" + tanggalSplit[1];
		String tahunLahir = "" + tanggalSplit[0];
		String tmp;
		
		String nik = "";
		String kodeKecamatan = kecamatan.getKodeKecamatan();
		nik += kodeKecamatan.substring(0, kodeKecamatan.length() - 1);
		
		if(getJenisKelamin() == 1) {
			tanggalLahir = (Integer.parseInt(tanggalLahir) + 40) + "";
		} 
		
		if(tanggalLahir.length() == 1) {
			nik += "0";
		} 
		
		nik += tanggalLahir;
		
		if(bulanLahir.length() == 1) {
			nik += "0";
		}
		
		nik += bulanLahir + tahunLahir.substring(2);
		
		nikMirip = pendudukDAO.selectPendudukMiripNIK(nik + '%', getIdPenduduk());
		
		if(nikMirip == null) {
			nik += "0001";
		} else {
			tmp = (Integer.parseInt(nikMirip.substring(12)) + 1) + "";
			if(tmp.length() == 1) {
				nik += "000" + tmp;
			} else if(tmp.length() == 2) {
				nik += "00" + tmp;
			} else if(tmp.length() == 3) {
				nik += "0" + tmp;
			} else {
				nik += tmp;
			}
		}
		
		setNik(nik);
		return nik;
	}
}
