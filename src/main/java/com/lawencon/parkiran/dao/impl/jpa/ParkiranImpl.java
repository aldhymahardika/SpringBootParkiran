package com.lawencon.parkiran.dao.impl.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.dao.ParkiranDao;
import com.lawencon.parkiran.model.Parkiran;

@Repository("parkir_repo_jpa")
public class ParkiranImpl implements ParkiranDao {

	@Autowired
	private ParkiranRepo parkirRepo;

	@Override
	public List<Parkiran> findAllByTanggalKeluar() throws Exception {
		return parkirRepo.findAllByTanggalKeluar();
	}

	@Override
	public Parkiran insert(Parkiran parkir, String username, String password) throws Exception {
		return parkirRepo.save(parkir);
	}

	@Override
	public Parkiran update(int id, String tanggal) throws Exception {
		Parkiran parkir = new Parkiran();
		parkir.setId(id);
		parkir.setTanggalKeluar(tanggal);
		return parkirRepo.save(parkir);
	}

	@Override
	public List<Parkiran> valid(Parkiran parkir) throws Exception {
		return parkirRepo.valid(parkir.getNoPlat());
	}

	@Override
	public List<Parkiran> findAll() throws Exception {
		return parkirRepo.findAll();
	}

	@Override
	public List<Parkiran> findByJenisKendaraan(String jenisKendaraan) throws Exception {
		return parkirRepo.findByJenisKendaraan(jenisKendaraan);
	}

}
