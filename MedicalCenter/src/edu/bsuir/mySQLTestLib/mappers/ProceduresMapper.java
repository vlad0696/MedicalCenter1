package edu.bsuir.mySQLTestLib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.bsuir.mySQLTestLib.dao.Mapper;
import edu.bsuir.mySQLTestLib.model.Procedures;


public class ProceduresMapper extends Mapper<Procedures> {
	
	@Override
	public Procedures mapRow(ResultSet rs) {
		Procedures procedures = new Procedures();
		try {
			procedures.setId(rs.getInt("id"));
			procedures.setCabinet(rs.getString("cabinet"));
			procedures.setDate(rs.getString("date"));
			procedures.setTime(rs.getString("time"));
		
		} catch (SQLException e) {
			super.log.warn("Mapper excecution failed with SQLException");
		}
		super.log.info("Users successfully extracted from db");
		return procedures;
	}

}