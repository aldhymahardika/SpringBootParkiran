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
	public List<Parkiran> valid(Parkiran parkir) throws Exception {
		Query q = em.createQuery(" from Parkiran where noPlat = :noParam").setParameter("noParam",
				parkir.getNoPlat().toLowerCase());
		return q.getResultList();
	}


	@Override
	public List<Parkiran> findAllByTanggalKeluar() throws Exception {
		Query q = em.createQuery(" from Parkiran where tanggalKeluar is not null");
		return q.getResultList();
	}

}
