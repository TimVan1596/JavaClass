package com.aiit.service;

import com.aiit.dao.LoginDao;
import com.aiit.model.Login;

public class LoginService {
public boolean checkLogin(Login login){
	LoginDao loginDao=new LoginDao();
	return loginDao.selectLogin(login);
	
}
}
