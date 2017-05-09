package mx.infotec.service;

import java.util.Map;

public interface FileManagerService {

	Map<String, String> getProperties(String file);
	
	boolean writeFile();
}
