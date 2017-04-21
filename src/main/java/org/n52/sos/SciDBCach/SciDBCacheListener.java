/**
 * Copyright (C) 2012-2016 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.SciDBCach;


/**
 * A Listener that reacts on service startup and shutdown. 
 * 
 * @author Oraib Almegdadi
 * 
 */

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.n52.sos.service.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SciDBCacheListener  implements ServletContextListener {
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SciDBCacheListener.class);

	/**
	 * Creates an instance of the SciDB on service startup.
	 */
	@Override
	public final void contextInitialized(final ServletContextEvent sce) {

		String scidbPath = "/" + ScidbCach.SUB_PATH;

		ServletContext servletContext = sce.getServletContext();
		URL scidbCacheFile = null;
		try {
			scidbCacheFile = servletContext
					.getResource(scidbPath);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		if (scidbCacheFile != null) {
			loadScidbCache();
		} else {
			LOGGER.warn("No scidb.ser exists at default location!");
		}

	}

	private void loadScidbCache() {
		Thread loadScidbCache = new Thread(new Runnable() {

			@Override
			public void run() {

				Configurator configurator = Configurator.getInstance();

				while (configurator == null) {
					try {

						Thread.sleep(5 * 1000); // wait for 5 seconds
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						configurator = Configurator.getInstance();
					}
				}

				ScidbCach.getInstance();

			}
		});

		LOGGER.info("Loading ScidbCache!");
		loadScidbCache.start();
		try {
			loadScidbCache.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
		


	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {

		// write chache to default location

		ScidbCach ScidbCache = ScidbCach.getInstance();
	
	}

	

}
