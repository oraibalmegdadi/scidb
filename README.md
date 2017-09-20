# scidb
SciDB Module 
:This module was created to integrate the SciDB array database as a data source of the 52°North SOS 4.x implementation under OGC standards.

# Prerequest
To run this Module; the system should contains the following:

•	Ubuntu operating system  (to run scidb)

•	Configured SciDB

•	Services : ssh, postgres, server (I use tomcat)

# Steps to compilation 

1- Download the 52°North Sensor Observation Service (SOS) version 4.4.x from the repository https://github.com/52North/SOS 

2- Download the current repository "Scidb"

3- move the scidb folder to the 52North SOS
4-Add the required dependencies 

5- This project is managed with Maven3. Simply run mvn clean install to create a deployable .WAR file

6- Upload the War file to te server, the connection to the SciDB should be accomlpished automaticly 

 


# Using the webapp without compilation 
1- find the war file under  (scidb\webapp\target) 
2- upload the webapp to the server  
3- Select the SciDB option from the drop down list and enter the configration details to connect to the SciDB array database


 ***NOTE: the ssh, postgresql services and SciDB should be running
