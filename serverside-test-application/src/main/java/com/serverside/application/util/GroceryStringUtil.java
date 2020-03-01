package com.serverside.application.util;

import org.springframework.stereotype.Component;

/**
 * @author yassine.mandaris
 *
 */
@Component
public class GroceryStringUtil
{
	public boolean isNullOrEmpty(String input)
	{
		return input == null || input.length() == 0 || input.equals("null");
	}
}
