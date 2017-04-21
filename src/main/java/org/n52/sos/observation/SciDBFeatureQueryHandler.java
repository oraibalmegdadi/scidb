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

import java.util.Collection;
import org.n52.sos.ogc.gml.CodeWithAuthority;
import org.n52.sos.ogc.om.features.SfConstants;
import java.util.List;
import java.util.Map;

import org.n52.sos.ds.FeatureQueryHandler;
import org.n52.sos.ds.FeatureQueryHandlerQueryObject;
import org.n52.sos.ogc.filter.SpatialFilter;
import org.n52.sos.ogc.gml.AbstractFeature;
import org.n52.sos.ogc.om.features.samplingFeatures.SamplingFeature;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.ogc.sos.SosEnvelope;

public class SciDBFeatureQueryHandler implements FeatureQueryHandler {

	@Override
	public String getDatasourceDaoIdentifier() {
		return "SciDBGetObservationDAO";
	}

	@Override
	public AbstractFeature getFeatureByID(String featureID, Object connection,
			String version) throws OwsExceptionReport {

			String procedureID= SciDBObservation.getProcedureID();
			SamplingFeature feature = null;
			feature = createSosAbstractFeature(featureID);
			return feature;
	}

	private SamplingFeature createSosAbstractFeature(String featureID) {
		CodeWithAuthority cwa = new CodeWithAuthority(featureID);
		SamplingFeature feature = new SamplingFeature(cwa);
		feature.setFeatureType(SfConstants.SAMPLING_FEAT_TYPE_SF_SAMPLING_POINT);
		return feature;
		
	}

	@Override
	public AbstractFeature getFeatureByID(
			FeatureQueryHandlerQueryObject queryObject){
		return null;
			
	}

	@Override
	public Collection<String> getFeatureIDs(SpatialFilter filter,
			Object connection) throws OwsExceptionReport {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getFeatureIDs(
			FeatureQueryHandlerQueryObject queryObject)
			throws OwsExceptionReport {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, AbstractFeature> getFeatures(Collection<String> foiIDs,
			List<SpatialFilter> list, Object connection, String version)
			throws OwsExceptionReport {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, AbstractFeature> getFeatures(
			FeatureQueryHandlerQueryObject queryObject)
			throws OwsExceptionReport {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SosEnvelope getEnvelopeForFeatureIDs(Collection<String> featureIDs,
			Object connection) throws OwsExceptionReport {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SosEnvelope getEnvelopeForFeatureIDs(
			FeatureQueryHandlerQueryObject queryObject)
			throws OwsExceptionReport {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertFeature(SamplingFeature samplingFeature,
			Object connection) throws OwsExceptionReport {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDefaultEPSG() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefault3DEPSG() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStorageEPSG() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStorage3DEPSG() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefaultResponseEPSG() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefaultResponse3DEPSG() {
		// TODO Auto-generated method stub
		return 0;
	}

}
