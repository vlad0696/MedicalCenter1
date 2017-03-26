package edu.bsuir.mySQLTestLib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.bsuir.mySQLTestLib.dao.Mapper;
import edu.bsuir.mySQLTestLib.model.Diseases;

public class DiseasesMapper extends Mapper<Diseases> {
	
	@Override
	public Diseases mapRow(ResultSet rs) {
		Diseases disease = new Diseases();
		try {
			disease.setId(rs.getInt("id"));
			disease.setDisease(rs.getString("name"));
		
		} catch (SQLException e) {
			super.log.warn("Mapper excecution failed with SQLException");
		}
		super.log.info("Diseases successfully extracted from db");
		return disease;
	}

}
