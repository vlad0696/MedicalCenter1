package edu.bsuir.mySQLTestLib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.bsuir.mySQLTestLib.dao.Mapper;
import edu.bsuir.mySQLTestLib.model.Doctors;
;

public class DoctorsMapper extends Mapper<Doctors> {
	
	@Override
	public Doctors mapRow(ResultSet rs) {
		Doctors doctors = new Doctors();
		try {
			doctors.setId(rs.getInt("id"));
			doctors.setSpeciality(rs.getString("speciality"));
			doctors.setDepartament(rs.getString("departament"));
			doctors.setCategory(rs.getString("category"));
			doctors.setPost(rs.getInt("post"));
		
		} catch (SQLException e) {
			super.log.warn("Mapper excecution failed with SQLException");
		}
		super.log.info("Users successfully extracted from db");
		return doctors;
	}

}