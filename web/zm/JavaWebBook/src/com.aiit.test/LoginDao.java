package com.aiit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aiit.common.JDBCUtils;
import com.aiit.model.Login;



public class LoginDao {
Connection conn = JDBCUtils.getConnection();
public boolean selectLogin(Login login){
	String sql="select *from login where username=? and password=?";
	try {
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, login.getUsername());
		pst.setString(2, login.getPassword());
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
		return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	
}
}
