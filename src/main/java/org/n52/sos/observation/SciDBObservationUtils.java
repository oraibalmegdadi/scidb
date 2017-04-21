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
package org.n52.sos.observation;
import org.joda.time.DateTime;
import org.n52.sos.ogc.om.OmObservation;
import org.scidb.jdbc.IResultSetWrapper;
import org.scidb.jdbc.IStatementWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.n52.sos.ogc.gml.AbstractFeature;
import org.n52.sos.ogc.gml.CodeWithAuthority;
import org.n52.sos.ogc.gml.time.Time;
import org.n52.sos.ogc.gml.time.TimeInstant;
import org.n52.sos.ogc.gml.time.TimePeriod;
import org.n52.sos.ogc.om.AbstractPhenomenon;
import org.n52.sos.ogc.om.OmConstants;
import org.n52.sos.ogc.om.OmObservableProperty;
import org.n52.sos.ogc.om.OmObservation;
import org.n52.sos.ogc.om.OmObservationConstellation;
import org.n52.sos.ogc.om.SingleObservationValue;
import org.n52.sos.ogc.om.values.CountValue;
import org.n52.sos.ogc.om.values.QuantityValue;
import org.n52.sos.ogc.om.values.TextValue;
import org.n52.sos.ogc.om.values.UnknownValue;
import org.n52.sos.ogc.om.values.Value;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.ogc.sensorML.SensorMLConstants;
import org.n52.sos.ogc.sos.SosProcedureDescription;
import org.n52.sos.ogc.sos.SosProcedureDescriptionUnknowType;
import org.n52.sos.request.GetObservationRequest;
import org.n52.sos.service.Configurator;
import org.n52.sos.service.ServiceConfiguration;
import org.n52.sos.util.SosHelper;
import org.n52.sos.util.StringHelper;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.request.GetObservationRequest;

public class SciDBObservationUtils {
	
	
	
	private static final Configurator CONFIGURATOR = Configurator.getInstance();
	private static final String PROCEDURE_DESCRIPTION_FORMAT = SensorMLConstants.NS_SML;

	private static final ServiceConfiguration SERVICE_CONFIGURATION = ServiceConfiguration
			.getInstance();
	private SciDBObservationUtils() {
		super();
	}

public static Connection returnconnection ()
	{
		 Connection conn=null;
		 try
	     {			  
	conn = DriverManager.getConnection("jdbc:scidb://localhost:1239/");
		     }
	     catch (SQLException e)
	     {
	         System.out.println(e);
	     }
		 return conn;	
	}
	
	

	public static List<OmObservation> createSosObservationsFromObservations(
			final HashSet<SciDBObservation> observations,
			final String version, final String resultModel) {
		
		
		final List<OmObservation> observationCollection = new ArrayList<OmObservation>(0);
		final Map<String, AbstractFeature> features = new HashMap<String, AbstractFeature>(0);
		final Map<String, AbstractPhenomenon> obsProps = new HashMap<String, AbstractPhenomenon>(0);
		final Map<String, SosProcedureDescription> procedures = new HashMap<String, SosProcedureDescription>(0);
		final Map<Integer, OmObservationConstellation> observationConstellations = new HashMap<Integer, OmObservationConstellation>(0);
		SciDBObservation observationw = new SciDBObservation() ;
		
		if (observations != null) {

		//	putAllAbstractFeaturesForObservations(features, observations);

			for ( SciDBObservation observation : observations) {
				

				final String procedureID = observation.getProcedureID();
				final String foiID = observation.getFeatureOfInterestID();
				final String phenID = observation.getObservableProperty();
				final String valueID= observation.getValue();
				final String unitID= observation.getUnit();
				final Value<?> value = extractvalue(valueID);
				final String observableProperty= observation.getObservableProperty();
				SosProcedureDescription procedure = retrieveSosProcedureDescription(
						version, procedures, procedureID);
				
				final OmObservationConstellation obsConst = new OmObservationConstellation(
						procedure, obsProps.get(phenID), features.get(foiID));
				final int obsConstHash = obsConst.hashCode();
				final OmObservation sosObservation = createNewObservation(
						observationConstellations, observation, value,
						obsConstHash);
				observationCollection.add(sosObservation);
								
			}
			
				
	}
		else 
		{
		
			final String procedureID = observationw.getProcedureID();
			final String foiID = observationw.getFeatureOfInterestID();
			final String phenID = observationw.getObservableProperty();
			final String valueID= observationw.getValue();
			final String unitID= observationw.getUnit();
			final Value<?> value = extractvalue(valueID);
			final String observableProperty= observationw.getObservableProperty();
			SosProcedureDescription procedure = retrieveSosProcedureDescription(
					version, procedures, procedureID);	
			final OmObservationConstellation obsConst = new OmObservationConstellation(
					procedure, obsProps.get(phenID), features.get(foiID));
			final int obsConstHash = obsConst.hashCode();
			final OmObservation sosObservation = createNewObservation(
					observationConstellations, observationw, value,
					obsConstHash);
				observationCollection.add(sosObservation);
			
		}
		return observationCollection;
	}
	
	 private static <Value> Value extractvalue(String a){
         return (Value) a;
 }


	private static OmObservation createNewObservation(
			final Map<Integer, OmObservationConstellation> observationConstellations,
			final SciDBObservation observation,  final Value<?> value,
			final int obsConstHash) {
	
		final OmObservation sosObservation = new OmObservation();
		
		String observationIdentifier = observation.getFeatureOfInterestID();
		String observationSerialID = observation.getFeatureOfInterestID();
		sosObservation.setObservationID(observationSerialID);

		final CodeWithAuthority identifier = new CodeWithAuthority(observationIdentifier);
		sosObservation.setIdentifier(identifier);

		sosObservation.setNoDataValue(CONFIGURATOR.getProfileHandler()
				.getActiveProfile().getResponseNoDataPlaceholder());

		sosObservation.setTokenSeparator(SERVICE_CONFIGURATION
				.getTokenSeparator());
		sosObservation.setTupleSeparator(SERVICE_CONFIGURATION
				.getTupleSeparator());
		sosObservation.setObservationConstellation(observationConstellations
				.get(obsConstHash));
	/*	sosObservation.setResultTime(new TimeInstant(new DateTime(observation
				.getResultTime(), DateTimeZone.UTC)));*/
		sosObservation.setValue(new SingleObservationValue(	getPhenomenonTime(), value));
		return sosObservation;
	}
	
	private static Time getPhenomenonTime() {
		// TODO Auto-generated method stub
		//DateTime dt = new DateTime();
		DateTime dt = new DateTime("2004-12-13T21:39:45.618-08:00");
	Time	phenomenonTime = new TimeInstant(dt);
		return phenomenonTime;
	}

	private static SosProcedureDescription retrieveSosProcedureDescription(
			final String version,
			final Map<String, SosProcedureDescription> procedures,
			final String procedureID) {
		SosProcedureDescription procedure;
		procedure = new SosProcedureDescriptionUnknowType("WorldClimSensor",
				PROCEDURE_DESCRIPTION_FORMAT, null);
		procedures.put(procedureID, procedure);
		
		return procedure;
	}



	public static Collection<SciDBObservation> queryObservations(Collection<String> observedProList,
			Collection<String> proceduresSciDB, GetObservationRequest request) throws SQLException {
		
		
		if (observedProList == null
				|| observedProList.isEmpty()) {
	
			observedProList.add("band1"); //band1: is the Min_temp
			
		}
		
		Collection<SciDBObservation> observations = Lists.newArrayList();
		SciDBObservation newObservation = new SciDBObservation();
		Connection conn=returnconnection ();
		Statement st = conn.createStatement();            
	     IStatementWrapper stWrapper = st.unwrap(IStatementWrapper.class);
	     stWrapper.setAfl(true);
	     int i=0;

	     ResultSet res = st.executeQuery("between(WClim,120,1222,1,121,1223,2)");
	     while (res.next() && i!=1)
         {   
	     int observationValue = res.getInt("band1");
	     
	     newObservation.setValue(Integer.toString(observationValue));
	     newObservation.setObservableProperty("band1");
	     i++;
         }
	
	               
	     observations.add(newObservation); 
	     //, SciDBObservation.getFeatureOfInterestID()		 			SciDBObservation.getValue(),SciDBObservation.getUnit(),request);
	     
	   
			//protected static String observableProperty = null; // ex: minT, maxT, meanT
			//private DateTime phenomenonTime = null; //month
			
	      return observations;
	}
}
