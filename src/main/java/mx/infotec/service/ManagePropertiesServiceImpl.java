package mx.infotec.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;

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
		String addPropertie = String.format("\n%s = %s", key, value);

		try {
			Files.write(Paths.get(file), addPropertie.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			LOGGER.error("Error al insertar o actualizar contenido del archivo, causa: ", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean setNewProperties(Map<String, String> properties, String file) {
		String addPropertie;
		try {
			for (Map.Entry<String, String> element : properties.entrySet()) {
				addPropertie = String.format("\n%s:%s", element.getKey(), element.getValue());
				Files.write(Paths.get(file), addPropertie.getBytes(), StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			LOGGER.error("Error al insertar contenido del archivo, causa: ", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean updatePropertie(String key, String value, String file) {
		Map<String, String> properties = fileManagerService.getProperties(file);
		if (properties != null) {
			try {
				if (properties.containsKey(key)) {
					String addPropertie = String.format("%s=%s", key, value);
					Files.setAttribute(Paths.get(file), addPropertie, true);
				} else {
					LOGGER.error(String.format("El archivo, no contiene la propiedad %s", key));
					return false;
				}
			} catch (IOException e) {
				LOGGER.error("Error al insertar o actualizar contenido del archivo, causa: ", e);
				return false;
			}

		}
		return false;
	}

	@Override
	public boolean updateProperties(Map<String, String> properties, String file) {
		try {
			for (Map.Entry<String, String> element : properties.entrySet()) {
				if (properties.containsKey(element.getKey())) {
					String addPropertie = String.format("%s:%s", element.getKey(), element.getValue());
					Files.setAttribute(Paths.get(file), addPropertie, true);
				} else {
					LOGGER.error(String.format("El archivo, no contiene la propiedad %s", element.getKey()));
					return false;
				}
			}
		} catch (IOException e) {
			LOGGER.error("Error al insertar contenido del archivo, causa: ", e);
			return false;
		}
		return false;
	}

}
