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
package org.n52.sos.ds.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.n52.sos.ds.DataConnectionProvider;
import org.n52.sos.ds.Datasource;

public abstract class AbstractSciDBDatasource implements Datasource, DataConnectionProvider{
	
	
	protected  static final String DIALECT_NAME = "SciDB";
	protected  static final String KEY_SCIDB_URI = "uri.path.scidb";
	public  static final String SCIDB_URL="jdbc:scidb://localhost/";
	protected  static final String DEFAULT_SCIDB_URI = "/SciDB";
	protected static final String URI_DESCRIPTION = "SCiDB path URI";
	protected static final String SCIDB_DRIVER_CLASS = "org.scidb.jdbc.Driver";  
	 
	protected static final String USERNAME_DEFAULT_VALUE = "scidb";
    protected static final String USERNAME_DESCRIPTION = "The default value for SciDB username is \"scidb\".";
    protected static final String USERNAME_TITLE = "User Name";

   protected static final String PASSWORD_DEFAULT_VALUE = "xxxx.xxxx.xxxx";
   protected static final String PASSWORD_DESCRIPTION = "The default password value is \"xxxx.xxxx.xxxx\".";
   protected static final String PASSWORD_TITLE = "Password";
   
   protected static final String HOST_TITLE = "Host";
   protected static final String HOST_DESCRIPTION = "the default value for database server for scidb is \"localhost\".";

   protected static final int PORT_DEFAULT_VALUE = 1239; 
   protected static final String PORT_DESCRIPTION = "The default value for scidb posrt is 1239";
   protected static final String PORT_TITLE = "DataBase port number ";
   
   protected static final int PORT_DEFAULT_SHIMPORT =8083;
   protected static final String PORT_SHIMPORT_DESCRIPTION = "The default value for SHIM posrt is 8083";
   protected static final String PORT_SHIMPORT_TITLE = "SHIMPORT number "; 

   protected static final String MIN_POOL_SIZE_KEY = "jdbc.pool.min";
   protected static final String MIN_POOL_SIZE_TITLE = "Minimum ConnectionPool size";
   protected static final String MIN_POOL_SIZE_DESCRIPTION = "Minimum size of the ConnectionPool";
   protected static final Integer MIN_POOL_SIZE_DEFAULT_VALUE = 10;

   protected static final String MAX_POOL_SIZE_KEY = "jdbc.pool.max";
   protected static final String MAX_POOL_SIZE_TITLE = "Maximum ConnectionPool size";
   protected static final String MAX_POOL_SIZE_DESCRIPTION = "Maximum size of the ConnectionPool";
   protected static final Integer MAX_POOL_SIZE_DEFAULT_VALUE = 30;
   
   protected static final String IDLE_TEST_PERIOD= "30";
   protected static final String ACQUIRE_INCREMENT ="1";
   protected static final String TIMEOUT ="0";
   protected static final String MAX_STATEMENTS = "0";
   
   String CONNECTION_AUTO_RECONNECT="true";
   String CONNECTION_AUTO_RECONNECT_FOR_POOLS = "true";
   String CONNECTION_TEST_ON_BORROW = "true";
   
   protected static final Boolean PROVIDED_JDBC_DRIVER_DEFAULT_VALUE = false;

   protected static final String PROVIDED_JDBC_DRIVER_TITLE = "Provided JDBC driver";

   protected static final String PROVIDED_JDBC_DRIVER_DESCRIPTION = "Is the JDBC driver provided and should not be derigistered during shutdown?";

   protected static final String PROVIDED_JDBC_DRIVER_KEY = "sos.jdbc.provided";
   String PROVIDED_JDBC = "PROVIDED_JDBC";
   
   protected static final String DATABASE_CONCEPT_KEY = "sos.database.concept";
   
    

    //  protected static final String DATABASE_KEY = "jdbc.database";

    

      protected static final String DATABASE_DESCRIPTION ="Set this to the name of the database you want to use for SOS.";

      protected static final String DATABASE_DEFAULT_VALUE = "sos";

      //protected static final String HOST_KEY = "jdbc.host";

     
      //protected static final String HOST_DEFAULT_VALUE = "localhost";

      //protected static final String PORT_KEY = "jdbc.port";
      protected abstract Connection openConnection() throws SQLException;

    

    

}
