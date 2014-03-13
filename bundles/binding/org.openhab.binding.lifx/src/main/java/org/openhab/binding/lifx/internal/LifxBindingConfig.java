package org.openhab.binding.lifx.internal;


import org.openhab.binding.lifx.internal.LifxBindingConfig;
import org.openhab.binding.lifx.internal.LifxBindingConfig.BindingType;
import org.openhab.core.binding.BindingConfig;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LifxBindingConfig implements BindingConfig {

	static final Logger logger = LoggerFactory
			.getLogger(LifxBindingConfig.class);

	/**
	 * The binding type of the LIFX item - only rgb colour supported ATM.
	 * <ul>
	 * <li>RGB</li>
	 * <li>white</li>
	 * </ul>
	 * TODO - remove white as it does not "make sense"
	 */
	public enum BindingType {
		rgb, white
	}
	
	/**
	 * The identifier under which the bulb is filed in the Hue bridge.
	 */
	private final String deviceIdentifier;

	/**
	 * The binding type of the hue item.
	 */
	private final BindingType type;

	/**
	 * Constructor of the LifxBindingConfig.
	 * 
	 * @param deviceNumber
	 *            The number of the bulb assigned during discovery
	 * @param type
	 *            The optional binding type of the hue binding.
	 *            <ul>
	 *            <li>RGB</li>
	 *            </ul>
	 * @throws BindingConfigParseException
	 */
	public LifxBindingConfig(String deviceIdentifier, String type)
			throws BindingConfigParseException {

		// string ID of the bulb		
		this.deviceIdentifier = deviceIdentifier;

		if (type != null) {
			this.type = parseBindingTypeConfigString(type);
		} else {
			this.type = LifxBindingConfig.BindingType.rgb;
		}

	}
	
	/**
	 * Parses a binding type string that has been found in the configuration. If
	 * the string could not be parsed, the rgb type is returned as default.
	 * 
	 * @param configString
	 *            The binding type as a string.
	 * @return The binding type as a {@link LifxBindingConfig.BindingType}
	 * @throws BindingConfigParseException
	 */
	private LifxBindingConfig.BindingType parseBindingTypeConfigString(
			String configString) throws BindingConfigParseException {

		if (configString != null) {
			try {
				return BindingType.valueOf(configString.trim());
			} catch (Exception ignore) {
			}
		}
		return LifxBindingConfig.BindingType.rgb;
	}
	
	/**
	 * @return The device identifier that has been declared in the binding
	 *         configuration.
	 */
	public String getDeviceNumber() {
		return deviceIdentifier;
	}

	/**
	 * @return The binding type as a {@link HueBindingConfig.BindingType} that
	 *         has been declared in the binding configuration.
	 */
	public BindingType getType() {
		return type;
	}

	
}
