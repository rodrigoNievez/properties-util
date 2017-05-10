package mx.infotec.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("fileManagerService")
public class FileManagerServiceImpl implements FileManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagerServiceImpl.class);
	private static final String EQUALS = "=";

	@Override
	public Map<String, String> getProperties(String file) {
		ResourceBundle bundle = ResourceBundle.getBundle(file);
		Map<String, String> properties = null;
		File propertiesFile = new File(file);
		Scanner fileScanner = null;
		try {
			if (propertiesFile.exists()) {
				fileScanner = new Scanner(propertiesFile);
				properties = new HashMap<>();
				while (fileScanner.hasNextLine()) {
					String[] values = fileScanner.nextLine().split(EQUALS,2);
					properties.put(values[0].trim(), values[1].trim());
				}
			} else {
				LOGGER.info("El archivo de origen, no existe");
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("No se encontro el archivo, causa: ", e);
		} finally {
			if (fileScanner != null)
				fileScanner.close();
		}

		return properties;
	}

	@Override
	public boolean writeFile() {
		// TODO Auto-generated method stub
		return false;
	}

}
