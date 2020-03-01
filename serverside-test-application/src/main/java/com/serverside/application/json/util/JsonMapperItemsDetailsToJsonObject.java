package com.serverside.application.json.util;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
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
public class JsonMapperItemsDetailsToJsonObject
{
	private static final String PRODUCT_TITLE = "title";
	private static final String KCAL_PER_100_GRAMS = "kcal_per_100g";
	private static final String PRODUCT_DESCRIPTION = "description";
	private static final String PRODUCT_UNIT_PRICE = "unit_price";
	
	private final GroceryStringUtil stringUtil;

	public JsonObject mapItemDetailsToJsonObject(GroceryItemDetails itemDetails)
	{
		log.debug("start mapping {} to Json Object", itemDetails.getTitle());

		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(PRODUCT_TITLE, itemDetails.getTitle());

		if (!stringUtil.isNullOrEmpty(itemDetails.getKcal()))
		{
			jsonObject.addProperty(KCAL_PER_100_GRAMS, itemDetails.getKcal());
		}

		jsonObject.addProperty(PRODUCT_UNIT_PRICE, itemDetails.getUnitPrice());
		jsonObject.addProperty(PRODUCT_DESCRIPTION, itemDetails.getDescription());

		log.debug("json object for {} is {}", jsonObject.toString(), itemDetails.getTitle());
		return jsonObject;
	}
}
