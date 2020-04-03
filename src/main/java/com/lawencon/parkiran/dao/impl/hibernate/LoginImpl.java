package com.lawencon.parkiran.dao.impl.hibernate;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.dao.LoginDao;
import com.lawencon.parkiran.dao.impl.hibernate.BaseHibernate;
import com.lawencon.parkiran.model.LoginUser;

@Repository("login_repo_hibernate")
public class LoginImpl extends BaseHibernate implements LoginDao {

	@Override
	public Boolean findByUsernameAndPassword(String username, String password) {
		LoginUser user = null;
		Query q = em.createQuery(" from LoginUser where username = :uname and password = :pwd");
		q.setParameter("uname", username);
		q.setParameter("pwd", password);
		try {
			user = (LoginUser) q.getSingleResult();
		} catch (Exception e) {
		}
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
}
