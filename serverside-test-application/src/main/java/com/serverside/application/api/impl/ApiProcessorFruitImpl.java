package com.serverside.application.api.impl;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.serverside.application.api.ApiProcesssor;
import com.serverside.application.grocerysender.GroceryRequestSender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@Component
@AllArgsConstructor
@Slf4j
public class ApiProcessorFruitImpl implements ApiProcesssor
{
	private final GroceryRequestSender groceryRequestSender;

	@Override
	public JsonObject process()
	{
		return groceryRequestSender.fetchGroceryFruitItems();
	}

	@Override
	public String getGroceryCategory()
	{
		log.debug("Get fruits instance");
		return "fruits";
	}
}
