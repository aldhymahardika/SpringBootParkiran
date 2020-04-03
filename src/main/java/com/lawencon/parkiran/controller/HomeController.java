package com.lawencon.parkiran.controller;

/*
 * @author Aldhy
 * Aplikasi parkiran dengan SpringBoot Hibernate/JPA
 * 
 */

import java.util.ArrayList;
import java.util.Base64;
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
public class HomeController extends BaseController {

	@Autowired
	private ParkiranService parkirService;
	
	@Override
	String authUser(String user) throws Exception {
		byte[] decodeBytes = Base64.getDecoder().decode(user);
		String decodeString = new String(decodeBytes);
		return decodeString;
	}
	
	@GetMapping("/home")
	public ResponseEntity<List<Parkiran>> getHome(@RequestHeader("Authorization") String user) {
		List<Parkiran> listParkir = new ArrayList<>();
		try {
			String[] auth = authUser(user).split(":");
			listParkir = parkirService.findAll(auth[0], auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listParkir, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listParkir, HttpStatus.OK);
	}

	@GetMapping("/home/")
	public ResponseEntity<List<Parkiran>> getJenis(@RequestParam("jenisKendaraan") String jenis,
			@RequestHeader("Authorization") String user) {
		List<Parkiran> listParkir = new ArrayList<>();
		try {
			String[] auth = authUser(user).split(":");
			listParkir = parkirService.findByJenisKendaraan(jenis, auth[0], auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(listParkir, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listParkir, HttpStatus.OK);
	}

	@GetMapping("/home/checkout")
	public ResponseEntity<List<Parkiran>> getCheckout(@RequestHeader("Authorization") String user) {
		List<Parkiran> listParkir = new ArrayList<>();
		try {
			String[] auth = authUser(user).split(":");
			listParkir = parkirService.findByTanggalKeluar(auth[0], auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listParkir, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listParkir, HttpStatus.OK);
	}

	@PostMapping("/checkin")
	public ResponseEntity<?> getInsert(@RequestBody String content, @RequestHeader("Authorization") String user) {
		try {
			String[] auth = authUser(user).split(":");
			Parkiran p = new ObjectMapper().readValue(content, Parkiran.class);
			parkirService.insert(p, auth[0], auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Berhasil", HttpStatus.OK);
	}

	@PostMapping("/checkout")
	public ResponseEntity<String> updateHiber(@RequestParam("id") int id,
			@RequestParam("tanggalKeluar") String tanggal_keluar, @RequestHeader("Authorization") String user) {
		try {
			String[] auth = authUser(user).split(":");
			parkirService.update(id, tanggal_keluar, auth[0], auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Berhasil", HttpStatus.OK);
	}

}
