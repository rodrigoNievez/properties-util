package mx.infotec.service;

import java.util.Map;

public interface ManagePropertiesService {
	
	boolean setNewPropertie(String key, String value, String file);
	
	boolean setNewProperties(Map<String, String> properties, String file);
	
	boolean updatePropertie(String key, String value, String file);
	
	boolean updateProperties(Map<String, String> properties, String file);
	
}
