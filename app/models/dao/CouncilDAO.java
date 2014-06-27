package models.dao;

import play.utils.dao.BasicDAO;
import models.Council;

public class CouncilDAO extends BasicDAO<Integer, Council> {

	public CouncilDAO() {
		super(Integer.class, Council.class);
	}
	
}
