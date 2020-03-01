package com.serverside.application.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * @author yassine.mandaris
 *
 */
@Component
public class HTMLParser
{
	public Elements getElementsByClass(final String endPoint, final String className) throws IOException
	{
		return Jsoup.connect(endPoint)
				.get()
				.getElementsByClass(className);
	}

	public Elements getNutritionTableHrefFromProduct(final String html, String tagToSelect) throws IOException
	{
		return Jsoup.parse(html)
				.select(tagToSelect);
	}
}
