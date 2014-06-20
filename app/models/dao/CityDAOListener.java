package models.dao;

import java.util.Random;

import models.City;
import play.utils.dao.DAOListener;

public class CityDAOListener implements DAOListener<Long, City> {

	Random random = new Random();
	

	@Override
	public void beforeCreate(City m) {
		m.setRandomValue(random.nextInt());
	}

	@Override
	public void beforeUpdate(City m) {
		m.setRandomValue(random.nextInt());
	}

	@Override
	public void afterCreate(Long key, City m) {
	}

	@Override
	public void afterRemove(Long key, City m) {
	}

	@Override
	public void afterUpdate(City m) {
	}

	@Override
	public void beforeRemove(Long key) {
	}

}
