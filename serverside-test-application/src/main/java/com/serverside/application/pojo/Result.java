package com.serverside.application.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title", "kcal_per_100g", "unit_price", "description" })
public class Result
{
	@JsonProperty("title")
	private String title;
	@JsonProperty("kcal_per_100g")
	private String kcalPer100g;
	@JsonProperty("unit_price")
	private String unitPrice;
	@JsonProperty("description")
	private String description;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("title")
	public String getTitle()
	{
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title)
	{
		this.title = title;
	}

	@JsonProperty("kcal_per_100g")
	public String getKcalPer100g()
	{
		return kcalPer100g;
	}

	@JsonProperty("kcal_per_100g")
	public void setKcalPer100g(String kcalPer100g)
	{
		this.kcalPer100g = kcalPer100g;
	}

	@JsonProperty("unit_price")
	public String getUnitPrice()
	{
		return unitPrice;
	}

	@JsonProperty("unit_price")
	public void setUnitPrice(String unitPrice)
	{
		this.unitPrice = unitPrice;
	}

	@JsonProperty("description")
	public String getDescription()
	{
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description)
	{
		this.description = description;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}
}