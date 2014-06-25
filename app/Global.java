import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import play.utils.crud.CRUDManager;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Global extends GlobalSettings {

	private CRUDManager manager;

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
		FiniteDuration delay = FiniteDuration.create(0, TimeUnit.SECONDS);
		FiniteDuration frequency = FiniteDuration.create(20, TimeUnit.SECONDS);
		Runnable showTime = cronProcess();

		Akka.system()
				.scheduler()
				.schedule(delay, frequency, showTime,
						Akka.system().dispatcher());

		super.onStart(app);

		manager = new CRUDManager(this);
		manager.initialize(app);
	}

	private Runnable cronProcess() {
		Runnable getPage = new Runnable() {
			@Override
			public void run() {
				Document doc;
				for (int cnt = 1; cnt < 2; cnt++) {
					String url = "http://the-tale.org/game/map/places/"+cnt;
					try {
						doc = Jsoup.parse(Jsoup.connect(url).get().toString(), "UTF-8");
						String str = Jsoup.connect(url).get().toString();
						//TODO refactor code to parse city size in a separate method
						int size = 0;
						if ((str.substring(str.indexOf("Размер города")+15, str.indexOf("Размер города")+16).matches("[0-9]")) && (str.substring(str.indexOf("Размер города")+16, str.indexOf("Размер города")+17).matches("[0-9]")))
						{
							size = Integer.parseInt(str.substring(str.indexOf("Размер города")+15, str.indexOf("Размер города")+17));
						}
						else
						{
							size = Integer.parseInt(str.substring(str.indexOf("Размер города")+15, str.indexOf("Размер города")+16));
						}
						System.out.println(size);
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