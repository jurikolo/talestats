package models.dao;

import play.utils.dao.BasicDAO;
import models.City;

public class CityDAO extends BasicDAO<Long, City> {

	public CityDAO() {
		super(Long.class, City.class);
		addListener(new CityDAOListener());
	}

}
