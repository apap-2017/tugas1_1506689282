package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	private int idKelurahan;
	private int idKecamatan;
	private String kodeKelurahan;
	private String namaKelurahan;
	private String kodePos;
}
