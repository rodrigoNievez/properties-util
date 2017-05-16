package mx.infotec.service;

import java.util.Properties;

public interface ManagePropertiesService {
	
	boolean setNewPropertie(String key, String value, String file);
	
	boolean setNewProperties(Properties putProperties, String file);
	
	boolean updatePropertie(String key, String value, String file);
	
	boolean updateProperties(Properties updatedProperties, String file);
	
	boolean setNewPropertiesFromFile(Properties fromProperties, String toFile);
	
	boolean mergeProperties(Properties fromProperties, String toFile);
	
}
