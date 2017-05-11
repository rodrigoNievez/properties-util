package mx.infotec.service;

import java.util.Properties;

public interface ManagePropertiesFilesService {
	
	boolean createPropertiesFile(Properties properties, String fileProperties);
	
	boolean createMultiplePropertiesFile(Properties properties, String fileContiner);
	
	boolean copyPropertiesFile(String from, String to);
	
	boolean copyMultiplePropertiesFile(String from, String fileContiner);
	
	boolean mergeProperties(String from, String to);
	
	boolean mergeMutipleProperties(String from, String fileContiner);
}
