package com.serverside.application.api;

import com.google.gson.JsonObject;

/**
 * @author yassine.mandaris
 *
 */
public interface ApiProcesssor
{
	JsonObject process();
	
	String getGroceryCategory();
}