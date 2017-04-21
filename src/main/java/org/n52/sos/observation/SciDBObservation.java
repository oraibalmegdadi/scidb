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


public class SciDBObservation {
	
	protected static String featureOfInterestID = "point"; // x and y 
	protected static  String procedureID ="WorldClimSensor"; // static value, only one sensor
	protected static String observableProperty = null; // ex: minT, maxT, meanT
	private DateTime phenomenonTime = null; //month
	protected static String value = null;
	protected static  String unit = "C";
	
	
	/*
	 * getters and setters
	 */

	public final static  String getFeatureOfInterestID() {
		return featureOfInterestID;
	}

	public final void setFeatureOfInterestID(double x, double y) {
	featureOfInterestID = featureOfInterestID + Double.toString(x) + Double.toString(y);
	}


	public final String getObservableProperty() {
		return observableProperty;
	}

	public final void setObservableProperty(final String observableProperty) {
		SciDBObservation.observableProperty = observableProperty;
	}

	public final DateTime getPhenomenonTime() {
		return phenomenonTime;
	}

	public final void setPhenomenonTimeStart(final DateTime phenomenonTime) {
		this.phenomenonTime = phenomenonTime;
	}


	public static String getValue() {
		return value;
	}

	public void setValue(final String value) {
		SciDBObservation.value = value;
	}

	public final static String getUnit() {
		return unit;
	}

	public static String getProcedureID() {
		return procedureID;
	}



	
	

}
