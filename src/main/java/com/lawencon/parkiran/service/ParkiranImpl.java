package com.lawencon.parkiran.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.parkiran.dao.ParkiranDao;
import com.lawencon.parkiran.model.Parkiran;

@Service
@Transactional
public class ParkiranImpl implements ParkiranService {

	@Autowired
	@Qualifier("parkir_repo_hibernate")
	private ParkiranDao parkirDao;

	@Autowired
	private LoginService loginService;

	@Override
	public List<Parkiran> findAll(String username, String password) throws Exception {
		if (loginService.findByUsernameAndPassword(username, password) == false) {
			System.out.println("User tidak valid");
			return null;
		} else {
			return parkirDao.findAll();
		}
	}

	@Override
	public Parkiran insert(Parkiran parkir, String username, String password) throws Exception {
		if (loginService.findByUsernameAndPassword(username, password) == false) {
			if (valid(parkir) == false) {
				System.out.println("gagal insert");
				return null;
			} else {
				return parkirDao.insert(parkir, username, password);
			}
		} else {
			System.out.println("User tidak valid");
			return null;
		}
	}

	@Override
	public Parkiran update(int id, String tanggal, String username, String password) throws Exception {
		if (loginService.findByUsernameAndPassword(username, password) == false) {
			System.out.println("User tidak valid");
			return null;
		} else {
			return parkirDao.update(id, tanggal);
		}

	}

	@Override
	public List<Parkiran> findByJenisKendaraan(String jenisKendaraan, String username, String password)
			throws Exception {
		if (loginService.findByUsernameAndPassword(username, password) == false) {
			System.out.println("User tidak valid");
			return null;
		} else {
			return parkirDao.findByJenisKendaraan(jenisKendaraan);
		}
	}

	@Override
	public Boolean valid(Parkiran parkir) throws Exception {
		return parkirDao.valid(parkir);
	}

	@Override
	public List<Parkiran> findByTanggalKeluar(String password, String username) throws Exception {
		if (loginService.findByUsernameAndPassword(username, password) == false) {
			System.out.println("User tidak valid");
			return null;
		} else {
			return parkirDao.findAllByTanggalKeluar();
		}
	}
}
