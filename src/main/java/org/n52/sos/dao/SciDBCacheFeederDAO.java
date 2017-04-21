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
package org.n52.sos.dao;


import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.n52.sos.SciDBCach.ScidbCach;
import org.n52.sos.cache.WritableContentCache;
import org.n52.sos.ds.CacheFeederDAO;
//import org.n52.sos.SciDBCach.ScidbCach;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SciDBCacheFeederDAO implements CacheFeederDAO{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SciDBCacheFeederDAO.class);
	
	
	@Override
	public String getDatasourceDaoIdentifier() {
		// TODO Auto-generated method stub
	//	return "SciDBCacheFeederDAO";
		return "SciDBGetObservationDAO";

	}

	@Override
	public void updateCache(WritableContentCache capabilitiesCache)
			throws OwsExceptionReport {
	
		LOGGER.info("Begin to launch cache update!");
		long startTime = System.currentTimeMillis();
		
	  
	    ScidbCach scidbcache = ScidbCach.getInstance();
		
		try {
			
			ExecutorService executor = Executors.newFixedThreadPool(4);

			executor.shutdown();

			executor.awaitTermination(30, TimeUnit.MINUTES);

			LOGGER.info("Cache update completed in "
					+ (System.currentTimeMillis() - startTime) + " ms!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

		
		
	

	@Override
	public void updateCacheOfferings(WritableContentCache capabilitiesCache,
			Collection<String> offerings) throws OwsExceptionReport {
		// TODO Auto-generated method stub
		
	}

}
