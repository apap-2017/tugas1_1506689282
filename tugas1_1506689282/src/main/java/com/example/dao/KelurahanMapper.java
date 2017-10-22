package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	@Select("SELECT * FROM kelurahan WHERE id = #{idKelurahan}")
	@Results(value = {
		@Result(property="idKelurahan", column="id"),
		@Result(property="idKecamatan", column="id_kecamatan"),
		@Result(property="kodeKelurahan", column="kode_kelurahan"),
		@Result(property="namaKelurahan", column="nama_kelurahan"),
		@Result(property="kodePos", column="kode_pos")
/*		,@Result(property="listKeluarga", column="id", 
	        javaType = List.class,
	        many=@Many(select="selectListKeluarga"))*/
	})
	KelurahanModel selectKelurahanId(@Param("idKelurahan") int idKelurahan);
	
	@Select("SELECT * FROM kelurahan WHERE kelurahan.id_kecamatan = #{idKecamatan}")
	@Results(value = {
			@Result(property="idKelurahan", column="id"),
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos")
		})
    List<KelurahanModel> selectListKelurahanByKecamatan(@Param("idKecamatan") int idKecamatan); 
}
