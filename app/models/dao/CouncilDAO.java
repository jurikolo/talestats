package models.dao;

import play.Logger;
import play.Logger.ALogger;
import play.utils.dao.BasicDAO;
import models.Council;

public class CouncilDAO extends BasicDAO<Integer, Council> {
	
	private static ALogger log = Logger.of(CouncilDAO.class);

	public CouncilDAO() {
		super(Integer.class, Council.class);
	}

	public void saveOrUpdate(Council council) {
		if (null == council) 
			throw new RuntimeException("Council is NULL!!!");
		
		if (null == get(council.getKey())) {
			log.debug("Council not found");
			create(council);
		} else {
			update(council);
		}
	}
}
