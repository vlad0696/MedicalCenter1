package edu.bsuir.mySQLTestLib.dao;

import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Mapper<T> {
	protected  Logger log = LogManager.getLogger(Class.class.getName());

	public abstract T mapRow(ResultSet rs);
}
