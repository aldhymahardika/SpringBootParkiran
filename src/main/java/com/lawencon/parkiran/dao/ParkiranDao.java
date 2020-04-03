package com.lawencon.parkiran.dao;

import java.util.List;

import com.lawencon.parkiran.model.Parkiran;

public interface ParkiranDao {
	abstract List<Parkiran> findAll() throws Exception;
	abstract List<Parkiran> findAllByTanggalKeluar() throws Exception;
	abstract List<Parkiran> findByJenisKendaraan(String jenisKendaraan) throws Exception;	
	abstract Parkiran insert(Parkiran parkir, String username, String password) throws Exception;
	abstract Parkiran update(int id, String tanggal) throws Exception;
	abstract Boolean valid(Parkiran parkir) throws Exception;
}
