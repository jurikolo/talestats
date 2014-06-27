package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import models.City;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;
import play.utils.inject.InjectAdapter;

public class CityControllerTest {

	CityController controller;

	@Before
	public void start() {
		FakeApplication app = fakeApplication(inMemoryDatabase());
		Helpers.start(app);
		controller = getInstance(CityController.class);
	}

	@Test
	public void testGetDao() {
		assertThat(controller.getDao()).isNotNull();
	}

	@Test
	public void testGetKeyClass() {
		assertThat(controller.getKeyClass()).isEqualTo(Integer.class);
	}

	@Test
	public void testGetModelClass() {
		assertThat(controller.getModelClass()).isEqualTo(City.class);
	}

	@Test
	public void testCreate() {
		City entity = new City();
		entity.setKey(99);
		entity.setName("testCreate");
		entity.setSize(42);
		controller.getDao().create(entity);
		Result result = controller.read(99);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testUpdate() {
		City entity = new City();
		//First create record
		entity.setKey(99);
		entity.setName("testUpdate");
		entity.setSize(42);
		controller.getDao().create(entity);
		
		//Now update record
		entity.setName("testUpdate2");
		entity.setSize(43);
		controller.getDao().update(entity);
		Result result = controller.read(99);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testDelete() {
		
		City entity = new City();
		entity.setKey(99);
		entity.setName("test");
		entity.setSize(42);
		controller.getDao().create(entity);
		Result result = controller.delete(99);
		assertThat(result).isNotNull();
	}

	protected <T> T getInstance(Class<T> type) {
		return InjectAdapter.getInstance().getInstance(type);
	}

}
