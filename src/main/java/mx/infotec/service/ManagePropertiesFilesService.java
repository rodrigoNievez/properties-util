package mx.infotec.service;

import java.util.Properties;

public interface ManagePropertiesFilesService {
	
	boolean createPropertiesFile(Properties properties, String fileProperties);
	
	boolean createMultiplePropertiesFile(Properties properties, String fileContiner);
	
	boolean copyPropertiesFile(String from, String to);
	
	boolean copyMultiplePropertiesFile(String from, String fileContiner);
	
	boolean updateProperties(String from, String to);
	
	boolean updateMutipleProperties(String from, String fileContiner);
	
	boolean setNewPropertiesFromFile(String from, String fileContainer);
	
	boolean mergePropertiesFromFile(String from, String to);
	
	boolean mergeMultiplePropertiesFromFile(String from, String fileContainer);
	
	boolean mergeWithSkipProperties(String from, String fileContainer, String skipFile);
}
