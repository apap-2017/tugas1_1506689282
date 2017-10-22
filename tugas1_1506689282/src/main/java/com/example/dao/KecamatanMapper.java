package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;

@Mapper
public interface KecamatanMapper {
	@Select("SELECT * FROM kecamatan WHERE id = #{idKecamatan}")
	@Results(value = {
		@Result(property="idKecamatan", column="id"),
		@Result(property="idKota", column="id_kota"),
		@Result(property="kodeKecamatan", column="kode_kecamatan"),
		@Result(property="namaKecamatan", column="nama_kecamatan")
	})
	KecamatanModel selectKecamatanId(@Param("idKecamatan") int idKecamatan);
	
	@Select("SELECT * FROM kecamatan WHERE kecamatan.id_kota = #{idKota}")
	@Results(value = {
			@Result(property="idKecamatan", column="id"),
			@Result(property="idKota", column="id_kota"),
			@Result(property="kodeKecamatan", column="kode_kecamatan"),
			@Result(property="namaKecamatan", column="nama_kecamatan")
		})
    List<KecamatanModel> selectListKecamatanByKota(@Param("idKota") int idKota); 
}
