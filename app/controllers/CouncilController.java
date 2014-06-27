package controllers;

import static play.data.Form.*;

import javax.inject.Inject;

import models.Council;
import models.dao.CouncilDAO;
import play.mvc.Call;
import play.utils.crud.CRUDController;

public class CouncilController extends CRUDController<Integer, Council> {
	
	@Inject
	public CouncilController(CouncilDAO dao) {
		super(dao, form(Council.class), Integer.class, Council.class, 999, "key");
	}

	@Override
	protected String templateForList() {
		return "councilList";
	}

	@Override
	protected String templateForForm() {
		return "councilForm";
	}

	@Override
	protected String templateForShow() {
		return "councilShow";
	}

	@Override
	protected Call toIndex() {
		return routes.Application.index();
	}

}
