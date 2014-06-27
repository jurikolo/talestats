package controllers;

import static play.data.Form.*;

import javax.inject.Inject;

import models.Hero;
import models.dao.HeroDAO;
import play.mvc.Call;
import play.utils.crud.CRUDController;

public class HeroController extends CRUDController<Integer, Hero> {
	
	@Inject
	public HeroController(HeroDAO dao) {
		super(dao, form(Hero.class), Integer.class, Hero.class, 999, "id");
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
