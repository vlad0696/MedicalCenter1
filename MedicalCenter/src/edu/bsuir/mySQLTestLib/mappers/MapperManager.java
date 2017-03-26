package edu.bsuir.mySQLTestLib.mappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.bsuir.mySQLTestLib.dao.Mapper;


public class MapperManager {
	
	private static final MapperManager INSTANCE;
	protected  Logger log = LogManager.getLogger(Class.class.getName());
	static
    {
		MapperManager mm;
        try
        {
            mm = new MapperManager();
        }
        catch(Exception e) {
            mm = null;
        }
        INSTANCE = mm;
    }
	
	private MapperManager() {
		
	}
	
	public static MapperManager getInstance() {
        return MapperManager.INSTANCE;
    }  
	
	public Mapper getMapper(Mappers mapper) {
		switch (mapper) {
			case DISEASES: {
				return new DiseasesMapper();
			}
			case USERS: {
				return new UsersMapper();
			}
			case DOCTORS:{
				return new DoctorsMapper();
			}
			case PROCEDURES:{
				return new ProceduresMapper();
			}
			case PAYMENTS:{
				return new PaymentsMapper();
			}
			
		}
		log.warn("Can't create mapper!");
		return null;
	}
}
