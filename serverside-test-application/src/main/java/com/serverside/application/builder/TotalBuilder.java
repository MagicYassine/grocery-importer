package com.serverside.application.builder;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.serverside.application.pojo.GroceryItems;
import com.serverside.application.pojo.Result;
import com.serverside.application.pojo.Total;
import com.serverside.application.tax.TaxCalculator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@Component
@AllArgsConstructor
@Slf4j
public class TotalBuilder
{
	private final TaxCalculator taxCalculator;
	
	public Total calculateTotal(GroceryItems items)
	{
		log.debug("Processing total and vat");

		// create instances
		final Total total = new Total();
		final BigDecimal vatRate = new BigDecimal(20.00);
		BigDecimal totalGross = new BigDecimal(0.00);

		if (items != null)
		{
			for (final Result singleItem : items.getResult())
			{
				final String unitPrice = singleItem.getUnitPrice()
						.replaceAll("[£,]", "");
				totalGross = totalGross.add(new BigDecimal(unitPrice));
			}

			// Calculate Vat
			final BigDecimal vat = taxCalculator.calculateVatAmount(totalGross, vatRate);

			// Populate total object
			total.setGross(totalGross.toString());
			total.setVat(vat.toString());

			log.info("Vat amount is: {}", vat);
			log.info("Gross amount is: {}", totalGross);
		}
		return total;
	}
}
