package utils;

import org.jsoup.nodes.Document;

public class CityExtract {
	public Integer getSize(String str)
	{
		if ((str.substring(str.indexOf("Размер города")+15, str.indexOf("Размер города")+16).matches("[0-9]")) && (str.substring(str.indexOf("Размер города")+16, str.indexOf("Размер города")+17).matches("[0-9]")))						{
			return Integer.parseInt(str.substring(str.indexOf("Размер города")+15, str.indexOf("Размер города")+17));
		}
		else {
			return Integer.parseInt(str.substring(str.indexOf("Размер города")+15, str.indexOf("Размер города")+16));
		}
	}

	public String getName(Document doc) {
		String description = doc.select("meta[name=description]").get(0).attr("content");
		String cityName = description.substring(description.indexOf("«")+1, description.indexOf("»"));
		return cityName;
	}
}
