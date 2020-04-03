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
		if (loginService.findByUsernameAndPassword(username, password) == true) {
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
		Parkiran par = null;
		try {
			par = (Parkiran) parkirDao.valid(parkir);
		} catch (Exception e) {
		}
		if (parkir.getNoPlat().replaceAll("\\s+", "").toLowerCase().length() > 8) {
			if (parkir.getNoPlat().replaceAll("\\s+", "").toLowerCase().substring(0, 1).equalsIgnoreCase("b")) {
				try {
					Integer.parseInt(parkir.getNoPlat().substring(1, 5));
					if (parkir.getNoPlat().substring(1, 5).length() > 4
							&& parkir.getNoPlat().substring(1, 5).length() >= 1) {
						try {
							Integer.parseInt(parkir.getNoPlat().substring(5, 8));
							return false;
						} catch (Exception e) {
							if (par != null) {
								if (par.getNoPlat().toLowerCase().equals(parkir.getNoPlat().toLowerCase())
										&& par.getTanggalMasuk().equals(parkir.getTanggalMasuk())) {
									return false;
								} else {
									return true;
								}
							} else {
								return false;
							}
						}
					} else {
						return false;
					}
				} catch (Exception e) {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
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
