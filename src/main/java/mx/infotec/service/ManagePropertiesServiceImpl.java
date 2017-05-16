package mx.infotec.service;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.exception.PropertiesException;

@Service
public class ManagePropertiesServiceImpl implements ManagePropertiesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagePropertiesServiceImpl.class);

	@Autowired
	private FileManagerService fileManagerService;

	@Override
	public boolean setNewPropertie(String key, String value, String file) {
		Properties properties;
		try {
			properties = fileManagerService.getProperties(file);
			properties.put(key, value);
			return fileManagerService.writeFile(properties, file);
		} catch (PropertiesException e) {
			LOGGER.error("Error al setear nuevas propiedades, causa: ", e);
		}
		
		return false;
	}

	@Override
	public boolean setNewProperties(Properties putProperties, String file) {
		Properties properties;
		try {
			properties = fileManagerService.getProperties(file);
			properties.putAll(putProperties);
			return fileManagerService.writeFile(properties, file);
		} catch (PropertiesException e) {
			LOGGER.error("Error al setear nuevas propiedades, causa: ", e);
		}
	
		return false;
	}

	@Override
	public boolean updatePropertie(String key, String value, String file) {
		Properties properties;
		try {
			properties = fileManagerService.getProperties(file);
			if (properties.containsKey(key)) {
				properties.setProperty(key, value);
				return fileManagerService.writeFile(properties, file);
			}
		} catch (PropertiesException e) {
			LOGGER.error("Error al setear nuevas propiedades, causa: ", e);
		}

		return false;
	}

	@Override
	public boolean updateProperties(Properties updatedProperties, String file) {
		Properties properties;
		try {
			properties = fileManagerService.getProperties(file);
			for (Map.Entry<Object, Object> elemet : updatedProperties.entrySet()) {
				String value = String.valueOf(elemet.getValue());
				String key = String.valueOf(elemet.getKey());
				if (properties.containsKey(elemet.getKey())) {
					properties.setProperty(key,value);
				}
			}
			return fileManagerService.writeFile(properties, file);
		} catch (PropertiesException e) {
			LOGGER.error("Error al setear nuevas propiedades, causa: ", e);
		}
		
		return false;
	}

	@Override
	public boolean setNewPropertiesFromFile(Properties fromProperties, String toFile) {
		Properties toProperties;
		try {
			toProperties = fileManagerService.getProperties(toFile);
			for (Map.Entry<Object, Object> elemet : fromProperties.entrySet()) {
				String value = String.valueOf(elemet.getValue());
				String key = String.valueOf(elemet.getKey());
				if (!toProperties.containsKey(elemet.getKey())) {
					toProperties.setProperty(key,value);
				}
			}
			return fileManagerService.writeFile(toProperties, toFile);
		} catch (PropertiesException e) {
			LOGGER.error("Error al setear nuevas propiedades, causa: ", e);
		}
		return false;
	}

	@Override
	public boolean mergeProperties(Properties fromProperties, String toFile) {
		Properties toProperties;
		try {
			toProperties = fileManagerService.getProperties(toFile);
			for (Map.Entry<Object, Object> elemet : fromProperties.entrySet()) {
				String value = String.valueOf(elemet.getValue());
				String key = String.valueOf(elemet.getKey());
				if (!toProperties.containsKey(elemet.getKey())) {
					toProperties.setProperty(key,value);
				} else {
					toProperties.put(key,value);
				}
			}
			return fileManagerService.writeFile(toProperties, toFile);
		} catch (PropertiesException e) {
			LOGGER.error("Error al setear nuevas propiedades, causa: ", e);
		}
		return false;
	}

}
