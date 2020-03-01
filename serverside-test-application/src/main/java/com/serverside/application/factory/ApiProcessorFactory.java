package com.serverside.application.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.serverside.application.api.ApiProcesssor;

import lombok.AllArgsConstructor;

/**
 * @author yassine.mandaris
 *
 */
@Component
@AllArgsConstructor
public class ApiProcessorFactory
{
	private List<ApiProcesssor> apiProcessors;

	public ApiProcesssor createInstance(String groceryCategory)
	{
		return apiProcessors.stream()
				.filter(apiProcessor -> groceryCategory.equals(apiProcessor.getGroceryCategory()))
				.findFirst()
				.orElse(null);
	}
}
