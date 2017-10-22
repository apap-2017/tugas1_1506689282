package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KeluargaModel;

@Mapper
public interface KeluargaMapper {
	
	@Insert("INSERT INTO keluarga (nomor_kk, alamat, rt, rw, id_kelurahan) "
			   + "VALUES (#{nkk}, #{alamat}, #{rt}, #{rw}, #{idKelurahan})")
	void addKeluarga(KeluargaModel keluarga);
	
	@Update("UPDATE keluarga SET nomor_kk=#{nkk}, alamat=#{alamat}, "
			+ "RT=#{rt}, RW=#{rw},id_kelurahan=#{idKelurahan}, "
			+ "is_tidak_berlaku=#{isTidakBerlaku} "
			+ "WHERE id=#{idKeluarga}")
	void updateKeluarga(KeluargaModel keluarga);
	
	@Select("SELECT * FROM keluarga WHERE nomor_kk = #{nkk}")
	@Results(value = {
		@Result(property="idKeluarga", column="id"),
		@Result(property="nkk", column="nomor_kk"),
		@Result(property="alamat", column="alamat"),
		@Result(property="rt", column="RT"),
		@Result(property="rw", column="RW"),
		@Result(property="idKelurahan", column="id_kelurahan"),
		@Result(property="isTidakBerlaku", column="is_tidak_berlaku")
		/*,@Result(property="listPenduduk", column="id", 
	        javaType = List.class,
	        many=@Many(select="selectAnggotaKeluarga"))*/
	})
	KeluargaModel selectKeluarga(@Param("nkk") String nkk);
	
	@Select("SELECT * FROM keluarga WHERE id = #{idKeluarga}")
	@Results(value = {
		@Result(property="idKeluarga", column="id"),
		@Result(property="nkk", column="nomor_kk"),
		@Result(property="alamat", column="alamat"),
		@Result(property="rt", column="RT"),
		@Result(property="rw", column="RW"),
		@Result(property="idKelurahan", column="id_kelurahan"),
		@Result(property="isTidakBerlaku", column="is_tidak_berlaku")
	})
	KeluargaModel selectKeluargaId(@Param("idKeluarga") int idKeluarga);
	
	@Select("SELECT * FROM keluarga WHERE keluarga.id_kelurahan = #{idKelurahan}")
	@Results(value = {
			@Result(property="idKeluarga", column="id"),
			@Result(property="nkk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rt", column="RT"),
			@Result(property="rw", column="RW"),
			@Result(property="idKelurahan", column="id_kelurahan"),
			@Result(property="isTidakBerlaku", column="is_tidak_berlaku")
		})
    List<KeluargaModel> selectListKeluargaByKelurahan(@Param("idKelurahan") int idKelurahan); 
	
	@Update("UPDATE keluarga SET is_tidak_berlaku=#{isTidakBerlaku} WHERE id=#{idKeluarga}")
	void setBerlaku(@Param("isTidakBerlaku") int isTidakBerlaku, @Param("idKeluarga") int idKeluarga);
	
	@Select("SELECT nomor_kk FROM keluarga WHERE nomor_kk LIKE #{nkkPart} AND id != #{idKeluarga} ORDER BY nomor_kk DESC LIMIT 1")
	String selectKeluargaMiripNKK(@Param("nkkPart") String nkkPart, @Param("idKeluarga") int idKeluarga);
	
}
