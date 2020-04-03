package com.lawencon.parkiran.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.dao.ParkiranDao;
import com.lawencon.parkiran.dao.impl.hibernate.BaseHibernate;
import com.lawencon.parkiran.model.Parkiran;

@Repository("parkir_repo_hibernate")
public class ParkiranImpl extends BaseHibernate implements ParkiranDao {

	@Override
	public List<Parkiran> findAll() throws Exception {
		Query q = em.createQuery(" from Parkiran");
		return q.getResultList();
	}

	@Override
	public Parkiran insert(Parkiran parkir, String username, String password) throws Exception {
		em.persist(parkir);
		return parkir;
	}

	@Override
	public Parkiran update(int id, String tanggal) throws Exception {
		Query q = em.createQuery(" from Parkiran where id = :idParam");
		q.setParameter("idParam", id);
		Parkiran parkir = new Parkiran();
		parkir = (Parkiran) q.getSingleResult();
		parkir.setTanggalKeluar(tanggal);
		em.merge(parkir);
		return parkir;
	}

	@Override
	public List<Parkiran> findByJenisKendaraan(String jenis) throws Exception {
		Query q = em.createQuery(" from Parkiran where jenisKendaraan = :jenisParam");
		q.setParameter("jenisParam", jenis);
		return q.getResultList();
	}

	@Override
	public Boolean valid(Parkiran parkir) throws Exception {
		Parkiran par = null;
		Query q = em.createQuery(" from Parkiran where noPlat = :noParam").setParameter("noParam",
				parkir.getNoPlat().toLowerCase());
		try {
			par = (Parkiran) q.getSingleResult();
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
	public List<Parkiran> findAllByTanggalKeluar() throws Exception {
		Query q = em.createQuery(" from Parkiran where tanggalKeluar is not null");
		return q.getResultList();
	}

}
