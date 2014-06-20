package controllers;

import static play.libs.Json.toJson;

import javax.inject.Inject;

import com.google.common.collect.ImmutableMap;

import models.City;
import models.dao.CityDAO;
import play.mvc.Result;
import play.utils.crud.APIController;

public class CityRestController extends APIController<Long, City> {

	@Inject
	public CityRestController(CityDAO dao) {
		super(dao, Long.class, City.class);
	}

	@Override
	public Result create() {
		Result check = checkRequired("name");
		if (check != null) {
			if (log.isDebugEnabled())
				log.debug("check : " + check);
			return check;
		}

		String name = jsonText("name");

		City m = new City();
		m.setName(name);
		
		Long key = dao.create(m);
		if (log.isDebugEnabled())
			log.debug("key : " + key);

		return created(toJson(ImmutableMap.of("status", "OK", "key", key)));
	}

}
