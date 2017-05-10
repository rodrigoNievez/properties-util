package mx.infotec.service;

import java.util.Properties;

public interface FileManagerService {

	Properties getProperties(String file);
	
	boolean writeFile(Properties properties, String outputFile);
}
