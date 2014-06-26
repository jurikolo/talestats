package models.dao;

import play.Logger;
import play.Logger.ALogger;
import play.utils.dao.BasicDAO;
import models.City;

public class CityDAO extends BasicDAO<Integer, City> {
	
	private static ALogger log = Logger.of(CityDAO.class);

	public CityDAO() {
		super(Integer.class, City.class);
		//addListener(new CityDAOListener());
	}
	
	public void saveOrUpdate(City city) {
		if (null == city) 
			throw new RuntimeException("City is NULL!!!");
		
		if (null == get(city.getKey())) {
			log.debug("City not found");
			create(city);
		} else {
			update(city);
		}
	}

}
