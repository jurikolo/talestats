package models.dao;

import play.utils.dao.BasicDAO;
import models.Hero;

public class HeroDAO extends BasicDAO<Integer, Hero> {

	public HeroDAO() {
		super(Integer.class, Hero.class);
	}

}
