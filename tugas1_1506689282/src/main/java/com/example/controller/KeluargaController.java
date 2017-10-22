package com.example.controller;

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
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

@Controller
public class KeluargaController {

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
	
	@RequestMapping(value = "/keluarga", method = RequestMethod.GET)
	public String viewKeluarga(Model model, @RequestParam(value = "nkk", required = false) String nkk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nkk);
		
		if(keluarga != null) {
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanId(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanId(kelurahan.getIdKecamatan());
			KotaModel kota = kotaDAO.selectKotaId(kecamatan.getIdKota());
			
			model.addAttribute("listPenduduk", pendudukDAO.selectListPendudukByKeluarga(keluarga.getIdKeluarga()));
			model.addAttribute("keluarga", keluarga);
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kota", kota);
			return "view-keluarga";
		} else {
			model.addAttribute("nkk", nkk);
			return "error-keluarga";
		}
	}
	
	@RequestMapping(value = "/keluarga/tambah")
	public String addKeluarga(@ModelAttribute("keluarga") KeluargaModel keluarga) {
		return "add-keluarga";
	}
	
	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String addKeluargaSubmit(Model model, @ModelAttribute("keluarga") KeluargaModel keluarga, BindingResult result) {
		if(result.hasErrors()) {
			return "error-page";
		} else {
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanId(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanId(kelurahan.getIdKecamatan());
			
			model.addAttribute("nkk", keluarga.resetNkk(kecamatan, pendudukDAO, keluargaDAO));
			keluargaDAO.addKeluarga(keluarga);
			return "success-keluarga";
		}
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}")
	public String updateKeluarga(Model model, @PathVariable(value = "nkk") String nkk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nkk);
		
		if(keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "update-keluarga";
		} else {
			model.addAttribute("nkk", nkk);
			return "error-keluarga-edit";
		}
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.POST)
	public String updateKeluargaSubmit(Model model, @ModelAttribute("keluarga") KeluargaModel keluarga, BindingResult result) {
		if(result.hasErrors()) {
			return "error-page";
		} else {
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanId(keluarga.getIdKelurahan());
			KecamatanModel kecamatan = kecamatanDAO.selectKecamatanId(kelurahan.getIdKecamatan());
			
			model.addAttribute("nkk", keluarga.getNkk());
			keluarga.resetNkk(kecamatan, pendudukDAO, keluargaDAO);
			model.addAttribute("nkkBaru", keluarga.getNkk());
			keluargaDAO.updateKeluarga(keluarga);
			
			return "success-keluarga-edit";
		}
	}
}