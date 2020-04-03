package com.lawencon.parkiran.controller;

/*
 * @author Aldhy
 * Aplikasi parkiran dengan SpringBoot Hibernate/JPA
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.parkiran.model.Parkiran;
import com.lawencon.parkiran.service.ParkiranService;

@RestController
public class HomeController {

	@Autowired
	private ParkiranService parkirService;

	@GetMapping("/home")
	public ResponseEntity<List<Parkiran>> getHome(@RequestHeader("username") String username,
			@RequestHeader("password") String password) {
		List<Parkiran> listParkir = new ArrayList<>();
		try {
			listParkir = parkirService.findAll(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listParkir, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listParkir, HttpStatus.OK);
	}

	@GetMapping("/home/")
	public ResponseEntity<List<Parkiran>> getJenis(@RequestParam("jenisKendaraan") String jenis,
			@RequestHeader("username") String username, @RequestHeader("password") String password) {
		List<Parkiran> listParkir = new ArrayList<>();
		try {
			listParkir = parkirService.findByJenisKendaraan(jenis, username, password);
		} catch (Exception e) {
			return new ResponseEntity<>(listParkir, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listParkir, HttpStatus.OK);
	}

	@GetMapping("/home/checkout")
	public ResponseEntity<List<Parkiran>> getCheckout(@RequestHeader("username") String username,
			@RequestHeader("password") String password) {
		List<Parkiran> listParkir = new ArrayList<>();
		try {
			listParkir = parkirService.findByTanggalKeluar(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listParkir, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listParkir, HttpStatus.OK);
	}

	@PostMapping("/checkin")
	public ResponseEntity<?> getInsert(@RequestBody String content, @RequestHeader("username") String username,
			@RequestHeader("password") String password) {
		try {
			Parkiran p = new ObjectMapper().readValue(content, Parkiran.class);
			parkirService.insert(p, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Berhasil", HttpStatus.OK);
	}

	@PostMapping("/checkout")
	public ResponseEntity<String> updateHiber(@RequestParam("id") int id,
			@RequestParam("tanggalKeluar") String tanggal_keluar, @RequestHeader("username") String username,
			@RequestHeader("password") String password) {
		try {
			parkirService.update(id, tanggal_keluar, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Berhasil", HttpStatus.OK);
	}

}
