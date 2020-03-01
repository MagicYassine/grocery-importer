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
@JsonPropertyOrder({ "gross", "vat" })
public class Total
{

	@JsonProperty("gross")
	private String gross;
	@JsonProperty("vat")
	private String vat;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("gross")
	public String getGross()
	{
		return gross;
	}

	@JsonProperty("gross")
	public void setGross(String gross)
	{
		this.gross = gross;
	}

	@JsonProperty("vat")
	public String getVat()
	{
		return vat;
	}

	@JsonProperty("vat")
	public void setVat(String vat)
	{
		this.vat = vat;
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