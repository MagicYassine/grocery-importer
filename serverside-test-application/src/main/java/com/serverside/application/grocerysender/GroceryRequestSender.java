package com.serverside.application.grocerysender;

import java.io.IOException;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.serverside.application.builder.JsonResultBuilder;
import com.serverside.application.config.GroceryProperties;
import com.serverside.application.enums.GroceryEndpointType;
import com.serverside.application.parser.HTMLParser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@Slf4j
@AllArgsConstructor
@Component
public class GroceryRequestSender
{
	private final GroceryProperties properties;
	private final JsonResultBuilder jsonBuilder;
	private final HTMLParser HTMLParser;

	public JsonObject fetchGroceryFruitItems()
	{
		final String productListEndPoint = properties.getEndpoints()
				.get(GroceryEndpointType.FRUITS);
		
		final JsonObject jasonObjectResult = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		log.info("Preparing to fetch product list from endpoint: {}", productListEndPoint);
		try
		{
			// We suppose to fetch 17 products.
			final Elements productList = HTMLParser.getElementsByClass(productListEndPoint, "productinfo");

			log.debug("Product list has {} products", productList.size());
			jsonArray = jsonBuilder.buildResultJsonArray(productList);

			jasonObjectResult.add("result", jsonArray);

			log.debug("built Json Array {}", jasonObjectResult);
		}
		catch (final IOException e)
		{
			log.error("fetch product list failed", e.getMessage(), e);
		}
		return jasonObjectResult;
	}
}
