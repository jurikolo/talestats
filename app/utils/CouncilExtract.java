package utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CouncilExtract {

	public Integer getCount(Document doc) {
		Elements cnt = doc.select("div.accordion-toggle");
		return cnt.size();
	}
	
	public Integer getId(Document doc, Integer cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("pgf-person")+11, str.indexOf("\">"));
		return Integer.parseInt(str);
	}

	public String getName(Document doc, Integer cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<a href=\"#\">")+12, str.indexOf("<small>"));
		return str;
	}

	public String getRace(Document doc, Integer cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>")+7, str.indexOf("</small"));
		str = str.substring(0, str.indexOf("-"));
		return str;
	}
}
