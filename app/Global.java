import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.FiniteDuration;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import controllers.Secured;
import scala.concurrent.duration.Duration;


public class Global extends GlobalSettings {
	@Override
	public void onStart(Application app) {
		// Magic goes here
		FiniteDuration delay = FiniteDuration.create(0, TimeUnit.SECONDS);
		FiniteDuration frequency = FiniteDuration.create(5, TimeUnit.SECONDS);
		Runnable showTime = new Runnable() {
			@Override
			public void run() {
				System.out.println("Time is now: " + new Date());
			}
		};

		Akka.system()
				.scheduler()
				.schedule(delay, frequency, showTime,
						Akka.system().dispatcher());
		
		//Another example
		Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.SECONDS),
                Duration.create(5, TimeUnit.SECONDS),
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Hello2");
                    }
                },
                Akka.system().dispatcher()
		);
	}

}