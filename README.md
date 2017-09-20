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

4- This project is managed with Maven3. Simply run mvn clean install to create a deployable .WAR file

4- Upload the War file to te server, the connection to the SciDB should be accomlpished automaticly 

 
 ***NOTE: the ssh, postgresql services and SciDB should be running





