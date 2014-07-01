import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import play.utils.crud.CRUDManager;
import scala.concurrent.duration.FiniteDuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import models.City;
import models.Council;
import models.dao.CityDAO;
import models.dao.CouncilDAO;
import models.dao.HeroDAO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import utils.CityExtract;
import utils.CouncilExtract;
import utils.TaleStatConstants;

public class Global extends GlobalSettings {

	private CRUDManager manager;
	private CityExtract cityExtract;
	private CityDAO cityDao;
	private CouncilExtract councilExtract;
	private CouncilDAO councilDao;
	private HeroDAO heroDao;
	
	@Override
	public <A> A getControllerInstance(Class<A> type) throws Exception {
		A crudController = manager.getController(type);
		if (crudController != null)
			return crudController;
		return super.getControllerInstance(type);
	}

	@Override
	public void onStart(Application app) {
		// TODO 
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
		councilExtract = new CouncilExtract();
		councilDao = new CouncilDAO();
		heroDao = new HeroDAO();
		manager = new CRUDManager(this);
		manager.initialize(app);
	}

	private Runnable cronProcess() {
		Runnable getPage = new Runnable() {
			@Override
			public void run() {
				Document doc;
				//Run through all the Pandora cities and gather city, council and hero information
				for (Integer cityId = 1; cityId <= TaleStatConstants.CITY_COUNT; cityId++) {
					String url = "http://the-tale.org/game/map/places/"+cityId;
					try {
						doc = Jsoup.parse(Jsoup.connect(url).get().toString(), "UTF-8");
						String str = Jsoup.connect(url).get().toString();
						
						//Add or update city information
						Integer size = cityExtract.getSize(str);
						String cityName = cityExtract.getName(doc);
						City city = new City();
						city.setName(cityName);
						city.setSize(size);
						city.setKey(cityId);
						cityDao.saveOrUpdate(city);
						
						//Add or update council information
						Council council = new Council();
						for (Integer councilCnt = 1; councilCnt < councilExtract.getCount(doc); councilCnt++)
						{
							Integer councilId = councilExtract.getId(doc, councilCnt);
							String councilName = councilExtract.getName(doc, councilCnt);
							String councilRace = councilExtract.getRace(doc, councilCnt);
							council.setKey(councilId);
							council.setCityId(cityId);
							council.setName(councilName);
							council.setRace(councilRace);
							//Play2 is dead at this point
							//Not really sure whether eBean, Play2 or whatever else is guilty
							//But I get OptimisticLock each time trying to set or update council object
							//I give up and go for Spring + Vaadin.
							try {
								councilDao.remove(councilId);
								councilDao.create(council);
							} catch (Exception ex) {
								
							}
						}
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