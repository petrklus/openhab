/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.lifx.internal;

import org.openhab.binding.lifx.internal.LifxBindingConfig.BindingType;
import org.openhab.binding.lifx.LifxBindingProvider;
import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.ColorItem;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is responsible for parsing the binding configuration.
 * 
 * @author Petr Klus
 * @since 1.5.0
 */
public class LifxGenericBindingProvider extends AbstractGenericBindingProvider implements LifxBindingProvider {

	static final Logger logger = LoggerFactory
			.getLogger(LifxGenericBindingProvider.class);
	
	/**
	 * {@inheritDoc}
	 */
	public String getBindingType() {
		return "lifx";
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException {
		
		// only allow color items		
		if (!(item instanceof ColorItem || item instanceof DimmerItem)) {
			throw new BindingConfigParseException("item '" + item.getName()
					+ "' is of type '" + item.getClass().getSimpleName()
					+ "', only ColorItems are allowed - please check your *.items configuration");
		}

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
		super.processBindingConfiguration(context, item, bindingConfig);		
		
		try {

			if (bindingConfig != null) {

				String[] configParts = bindingConfig.split(":");

				logger.debug("Lifx config parsing: {}", configParts);				

				String bulb_id =  configParts[0];
				
				
				if (item instanceof ColorItem) {
					BindingConfig lifxBindingConfig = (BindingConfig) new LifxBindingConfig(
							bulb_id, BindingType.rgb.name());
					
					addBindingConfig(item, lifxBindingConfig);
				} if (item instanceof DimmerItem) {
					BindingConfig lifxBindingConfig = (BindingConfig) new LifxBindingConfig(
							bulb_id, BindingType.white.name());
					addBindingConfig(item, lifxBindingConfig);
				}

			} else {
				logger.warn("bindingConfig is NULL (item=" + item
						+ ") -> processing bindingConfig aborted!");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.warn("bindingConfig is invalid (item=" + item
					+ ") -> processing bindingConfig aborted!");
		}
		
			
	}	
	
}
