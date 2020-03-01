package com.serverside.application.builder;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.serverside.application.json.util.JsonMapperItemsDetailsToJsonObject;
import com.serverside.application.parser.HTMLParser;
import com.serverside.application.pojo.GroceryItemDetails;
import com.serverside.application.util.GroceryStringUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@AllArgsConstructor
@Slf4j
@Component
public class JsonResultBuilder
{
	private final GroceryStringUtil stringUtil;
	private final HTMLParser HTMLParser;
	private final  JsonMapperItemsDetailsToJsonObject jsonMapperToJsonObject;
	public JsonArray buildResultJsonArray(final Elements productList) throws IOException
	{
		log.debug("Start building Json Array");
		final JsonArray jsonArray = new JsonArray();

		for (final Element product : productList)
		{
			final GroceryItemDetails itemDetails = new GroceryItemDetails();

			// Find unitprice and stripe off "/unit" from price string
			final String pricePerUnit = product.parent()
					.getElementsByClass("pricePerUnit")
					.text()
					.split("/")[0];

			// Get link to the nutrition tables for this product
			final Elements nutritionTablesReference =
					HTMLParser.getNutritionTableHrefFromProduct(product.html(), "a[href]");

			// Get product table from nutrition reference
			final String productTitle = nutritionTablesReference.text();

			final String href = nutritionTablesReference.attr("href");

			// If the Href is related to any product then build the nutrition table URL
			if (href.contains("shop/gb/groceries/berries-cherries-currants"))
			{
				fetchDescriptionAndKcalFromEndPoint(itemDetails, productTitle, href);
			}
			itemDetails.setTitle(productTitle);
			itemDetails.setUnitPrice(pricePerUnit);

			final JsonObject jsonObject = jsonMapperToJsonObject.mapItemDetailsToJsonObject(itemDetails);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	private void fetchDescriptionAndKcalFromEndPoint(final GroceryItemDetails itemDetails, final String productTitle,
			final String href) throws IOException
	{
		final String nutritionTableEndPoint = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk"
				+ href.substring(17, href.length());

		log.debug("fetching nutrition table for {} at endpoint {}", productTitle, nutritionTableEndPoint);

		// Get the first element with class name productText then the first child text
		String description = "";
		final Elements paragraphsList = HTMLParser.getElementsByClass(nutritionTableEndPoint, "productText")
				.select("p");

		// we expetct o find the description at the first or second paraghraph
		for (final Element paragraph : paragraphsList)
		{
			if (!stringUtil.isNullOrEmpty(paragraph.text()))
			{
				description = paragraph.text();
				break;
			}
		}

		fetchKcalFromEndPoint(itemDetails, productTitle, nutritionTableEndPoint);
		itemDetails.setDescription(description);
	}

	private void fetchKcalFromEndPoint(final GroceryItemDetails itemDetails, final String productTitle,
			String nutritionTableEndPoint) throws IOException
	{
		final Elements kcalElementsOnTabRow0 = HTMLParser.getElementsByClass(nutritionTableEndPoint, "tableRow0");
		final Elements kcalElementsOnRowHeader = HTMLParser.getElementsByClass(nutritionTableEndPoint, "rowHeader");

		String kcalPer100Grams = "";

		if (!kcalElementsOnTabRow0.isEmpty())
		{
			kcalPer100Grams = kcalElementsOnTabRow0.get(0)
					.child(0)
					.text()
					.replaceAll("[kcal,]", "");
		}
		else if (!kcalElementsOnRowHeader.isEmpty())
		{
			kcalPer100Grams = kcalElementsOnRowHeader.get(1)
					.parentNode()
					.childNode(2)
					.toString()
					.replaceAll("<[^>]*>", "")
					.replaceAll("[kcal,]", "");
		}
		else
		{
			log.debug("no kcal per 100g found for {}", productTitle);
		}
		itemDetails.setKcal(kcalPer100Grams);
	}
}
