package mx.infotec.service;

import java.util.Properties;

import mx.infotec.exception.PropertiesException;

public interface FileManagerService {

	Properties getProperties(String file) throws PropertiesException;
	
	boolean writeFile(Properties properties, String outputFile) throws PropertiesException;
}
