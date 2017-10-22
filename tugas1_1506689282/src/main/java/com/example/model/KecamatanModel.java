package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel {
	private int idKecamatan;
	private int idKota;
	private String kodeKecamatan;
	private String namaKecamatan;
}
