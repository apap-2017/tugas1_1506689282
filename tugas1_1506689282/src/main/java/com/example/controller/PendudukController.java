package com.example.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

@Controller
public class PendudukController {

	@Autowired
	PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KotaService kotaDAO;

	@RequestMapping(value = "/penduduk", method = RequestMethod.GET)
	public String viewPenduduk(Model model, @RequestParam(value = "nik", required = false) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		
		if (penduduk != null) {
			KeluargaModel keluarga = keluargaDAO.selectKeluargaId(penduduk.getIdKeluarga());
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanId(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanId(kelurahan.getIdKecamatan());
			KotaModel kota = kotaDAO.selectKotaId(kecamatan.getIdKota());
			
			model.addAttribute("penduduk", penduduk);
			model.addAttribute("keluarga", keluarga);
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kota", kota);
			return "view-penduduk";
		} else {
			model.addAttribute("nik", nik);
			return "error-penduduk";
		}
	}
	
	@RequestMapping(value = "/penduduk/tambah")
	public String addPenduduk(@ModelAttribute("penduduk") PendudukModel penduduk) {
		return "add-penduduk";
	}
	
	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
	public String addPendudukSubmit(Model model, @ModelAttribute("penduduk") PendudukModel penduduk, BindingResult result) {
		if(result.hasErrors()) {
			return "error-page";
		} else {
			KeluargaModel keluarga = keluargaDAO.selectKeluargaId(penduduk.getIdKeluarga());
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanId(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanId(kelurahan.getIdKecamatan());	
		
			model.addAttribute("nik", penduduk.resetNik(kecamatan, pendudukDAO));
			pendudukDAO.addPenduduk(penduduk);
			return "success-penduduk";
		}
	}
	
	
	@RequestMapping(value = "/penduduk/ubah/{nik}")
	public String updatePenduduk(@PathVariable(value="nik") String nik, Model model) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		
		if(penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			return "update-penduduk";
		} else {
			model.addAttribute("nik", nik);
			return "error-penduduk-edit";
		}
	}
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String updatePendudukSubmit(Model model, @ModelAttribute("penduduk") PendudukModel penduduk, BindingResult result) {
		if(result.hasErrors()) {
			return "error-page";
		} else {
			KeluargaModel keluarga = keluargaDAO.selectKeluargaId(penduduk.getIdKeluarga());
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanId(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanId(kelurahan.getIdKecamatan());	
		
			model.addAttribute("nik", penduduk.getNik());
			penduduk.resetNik(kecamatan, pendudukDAO);
			model.addAttribute("nikBaru", penduduk.getNik());
			
			pendudukDAO.updatePenduduk(penduduk);
			
			keluarga.checkBerlaku(pendudukDAO, keluargaDAO);
			keluargaDAO.setBerlaku(keluarga.getIsTidakBerlaku(), keluarga.getIdKeluarga());
			
			return "success-penduduk-edit";
		}
	}
	
	@RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String wafatPendudukSubmit(Model model,
		@RequestParam(value = "isWafat", required = false) Integer isWafat, 
		@RequestParam(value = "idPenduduk", required = false) Integer idPenduduk,
		@RequestParam(value = "idKeluarga", required = false) Integer idKeluarga,
		@RequestParam(value = "nik", required = false) String nik) {
		
		if(isWafat != null && idPenduduk != null) {
			pendudukDAO.wafatPenduduk(isWafat, idPenduduk);
			
			KeluargaModel keluarga = keluargaDAO.selectKeluargaId(idKeluarga.intValue());
			keluarga.checkBerlaku(pendudukDAO, keluargaDAO);
			keluargaDAO.setBerlaku(keluarga.getIsTidakBerlaku(), idKeluarga);
			
			model.addAttribute("nik", nik);
			model.addAttribute("isWafat", isWafat);
			return "success-mati";
		} else {
			return "error-page";
		}
	}
	
	@RequestMapping(value = "/penduduk/cari", method = RequestMethod.GET)
	public String searchPendudukKota(Model model, 
			@RequestParam(value = "kt", required = false) Integer idKota,
			@RequestParam(value = "kc", required = false) Integer idKecamatan,
			@RequestParam(value = "kl", required = false) Integer idKelurahan) {
		
			if(idKota != null && idKecamatan != null && idKelurahan !=null) {
				model.addAttribute("kelurahan", kelurahanDAO.selectKelurahanId(idKelurahan));
				List<PendudukModel> listPenduduk = pendudukDAO.selectListPendudukByKelurahan(idKelurahan);
				model.addAttribute("listPenduduk", listPenduduk);
				
				Collections.sort(listPenduduk);
				PendudukModel pendudukTermuda = listPenduduk.get(listPenduduk.size() - 1);
				PendudukModel pendudukTertua = listPenduduk.get(0);
				
				model.addAttribute("pendudukTermuda", pendudukTermuda);
				model.addAttribute("pendudukTertua", pendudukTertua);
				
				return "search-penduduk-table";
			} else if (idKota !=null && idKecamatan != null) {
				model.addAttribute("kota", kotaDAO.selectKotaId(idKota));
				model.addAttribute("kecamatan", kecamatanDAO.selectKecamatanId(idKecamatan));
				model.addAttribute("listKelurahan", kelurahanDAO.selectListKelurahanByKecamatan(idKecamatan));
				
				return "search-penduduk-kelurahan";
			} else if (idKota !=null) {
				model.addAttribute("kota", kotaDAO.selectKotaId(idKota));
				model.addAttribute("listKecamatan", kecamatanDAO.selectListKecamatanByKota(idKota));
				
				return "search-penduduk-kecamatan";
			} else {
				model.addAttribute("listKota", kotaDAO.selectKotaAll());
				return "search-penduduk-kota";
			}
		
	}
	
}