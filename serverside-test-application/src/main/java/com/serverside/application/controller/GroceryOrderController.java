package com.serverside.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serverside.application.builder.TotalBuilder;
import com.serverside.application.json.util.JsonMapperToGroceryItems;
import com.serverside.application.pojo.GroceryItems;
import com.serverside.application.pojo.Total;
import com.serverside.application.processor.GroceryProcessor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/Grocery")
public class GroceryOrderController
{
	private final GroceryProcessor groceryProcessor;
	private final TotalBuilder totalBuilder;
	private final JsonMapperToGroceryItems jsonUtil;

	@GetMapping(value = "/fruits")
	@ResponseBody
	public GroceryItems getFruit()
	{
		log.info("Start processing fruits crocery list");
		final GroceryItems items = jsonUtil.jsonToGroceryItems(groceryProcessor.run("fruits")
				.toString());

		final Total total = totalBuilder.calculateTotal(items);
		items.setTotal(total);
		
		log.info("Finish processing fruits crocery list");
		return items;
	}
}
