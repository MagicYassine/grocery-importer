package com.serverside.application.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "result", "total" })
public class GroceryItems
{
	@JsonProperty("result")
	private List<Result> result = new ArrayList<Result>();
	@JsonProperty("total")
	private Total total;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("result")
	public List<Result> getResult()
	{
		return result;
	}

	@JsonProperty("result")
	public void setResult(List<Result> result)
	{
		this.result = result;
	}

	@JsonProperty("total")
	public Total getTotal()
	{
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Total total)
	{
		this.total = total;
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