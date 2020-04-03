package com.lawencon.parkiran.dao.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.model.Parkiran;

@Repository
public interface ParkiranRepo extends JpaRepository<Parkiran, Integer> {
	@Query(value = "select * from parkiran where tanggal_keluar is not null", nativeQuery = true)
	List<Parkiran> findAllByTanggalKeluar();

	List<Parkiran> findByJenisKendaraan(String jenisKendaraan);
}