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

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.n52.sos.config.SettingDefinition;
import org.n52.sos.config.settings.StringSettingDefinition;
import org.n52.sos.ds.ConnectionProvider;
import org.n52.sos.ds.ConnectionProviderException;
import org.n52.sos.ds.DatasourceCallback;


import com.google.common.collect.Sets;

import org.n52.sos.ds.datasource.AbstractSciDBDatasource;
import org.n52.sos.service.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SciDBDatasource extends AbstractSciDBDatasource  {

	private static final Logger LOGGER = LoggerFactory.getLogger(SciDBDatasource.class);
	
	
	// private static SciDBDataConnectionProvider instance = null; 
	private static SciDBDatasource instance = null;
	 public Connection conn = null;
	// private String connString=null; 
	//  private final ConnectionProvider connectionProvider = Configurator.getInstance().getDataConnectionProvider();
	  //LOGGER.info("SciDB connection provider value  " + connectionProvider);
	  

	  //  private final ThreadLocalSessionFactory sessionFactory = new ThreadLocalSessionFactory(connectionProvider);
 
	
	 @Override
	 public final void initialize(final Properties properties) {
			
		 try {
	            Class.forName(SciDBDatasource.SCIDB_DRIVER_CLASS);
	            
	        } catch (ClassNotFoundException e) {
	            LOGGER.info("SciDB JDBC Driver is not in the CLASSPATH: " + e);
	        }
	        //"MA"connString = "jdbc:scidb://" + Config.get().SCIDBWCS_DB_HOST + ":" + Config.get().SCIDBWCS_DB_PORT;
	//	connString = SciDBDatasource.SCIDB_URL+ ":" + SciDBDatasource.PORT_DEFAULT_VALUE;
	//	jdbc:scidb://localhost/:1239
		try {
			conn= openConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		}
	 
	 
		
		@Override
		public void returnConnection(final Object file) {

		}

	

		@Override
		public String getConnectionProviderIdentifier() {
	
			//return HibernateDatasourceConstants.ORM_CONNECTION_PROVIDER_IDENTIFIER;
			return "SciDBDatasource";
		//	return null;
		}

		@Override
		public void cleanup() {
			// TODO Auto-generated method stub
			
		}
	 
	 
	 @Override
	    protected Connection openConnection() throws SQLException {
		 
		  
		 try
		    {
		      Class.forName("org.scidb.jdbc.Driver");
		    }
		    catch (ClassNotFoundException e)
		    {
		      System.out.println("Driver is not in the CLASSPATH -> " + e);
		    }

		  
		    	String pass = PASSWORD_DEFAULT_VALUE;
	            String user = USERNAME_DEFAULT_VALUE;
		   //  return  conn = DriverManager.getConnection("jdbc:scidb://localhost/", user, pass);
	            return  conn = DriverManager.getConnection("jdbc:scidb://localhost:1239/", user, pass);
		     
		   
	        
	    }


	
/*	public static void main(String [] args) throws IOException
	  {
	    try
	    {
	      Class.forName(SCIDB_DRIVER_CLASS);
	    }
	    catch (ClassNotFoundException e)
	    {
	      System.out.println("Driver is not in the CLASSPATH -> " + e);
	    }

	    try
	    {
	      Connection conn = DriverManager.getConnection(SCIDB_URL);
	      Statement st = conn.createStatement();
	      	      System.out.println("=====");
	      	   

	        }
	    catch (SQLException e)
	    {
	      System.out.println(e);
	    }
	  System.exit(0);
	  }*/
	
/*	 protected Connection openConnection(Map<String, Object> settings) throws SQLException {
	        try {
	        //    String jdbc = toURL(settings);
	            Class.forName(getDriverClass());
	            String pass = PASSWORD_DEFAULT_VALUE;
	            String user = USERNAME_DEFAULT_VALUE;
	            return DriverManager.getConnection(jdbc, user, pass);
	        } catch (ClassNotFoundException ex) {
	            throw new SQLException(ex);
	        }
	    }*/
	
	
	 /**
     * Create multiple settings definition for database name
     *
     * @return database name settings definition
     */
 

	    protected final StringSettingDefinition createUsernameDefinition() {
	        return new StringSettingDefinition()
	                .setGroup(BASE_GROUP)
	                .setOrder(1)
	                .setDefaultValue(USERNAME_DEFAULT_VALUE)
	                .setKey(USERNAME_DEFAULT_VALUE)
	                .setTitle(USERNAME_TITLE);
	    }

	      
	       
	    protected final StringSettingDefinition createPasswordDefinition() {
	        return new StringSettingDefinition()
	        		.setGroup(BASE_GROUP)
	        		.setOrder(2)
	                .setKey(PASSWORD_DEFAULT_VALUE)
	                .setDescription(PASSWORD_DESCRIPTION)
	                .setDefaultValue(PASSWORD_DEFAULT_VALUE)
	                .setTitle(PASSWORD_TITLE);
	    }
	   
	 
	protected final StringSettingDefinition createScidbUri() {
		return new StringSettingDefinition()
				.setGroup(BASE_GROUP)
				.setOrder(3)
				.setKey(KEY_SCIDB_URI)
				.setTitle(URI_DESCRIPTION)
				.setDefaultValue(DEFAULT_SCIDB_URI);
	}
	
	protected final StringSettingDefinition createPort() {
		return new StringSettingDefinition()
				.setGroup(BASE_GROUP)
				.setOrder(4)
				.setKey(Integer.toString(PORT_DEFAULT_VALUE))
				.setDescription(PORT_DESCRIPTION)
				.setTitle(PORT_TITLE)
				.setDefaultValue(Integer.toString(PORT_DEFAULT_VALUE)); 
	}
/*	
	protected final StringSettingDefinition createSHIMPort() {
		return new StringSettingDefinition()
				.setGroup(BASE_GROUP)
				.setOrder(5)
				.setDescription(PORT_SHIMPORT_DESCRIPTION)
				.setTitle(PORT_SHIMPORT_TITLE);
				//.setDefaultValue(Integer.toString(PORT_DEFAULT_SHIMPORT)); 
	}
*/
	
//setter override	
	
	@Override
	public final String getDialectName() {
		return DIALECT_NAME;
	}
	/*@Override
	 public void setUsernameDefault() {
	        return USERNAME_DEFAULT_VALUE;
	    }
	
	 public void setUsernameDescription() {
	        return USERNAME_DESCRIPTION;
	    }
	public void setPasswordDefault() {
		        return PASSWORD_DEFAULT_VALUE;
		    }
	public void   setPasswordDescription() {
		        return PASSWORD_DESCRIPTION;
		    }
	public void setHostDescription(){ 
	return HOST_DESCRIPTION; }
	
	public void setPortDefault(){ 
	return PORT_DEFAULT_VALUE; 
	} 
	
	public void setPortDescription(){ 
	return PORT_DESCRIPTION;}
	*/
	
	 protected String getDriverClass() {
	        return SCIDB_DRIVER_CLASS;
	    }
	
             @Override
         	public final Set<SettingDefinition<?, ?>> getSettingDefinitions() {
         		return Sets.<SettingDefinition<?, ?>> newHashSet(
         				createUsernameDefinition(),
         				createPasswordDefinition(),
         				createScidbUri(),
         				createPort()
         				//createSHIMPort()
         				);

         	}
         	
         	@Override
         	public final Set<SettingDefinition<?, ?>> getChangableSettingDefinitions(
         			final Properties arg0) {
         		return Sets.<SettingDefinition<?, ?>> newHashSet(
         				createUsernameDefinition(),
         				createPasswordDefinition(),
         				createScidbUri()//,
         				//createPort(),
         				//createSHIMPort()
         				);

         	}
/*	
 * 
 *  return Sets.<SettingDefinition<?, ?>> newHashSet(createUsernameDefinition(usernameDefault),
             createPasswordDefinition(passwordDefault),
             
	@Override
	public final Set<SettingDefinition<?, ?>> getSettingDefinitions() {
		return Sets.<SettingDefinition<?, ?>> newHashSet(createScidbUri(),
				createScidbUri());

	}
	
	@Override
	public final Set<SettingDefinition<?, ?>> getChangableSettingDefinitions(
			final Properties arg0) {
		return Sets.<SettingDefinition<?, ?>> newHashSet(createScidbUri(),
				createScidbUri());
	}
	@Override
	public final Set<SettingDefinition<?, ?>> getSettingDefinitions() {
		return Sets.<SettingDefinition<?, ?>> newHashSet(createUsernameDefinition,
				createUsernameDefinition);

	}
	
   @Override
	public final Set<SettingDefinition<?, ?>> getChangableSettingDefinitions(
			final Properties arg0) {
		return Sets.<SettingDefinition<?, ?>> newHashSet(createUsernameDefinition,
				createUsernameDefinition);
	}*/
       	
  
    @Override
	public final Object getConnection() throws ConnectionProviderException {

        if (instance == null) {
            instance = new SciDBDatasource();
        }
        return instance;

	}
	@Override
	public final boolean checkIfSchemaExists(final Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public final boolean checkIfSchemaExists(final Properties arg0,
			final Map<String, Object> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public final boolean checkSchemaCreation(final Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear(final Properties arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public final String[] createSchema(final Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	public final String[] dropSchema(final Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	public void execute(final String[] arg0, final Map<String, Object> arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public final Properties getDatasourceProperties(final Map<String, Object> settings) {
		final Properties p = new Properties();
		p.put(KEY_SCIDB_URI, settings.get(KEY_SCIDB_URI));
		p.put(USERNAME_DEFAULT_VALUE, settings.get(USERNAME_DEFAULT_VALUE));
        p.put(PASSWORD_DEFAULT_VALUE, settings.get(PASSWORD_DEFAULT_VALUE));
       p.put(SCIDB_URL,"jdbc:scidb://localhost:1239/");
   //    p.put(HibernateConstants.CONNECTION_PROVIDER_CLASS, C3P0_CONNECTION_POOL);
        
        
        //////return delicate//////
        
        p.put(DIALECT_NAME, getDialectName());
        p.put(SCIDB_DRIVER_CLASS, getDriverClass());
        p.put(MIN_POOL_SIZE_DEFAULT_VALUE, Integer.toString(MIN_POOL_SIZE_DEFAULT_VALUE));
        p.put(MAX_POOL_SIZE_DEFAULT_VALUE, Integer.toString(MAX_POOL_SIZE_DEFAULT_VALUE));
        p.put(IDLE_TEST_PERIOD, "30");
        p.put(ACQUIRE_INCREMENT, "1");
        p.put(TIMEOUT, "0");
        p.put(MAX_STATEMENTS, "0");
       /* if (settings.containsKey(BATCH_SIZE_KEY)) {
            p.put(HibernateConstants.JDBC_BATCH_SIZE, settings.get(BATCH_SIZE_KEY).toString());
        }*/
        p.put(CONNECTION_AUTO_RECONNECT, "true");
        p.put(CONNECTION_AUTO_RECONNECT_FOR_POOLS, "true");
        p.put(CONNECTION_TEST_ON_BORROW, "true");
        p.put(PROVIDED_JDBC, PROVIDED_JDBC_DRIVER_KEY);
        p.put(DATABASE_CONCEPT_KEY, DATABASE_CONCEPT_KEY);
       ///Return here/// addMappingFileDirectories(settings, p);
		
		
		
		return p;
	}
	

/*	@Override
	public final Properties getDatasourceProperties(final Properties arg0,
			final Map<String, Object> settings) {
		// TODO Auto-generated method stub
		return getDatasourceProperties(settings);
	}*/

	@Override
	public final boolean needsSchema() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public final boolean supportsClear() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validateConnection(final Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateConnection(final Properties arg0,
			final Map<String, Object> arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validatePrerequisites(final Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validatePrerequisites(final Properties arg0,
			final Map<String, Object> arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateSchema(final Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateSchema(final Properties arg0,
			final Map<String, Object> arg1) {
		// TODO Auto-generated method stub

	}

    @Override
    public String[] updateSchema(Map<String, Object> arg0) {
        // TODO Auto-generated method stub
        return new String[0];
    }



	@Override
	public String getDatasourceDaoIdentifier() {
		// TODO Auto-generated method stub
		
		// return HibernateDatasourceConstants.ORM_DATASOURCE_DAO_IDENTIFIER;
	//	return "SciDBCacheFeederDAO";
		return "SciDBGetObservationDAO";
		//return null;
	}

	@Override
	public DatasourceCallback getCallback() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepare(Map<String, Object> settings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPostCreateSchema() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executePostCreateSchema(Map<String, Object> databaseSettings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPostCreation(Properties properties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Properties getDatasourceProperties(Properties current,
			Map<String, Object> newSettings) {
		// TODO Auto-generated method stub
		return null;
	}

}
