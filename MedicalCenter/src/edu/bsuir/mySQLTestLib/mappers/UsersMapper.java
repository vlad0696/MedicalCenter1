package edu.bsuir.mySQLTestLib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.bsuir.mySQLTestLib.dao.Mapper;
import edu.bsuir.mySQLTestLib.model.Users;

public class UsersMapper extends Mapper<Users> {
	
	@Override
	public Users mapRow(ResultSet rs) {
		Users users = new Users();
		try {
			users.setId(rs.getInt("id"));
			users.setPasswordHash(rs.getString("password_hash"));
			users.setTemporaryPasswordHash(rs.getString("temporary_password_hash"));
			users.setMail(rs.getString("mail"));
			users.setRoleId(rs.getInt("role_id"));
		
		} catch (SQLException e) {
			super.log.warn("Mapper excecution failed with SQLException");
		}
		super.log.info("Users successfully extracted from db");
		return users;
	}

}