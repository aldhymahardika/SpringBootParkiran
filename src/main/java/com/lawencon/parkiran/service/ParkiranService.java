package com.lawencon.parkiran.service;

import java.util.List;

import com.lawencon.parkiran.model.Parkiran;

public interface ParkiranService {
	abstract List<Parkiran> findAll(String username, String password) throws Exception;

	abstract List<Parkiran> findByTanggalKeluar(String username, String password) throws Exception;

	abstract List<Parkiran> findByJenisKendaraan(String jenis, String username, String password) throws Exception;

	abstract Parkiran insert(Parkiran parkir, String username, String password) throws Exception;

	abstract Parkiran update(int id, String tanggal, String username, String password) throws Exception;

	abstract Boolean valid(Parkiran parkir) throws Exception;
}
