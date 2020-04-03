package com.lawencon.parkiran.dao.impl.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.model.LoginUser;

@Repository
public interface LoginRepo extends JpaRepository<LoginUser, Integer> {
	@Query(value="select * from login_user lg where username = :username and password = :password", nativeQuery=true)
	LoginUser findUser(@Param("username") String username, @Param("password") String password);
}
