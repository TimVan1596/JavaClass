package com.smallfangyu.jdbcdvd.data;

import com.smallfangyu.jdbcdvd.model.DVD;
import java.sql.*;
import java.util.ArrayList;

public class Data {

	ArrayList<DVD> dvds = new ArrayList<DVD>();
	DbUtil db = new DbUtil();

	/**
	 * 把数据库里DVD的信息添加进集合里
	 *
	 * @return
	 */
	public ArrayList<DVD> dvdList() {
		String sql = "SELECT * FROM dvd";
		ResultSet rs = db.executeQuery(sql, null);
		try {
			while (rs.next()) {
				dvds.add(new DVD(rs.getInt("dvdno"), rs.getString("dvdname"), rs.getString("state")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dvds;
	}

}
