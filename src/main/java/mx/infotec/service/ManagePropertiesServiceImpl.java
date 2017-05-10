package mx.infotec.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagePropertiesServiceImpl implements ManagePropertiesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagePropertiesServiceImpl.class);

	@Autowired
	private FileManagerService fileManagerService;

	@Override
	public boolean setNewPropertie(String key, String value, String file) {
		Properties properties = fileManagerService.getProperties(file);
		if (properties != null) {

			properties.put(key, value);
			return fileManagerService.writeFile(properties, file);

		} else {
			LOGGER.error("No se encontraron propiedades!");
		}

		return false;
	}

	@Override
	public boolean setNewProperties(Properties putProperties, String file) {
		Properties properties = fileManagerService.getProperties(file);
		if (properties != null) {
			properties.putAll(putProperties);
			return fileManagerService.writeFile(properties, file);
		} else {
			LOGGER.error("No se encontro archivo de propiedades destino");
		}
		
		
		return true;
	}

	@Override
	public boolean updatePropertie(String key, String value, String file) {
		Properties properties = fileManagerService.getProperties(file);
		if (properties != null) {
			if (properties.containsKey(key)) {
				properties.setProperty(key, value);
				return fileManagerService.writeFile(properties, file);
			}
		}

		return false;
	}

	@Override
	public boolean updateProperties(Properties updatedProperties, String file) {
		Properties properties = fileManagerService.getProperties(file);
		for (Map.Entry<Object, Object> elemet : updatedProperties.entrySet()) {

		}
		return false;
	}

}
