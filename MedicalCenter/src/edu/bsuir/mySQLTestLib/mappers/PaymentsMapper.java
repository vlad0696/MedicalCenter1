package edu.bsuir.mySQLTestLib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.bsuir.mySQLTestLib.dao.Mapper;
import edu.bsuir.mySQLTestLib.model.Payments;

public class PaymentsMapper extends Mapper<Payments> {
	@Override
	public Payments mapRow(ResultSet rs) {
		Payments payments = new Payments();
		try {
			payments.setId(rs.getInt("id"));
			payments.setCost(rs.getInt("cost"));
			payments.setDate(rs.getInt("date"));
				
		} catch (SQLException e) {
			super.log.warn("Mapper excecution failed with SQLException");
		}
		super.log.info("Users successfully extracted from db");
		return payments;
	}
}
