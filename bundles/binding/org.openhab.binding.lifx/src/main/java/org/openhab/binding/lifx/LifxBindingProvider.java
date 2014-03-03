/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.lifx;

import org.openhab.core.binding.BindingProvider;

/**
 * @author Petr Klus
 * @since 1.5.0
 */
public interface LifxBindingProvider extends BindingProvider {

	/**Discovery
	The apps start by sending UDP "discovery" packets to the network broadcast address, port 56700. They do this repeatedly until a bulb responds by sending a UDP packet back to you on port 56700. Packets are identified via its type (of 0x03).
	The first response which matches this is what I'm using as the "controller" bulb. The controller appears to continue sending UDP packets, but I have not yet dug in to these; my assumption is that they are general announcements to the rest of the network in case there are multiple apps running which want to control the bulbs.
	*/
	
}

