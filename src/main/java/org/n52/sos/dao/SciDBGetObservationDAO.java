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

import java.sql.SQLException;
import org.n52.sos.ds.AbstractGetObservationDAO;
import org.n52.sos.observation.SciDBObservation;
import org.n52.sos.observation.SciDBObservationUtils;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.ogc.sos.SosConstants;
import org.n52.sos.request.GetObservationRequest;
import org.n52.sos.response.GetObservationResponse;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.n52.sos.exception.ows.concrete.MissingObservedPropertyParameterException;
import org.n52.sos.ogc.om.OmObservation;
import org.n52.sos.ogc.sos.Sos1Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SciDBGetObservationDAO  extends AbstractGetObservationDAO { 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SciDBGetObservationDAO.class);
	
	 
	public SciDBGetObservationDAO() {
		super(SosConstants.SOS);
		// TODO Auto-generated constructor stub
		

	}

	@Override
	public String getDatasourceDaoIdentifier() {
		// TODO Auto-generated method stub
		return "SciDBGetObservationDAO";
	}

	@Override
	public GetObservationResponse getObservation(GetObservationRequest request)
			throws OwsExceptionReport {
	
		
		double startTime = System.currentTimeMillis();

		if (request.getVersion().equals(Sos1Constants.SERVICEVERSION)
				&& request.getObservedProperties().isEmpty()) {
			throw new MissingObservedPropertyParameterException();
		} 
		else {
			
			final GetObservationResponse sosResponse = new GetObservationResponse();
			sosResponse.setService(request.getService());
			sosResponse.setVersion(request.getVersion());
			sosResponse.setResponseFormat(request.getResponseFormat());
			if (request.isSetResultModel()) {
				sosResponse.setResultModel(request.getResultModel());
			}

			try {
				sosResponse.setObservationCollection(queryObservation(request));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			double workingTime = System.currentTimeMillis() - startTime;
			LOGGER.info("Time in milliseconds needed for acquiring "
					+ "the GetObservationResponse-object was: " + workingTime
					+ "\nAmount of resulting observations: "
					+ sosResponse.getObservationCollection().size());

			return sosResponse;
		}


	}
	
	
	private List<OmObservation> queryObservation(
			final GetObservationRequest request) throws OwsExceptionReport, SQLException {
		
		Collection<String> ObservedProList = request.getObservedProperties();
		
		double xCord = request.getSpatialFilter().getGeometry().getCoordinate().x;
		double yCord = request.getSpatialFilter().getGeometry().getCoordinate().y;
		
		Collection<String> proceduresSciDB =new HashSet<String>();
		proceduresSciDB.add(SciDBObservation.getProcedureID());
		
	//	SciDBObservation.setFeatureOfInterestID(xCord,yCord);
		Collection<String> foiSciDB = new HashSet<String>();
		foiSciDB.add(SciDBObservation.getFeatureOfInterestID());
		
			
		
		
		Collection<SciDBObservation> observationsSciDB = SciDBObservationUtils.queryObservations(
						ObservedProList,proceduresSciDB, request);

		return toSosObservations(observationsSciDB, request.getVersion(),
  				request.getResultModel());

			
	        }

	private List<OmObservation> toSosObservations(
			Collection<SciDBObservation> observations, String version,
			String resultModel) {
		
		
		final long startProcess = System.currentTimeMillis();
		final List<OmObservation> sosObservations = SciDBObservationUtils
				.createSosObservationsFromObservations(
						new HashSet<SciDBObservation>(observations),
						version, resultModel);
		LOGGER.debug("Time to process observations needs {} ms!",
				(System.currentTimeMillis() - startProcess));
		return sosObservations;
	}

		
		
	}


