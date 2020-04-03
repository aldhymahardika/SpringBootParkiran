package com.lawencon.parkiran.dao;

public interface LoginDao {
	abstract Boolean findByUsernameAndPassword(String username, String password);
}
