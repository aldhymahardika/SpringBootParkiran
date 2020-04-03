package com.lawencon.parkiran.dao.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.model.Parkiran;

@Repository
public interface ParkiranRepo extends JpaRepository<Parkiran, Integer> {
	@Query(value = "select * from parkiran where tanggal_keluar is not null", nativeQuery = true)
	List<Parkiran> findAllByTanggalKeluar();

	List<Parkiran> findByJenisKendaraan(String jenisKendaraan);
	
	@Query(value="select * from parkiran where no_plat = :noParam ", nativeQuery = true)
	List<Parkiran> valid(@Param("noParam") String noPlat);
	
	//error terus
	@Query(value="update parkiran set tanggal_keluar = :keluarParam where id = :idParam", nativeQuery = true)
	Parkiran update(@Param("idParam") int id, @Param("keluarParam") String tanggalKeluar);
}