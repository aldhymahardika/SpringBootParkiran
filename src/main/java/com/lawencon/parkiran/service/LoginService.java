package com.lawencon.parkiran.service;

public interface LoginService {
	abstract Boolean findByUsernameAndPassword(String username, String password);
}
