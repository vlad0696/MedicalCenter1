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
import edu.bsuir.mySQLTestLib.model.Procedures;

public class ProceduresDAO extends AbstractDAO {

	private static Map<String, String> queryDB = Collections.unmodifiableMap(
	    new HashMap<String, String>() {{
	        put("listProcedures", "SELECT id, cabinet, date,"
	        		+ "time FROM procedures");
			
	        put("addProcedures", "INSERT INTO procedures (cabinet, date, time)"
	        		+ " VALUES (?,?,?)");
			
			put("deleteProcedures", "DELETE FROM procedures WHERE id = ?");
			put("updateProcedures", "UPDATE procedures SET cabinet = ?, date=?, time=?"+ 
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
	
	public List<Procedures> getAll() throws ConfigurationException, SQLException {
		List<Procedures> listProcedures = new ArrayList<Procedures>();
		ResultSet rs = super.select(this.getQuery("listProcedures"));
		Mapper<Procedures> mapper = MapperManager.getInstance().getMapper(Mappers.PROCEDURES);
		while (rs.next()) {
			listProcedures.add((Procedures) mapper.mapRow(rs));
		}
		return listProcedures;
	}


	@Override
	public void Methods(Map<String, String> response) {
		try {
			Method[] allMethods = ProceduresDAO.class.getMethods();
			for (Method m : allMethods)
				if (m.getName().equals(this.methods.get(response.get("action"))))
					m.invoke(ProceduresDAO.class.newInstance(), response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException
				| InstantiationException e) {
			e.printStackTrace();
		}
		
	}
	public void addElement(Map<String, String> resp) throws ConfigurationException, SQLException {
		PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("addProcedures"));
		pstmt.setString(1, resp.get("cabinet"));
		pstmt.setString(2, resp.get("date"));
		pstmt.setString(3, resp.get("time"));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void deleteElement(Map<String, String> response) throws ConfigurationException, NumberFormatException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("deleteProcedures"));
		pstmt.setInt(1, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void updateElement(Map<String, String> response) throws ConfigurationException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("updateProcedures"));
		pstmt.setString(1, response.get("cabinet"));
		pstmt.setString(2, response.get("date"));
		pstmt.setString(3, response.get("time"));
		pstmt.setInt(4, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}
}



