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
import edu.bsuir.mySQLTestLib.model.Diseases;

public class DiseasesDAO extends AbstractDAO {

	private static Map<String, String> queryDB = Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put("listDiseases", "SELECT id, name FROM disease");
			put("addDiseases", "INSERT INTO disease (name) VALUES (?)");
			put("deleteDisease", "DELETE FROM disease WHERE id = ?");
			put("updateDisease", "UPDATE disease SET name = ? WHERE id = ?");
		}
	});

	private static Map<String, String> methods = Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put("delete", "deleteElement");
			put("create", "addElement");
			put("update", "updateElement");
		}
	});

	public void Methods( Map<String, String> response) {
		try {
			Method[] allMethods = DiseasesDAO.class.getMethods();
			for (Method m : allMethods)
				if (m.getName().equals(this.methods.get(response.get("action"))))
					m.invoke(DiseasesDAO.class.newInstance(), response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException
				| InstantiationException e) {
			e.printStackTrace();
		}
	}

	private String getQuery(String key) {
		return DiseasesDAO.queryDB.get(key);
	}

	public List<Diseases> getAll() throws ConfigurationException, SQLException {
		List<Diseases> listDiseases = new ArrayList<Diseases>();
		ResultSet rs = super.select(this.getQuery("listDiseases"));
		Mapper<Diseases> mapper = MapperManager.getInstance().getMapper(Mappers.DISEASES);
		while (rs.next()) {
			listDiseases.add((Diseases) mapper.mapRow(rs));
		}
		return listDiseases;
	}

	public void addElement(Map<String, String> resp) throws ConfigurationException, SQLException {
		PreparedStatement pstmt = super.prepareStatement(DiseasesDAO.queryDB.get("addDiseases"));
		pstmt.setString(1, resp.get("disease"));
		pstmt.executeUpdate();
	}

	public void deleteElement(Map<String, String> response) throws ConfigurationException, NumberFormatException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(DiseasesDAO.queryDB.get("deleteDisease"));
		pstmt.setInt(1, Integer.parseInt(response.get("id")));
		pstmt.executeUpdate();
	}

	public void updateElement(Map<String, String> response) throws ConfigurationException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(DiseasesDAO.queryDB.get("updateDisease"));
		pstmt.setString(1, response.get("disease"));
		pstmt.setInt(2, Integer.parseInt(response.get("id")));
		pstmt.executeUpdate();
	}
}
