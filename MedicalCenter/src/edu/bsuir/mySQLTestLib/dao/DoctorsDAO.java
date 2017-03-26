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
import edu.bsuir.mySQLTestLib.mappers.*;
import edu.bsuir.mySQLTestLib.model.Doctors;

public class DoctorsDAO extends AbstractDAO {

	private static Map<String, String> queryDB = Collections.unmodifiableMap(
	    new HashMap<String, String>() {{
	        put("listDoctors", "SELECT id, speciality, post,"
	        		+ "category, departament FROM doctor");
			
	        put("addDoctors", "INSERT INTO doctor (speciality, post, category, departament)"
	        		+ " VALUES (?,?,?,?)");
			
			put("deleteDoctors", "DELETE FROM doctor WHERE id = ?");
			put("updateDoctors", "UPDATE doctor SET speciality = ?, category=?, departament=?, post=?"+ 
			" WHERE id = ?");
	    }}
    );
	private static Map<String, String> methods = Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put("delete", "deleteElement");
			put("create", "addElement");
			put("update", "updateElement");
		}
	});

	
	private String getQuery(String key){
		return this.queryDB.get(key);
	}
	
	public List<Doctors> getAll() throws ConfigurationException, SQLException {
		List<Doctors> listDoctors = new ArrayList<Doctors>();
		ResultSet rs = super.select(this.getQuery("listDoctors"));
		Mapper<Doctors> mapper = MapperManager.getInstance().getMapper(Mappers.DOCTORS);
		while (rs.next()) {
			listDoctors.add((Doctors) mapper.mapRow(rs));
		}
		return listDoctors;
	}


	@Override
	public void Methods(Map<String, String> response) {
		try {
			Method[] allMethods = DoctorsDAO.class.getMethods();
			for (Method m : allMethods)
				if (m.getName().equals(DoctorsDAO.methods.get(response.get("action"))))
					m.invoke(DoctorsDAO.class.newInstance(), response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException
				| InstantiationException e) {
			e.printStackTrace();
		}
		
	}
	public void addElement(Map<String, String> resp) throws ConfigurationException, SQLException {
		PreparedStatement pstmt = super.prepareStatement(DoctorsDAO.queryDB.get("addDoctors"));
		pstmt.setString(1, resp.get("speciality"));
		pstmt.setString(4, resp.get("departament"));
		pstmt.setString(3, resp.get("category"));
		pstmt.setInt(2, Integer.parseInt(resp.get("post")));
	
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void deleteElement(Map<String, String> response) throws ConfigurationException, NumberFormatException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("deleteDoctors"));
		pstmt.setInt(1, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void updateElement(Map<String, String> response) throws ConfigurationException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("updateDoctors"));
		pstmt.setString(1, response.get("speciality"));
		pstmt.setString(2, response.get("category"));
		pstmt.setString(3, response.get("departament"));
		pstmt.setInt(4, Integer.parseInt(response.get("post")));
		pstmt.setInt(5, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}
}



