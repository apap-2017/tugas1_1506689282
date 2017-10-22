package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, "
			+ "is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, "
			+ "golongan_darah, is_wafat) VALUES (#{nik}, #{namaPenduduk}, #{tempatLahir}, "
			+ "#{tanggalLahir}, #{jenisKelamin}, #{isWni}, #{idKeluarga}, "
			+ "#{agama}, #{pekerjaan}, #{statusPerkawinan}, #{statusDalamKeluarga},"
			+ " #{golonganDarah}, #{isWafat})")
	void addPenduduk(PendudukModel penduduk);
	
	@Update("UPDATE penduduk SET nik=#{nik}, nama=#{namaPenduduk}, "
			+ "tempat_lahir=#{tempatLahir}, tanggal_lahir=#{tanggalLahir}, "
			+ "jenis_kelamin=#{jenisKelamin}, is_wni=#{isWni}, "
			+ "id_keluarga=#{idKeluarga}, agama=#{agama}, pekerjaan=#{pekerjaan}, "
			+ "status_perkawinan=#{statusPerkawinan}, "
			+ "status_dalam_keluarga=#{statusDalamKeluarga}, "
			+ "golongan_darah=#{golonganDarah}, is_wafat=#{isWafat} "
			+ "WHERE id=#{idPenduduk}")
	void updatePenduduk(PendudukModel penduduk);
	
	@Select("SELECT * FROM penduduk WHERE nik = #{nik}")
	@Results(value = {
		@Result(property="idPenduduk", column="id"),
		@Result(property="nik", column="nik"),
		@Result(property="namaPenduduk", column="nama"),
		@Result(property="tempatLahir", column="tempat_lahir"),
		@Result(property="tanggalLahir", column="tanggal_lahir"),
		@Result(property="jenisKelamin", column="jenis_kelamin"),
		@Result(property="isWni", column="is_wni"),
		@Result(property="idKeluarga", column="id_keluarga"),
		@Result(property="agama", column="agama"),
		@Result(property="pekerjaan", column="pekerjaan"),
		@Result(property="statusPerkawinan", column="status_perkawinan"),
		@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
		@Result(property="golonganDarah", column="golongan_darah"),
		@Result(property="isWafat", column="is_wafat")
	})
	PendudukModel selectPenduduk(@Param("nik") String nik);
	
	@Select("SELECT nik FROM penduduk WHERE nik LIKE #{nikPart} AND id != #{idPenduduk} ORDER BY nik DESC LIMIT 1")
	String selectPendudukMiripNIK(@Param("nikPart") String nikPart, @Param("idPenduduk") int idPenduduk);
	
	@Update("UPDATE penduduk SET is_wafat=#{isWafat} WHERE id=#{idPenduduk}")
	void wafatPenduduk(@Param("isWafat") int isWafat, @Param("idPenduduk") int idPenduduk);
	
	@Select("SELECT * FROM penduduk WHERE penduduk.id_keluarga = #{idKeluarga}")
	@Results(value = {
			@Result(property="idPenduduk", column="id"),
			@Result(property="nik", column="nik"),
			@Result(property="namaPenduduk", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat")
		})
    List<PendudukModel> selectListPendudukByKeluarga(@Param("idKeluarga") int idKeluarga);
	
	@Select("SELECT penduduk.* FROM penduduk WHERE penduduk.id_keluarga IN (SELECT keluarga.id FROM keluarga WHERE keluarga.id_kelurahan = #{idKelurahan})")
	@Results(value = {
			@Result(property="idPenduduk", column="id"),
			@Result(property="nik", column="nik"),
			@Result(property="namaPenduduk", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat")
		})
	List<PendudukModel> selectListPendudukByKelurahan(@Param("idKelurahan") int idKelurahan); 
	
}
