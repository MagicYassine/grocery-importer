package com.serverside.application.processor;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.serverside.application.api.ApiProcesssor;
import com.serverside.application.factory.ApiProcessorFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GroceryProcessor
{
	private final ApiProcessorFactory apiProcessorFactory;

	public JsonObject run(String groceryCategory)
	{
		return loadGrocery(groceryCategory);
	}

	private JsonObject loadGrocery(String category)
	{
		JsonObject jsonFinalResult = null;
		log.debug("Create instance fruits processor");
		ApiProcesssor apiProcesssor = apiProcessorFactory.createInstance(category);
		if (apiProcesssor != null)
		{
			jsonFinalResult = apiProcesssor.process();
		}
		else
		{
			log.info("No processor available for this food category");
		}
		return jsonFinalResult;
	}
}
