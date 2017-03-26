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
import edu.bsuir.mySQLTestLib.model.Payments;

public class PaymentsDAO extends AbstractDAO {

	private static Map<String, String> queryDB = Collections.unmodifiableMap(
	    new HashMap<String, String>() {{
	        put("listPayments", "SELECT id, cost, date FROM payment");
			
	        put("addPayments", "INSERT INTO payment (cost, date)"
	        		+ " VALUES (?,?)");
			
			put("deletePayments", "DELETE FROM payment WHERE id = ?");
			put("updatePayments", "UPDATE payment SET cost = ?, date=?"+ 
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
	
	public List<Payments> getAll() throws ConfigurationException, SQLException {
		List<Payments> listProcedures = new ArrayList<Payments>();
		ResultSet rs = super.select(this.getQuery("listPayments"));
		Mapper<Payments> mapper = MapperManager.getInstance().getMapper(Mappers.PAYMENTS);
		while (rs.next()) {
			listProcedures.add((Payments) mapper.mapRow(rs));
		}
		return listProcedures;
	}


	@Override
	public void Methods(Map<String, String> response) {
		try {
			Method[] allMethods = PaymentsDAO.class.getMethods();
			for (Method m : allMethods)
				if (m.getName().equals(this.methods.get(response.get("action"))))
					m.invoke(PaymentsDAO.class.newInstance(), response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException
				| InstantiationException e) {
			e.printStackTrace();
		}
		
	}
	public void addElement(Map<String, String> resp) throws ConfigurationException, SQLException {
		PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("addPayments"));
		pstmt.setString(1, resp.get("cost"));
		pstmt.setString(2, resp.get("date"));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void deleteElement(Map<String, String> response) throws ConfigurationException, NumberFormatException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("deletePayments"));
		pstmt.setInt(1, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void updateElement(Map<String, String> response) throws ConfigurationException, SQLException {
        PreparedStatement pstmt = super.prepareStatement(this.queryDB.get("updatePayments"));
		pstmt.setString(1, response.get("cost"));
		pstmt.setString(2, response.get("date"));
		pstmt.setInt(3, Integer.parseInt(response.get("id")));
		super.log.info(pstmt.toString());
		pstmt.executeUpdate();
	}
}



