package edu.bsuir.mySQLTestLib.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.bsuir.mySQLTestLib.config.ConfigurationException;
import edu.bsuir.mySQLTestLib.mappers.MapperManager;
import edu.bsuir.mySQLTestLib.mappers.Mappers;
import edu.bsuir.mySQLTestLib.model.Users;

public class UsersDAO extends AbstractDAO {

	private static Map<String, String> queryDB = Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put("listUsers", "SELECT id, password_hash, temporary_password_hash," + "mail, role_id FROM users");

			put("addUsers",
					"INSERT INTO users (password_hash," + "temporary_password_hash, mail, role_id) VALUES (?,?,?,?)");

			put("deleteUsers", "DELETE FROM users WHERE id = ?");
			put("updateUsers", "UPDATE users SET password_hash = ?, temporary_password_hash=?, mail=?, role_id=?"
					+ " WHERE id = ?");
		}
	});
	private static Map<String, String> methods = Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put("delete", "deleteElement");
			put("create", "addElement");
			put("update", "updateElement");
		}
	});

	private String getQuery(String key) {
		return UsersDAO.queryDB.get(key);
	}

	public List<Users> getAll() throws ConfigurationException, SQLException {
		List<Users> listUsers = new ArrayList<Users>();
		ResultSet rs = super.select(this.getQuery("listUsers"));
		Mapper<Users> mapper = MapperManager.getInstance().getMapper(Mappers.USERS);
		while (rs.next()) {
			listUsers.add((Users) mapper.mapRow(rs));
		}
		return listUsers;
	}

	@Override
	public void Methods(Map<String, String> response) {
		try {
			Method[] allMethods = UsersDAO.class.getMethods();
			for (Method m : allMethods)
				if (m.getName().equals(this.methods.get(response.get("action"))))
					m.invoke(UsersDAO.class.newInstance(), response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException
				| InstantiationException e) {
			e.printStackTrace();
		}

	}

	public void addElement(Map<String, String> resp) throws ConfigurationException, SQLException {
		PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("addUsers"));
		pstmt.setString(1, resp.get("passwordHash"));
		pstmt.setString(2, resp.get("temporaryPasswordHash"));
		pstmt.setString(3, resp.get("mail"));
		pstmt.setInt(4, Integer.parseInt(resp.get("roleId")));

		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void deleteElement(Map<String, String> response)
			throws ConfigurationException, NumberFormatException, SQLException {
		PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("deleteUsers"));
		pstmt.setInt(1, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void updateElement(Map<String, String> response) throws ConfigurationException, SQLException {
		PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("updateUsers"));
		pstmt.setString(1, response.get("passwordHash"));
		pstmt.setString(2, response.get("temporaryPasswordHash"));
		pstmt.setString(3, response.get("mail"));
		pstmt.setInt(4, Integer.parseInt(response.get("roleId")));
		pstmt.setInt(5, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}
}
