package controllers;

import static play.data.Form.*;

import javax.inject.Inject;

import models.City;
import models.dao.CityDAO;
import play.mvc.Call;
import play.utils.crud.CRUDController;

public class CityController extends CRUDController<Integer, City> {
	
	@Inject
	public CityController(CityDAO dao) {
		super(dao, form(City.class), Integer.class, City.class, 999, "key");
	}

	@Override
	protected String templateForList() {
		return "cityList";
	}

	@Override
	protected String templateForForm() {
		return "cityForm";
	}

	@Override
	protected String templateForShow() {
		return "cityShow";
	}

	@Override
	protected Call toIndex() {
		return routes.Application.index();
	}

}
