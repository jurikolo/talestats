import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import play.utils.crud.CRUDManager;
import scala.concurrent.duration.FiniteDuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import models.City;
import models.dao.CityDAO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import utils.CityExtract;
import utils.TaleStatConstants;

public class Global extends GlobalSettings {

	private CRUDManager manager;
	private CityExtract cityExtract;
	private CityDAO cityDao;
	
	@Override
	public <A> A getControllerInstance(Class<A> type) throws Exception {
		A crudController = manager.getController(type);
		if (crudController != null)
			return crudController;
		return super.getControllerInstance(type);
	}

	@Override
	public void onStart(Application app) {
		// Magic goes here
		// Comment this part for tests execution
		FiniteDuration delay = FiniteDuration.create(0, TimeUnit.MINUTES);
		FiniteDuration frequency = FiniteDuration.create(1, TimeUnit.MINUTES);
		Runnable showTime = cronProcess();

		Akka.system()
				.scheduler()
				.schedule(delay, frequency, showTime,
						Akka.system().dispatcher());

		super.onStart(app);

		cityExtract = new CityExtract();
		cityDao = new CityDAO();
		manager = new CRUDManager(this);
		manager.initialize(app);
	}

	private Runnable cronProcess() {
		Runnable getPage = new Runnable() {
			@Override
			public void run() {
				Document doc;
				for (int cityId = 1; cityId <= TaleStatConstants.CITY_COUNT; cityId++) {
					String url = "http://the-tale.org/game/map/places/"+cityId;
					try {
						doc = Jsoup.parse(Jsoup.connect(url).get().toString(), "UTF-8");
						String str = Jsoup.connect(url).get().toString();
						
						//Add or update city information
						Integer size = cityExtract.getSize(str);
						String cityName = cityExtract.getCityName(doc);
						City city = new City();
						city.setName(cityName);
						city.setSize(size);
						city.setKey(cityId);
						cityDao.saveOrUpdate(city);
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		return getPage;
	}
}