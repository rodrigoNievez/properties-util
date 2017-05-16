package mx.infotec.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.exception.PropertiesException;

@Service
public class ManagePropertiesFilesServiceImpl implements ManagePropertiesFilesService {

	private Logger LOGGER = LoggerFactory.getLogger(ManagePropertiesFilesServiceImpl.class);

	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private ManagePropertiesService managePropertiesService;

	@Override
	public boolean createPropertiesFile(Properties properties, String fileProperties) {
		File generateFile = new File(fileProperties);
		try {
			if (!generateFile.exists()) {
				if (!createFile(generateFile)) {
					return false;
				}
			}
			return fileManagerService.writeFile(properties, fileProperties);
		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);
		}

		return false;
	}

	@Override
	public boolean createMultiplePropertiesFile(Properties properties, String fileContiner) {
		File file = new File(fileContiner);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String filePath = scanner.nextLine();
				File createFile = new File(filePath);
				if (createFile(createFile)) {
					fileManagerService.writeFile(properties, filePath);
				}
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al crear el archivo de propiedades, causa: ", e);

		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);

		}
		return false;
	}

	@Override
	public boolean copyPropertiesFile(String from, String to) {
		try {
			Properties properties = fileManagerService.getProperties(from);
			File toFile = new File(to);
			if (createFile(toFile)) {
				return managePropertiesService.setNewProperties(properties, to);
			}
			
		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);
		}
		return false;
	}

	@Override
	public boolean copyMultiplePropertiesFile(String from, String fileContiner) {
		File file = new File(fileContiner);
		try {
			Properties properties = fileManagerService.getProperties(from);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String filePath = scanner.nextLine();
				File createFile = new File(filePath);
				if (createFile(createFile)) {
					fileManagerService.writeFile(properties, filePath);
				}
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al crear el archivo de propiedades, causa: ", e);

		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);

		}
		return false;
	}

	@Override
	public boolean updateProperties(String from, String to) {
		Properties properties;
		try {
			properties = fileManagerService.getProperties(from);
			return managePropertiesService.updateProperties(properties, to);
		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);
		}
		return false;
	}

	@Override
	public boolean updateMutipleProperties(String from, String fileContiner) {
		File file = new File(fileContiner);
		try {
			Properties properties = fileManagerService.getProperties(from);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				managePropertiesService.updateProperties(properties, scanner.nextLine());
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al crear el archivo de propiedades, causa: ", e);

		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);

		}
		return false;
	}


	@Override
	public boolean setNewPropertiesFromFile(String from, String fileContainer) {
		File file = new File(fileContainer);
		try {
			Properties properties = fileManagerService.getProperties(from);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				managePropertiesService.setNewPropertiesFromFile(properties, scanner.nextLine());
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al crear el archivo de propiedades, causa: ", e);

		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);

		}
		return false;
	}
	
	@Override
	public boolean mergePropertiesFromFile(String from, String to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mergeMultiplePropertiesFromFile(String from, String fileContainer) {
		File file = new File(fileContainer);
		try {
			Properties properties = fileManagerService.getProperties(from);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				managePropertiesService.mergeProperties(properties, scanner.nextLine());
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al crear el archivo de propiedades, causa: ", e);

		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);

		}
		
		return false;
	}
	
	@Override
	public boolean mergeWithSkipProperties(String from, String fileContainer, String skipFile) {
		File file = new File(fileContainer);
		try {
			Properties fromProperties = fileManagerService.getProperties(from);
			Properties skipProperties = fileManagerService.getProperties(skipFile);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				managePropertiesService.mergePropertiesWithSkipProperties(fromProperties, skipProperties, scanner.nextLine());
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al crear el archivo de propiedades, causa: ", e);

		} catch (PropertiesException e) {
			LOGGER.error("Error al escribir las propiedades, causa: ", e);

		}
		
		return false;
			
	}
	private boolean createFile(File file) {
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
			return true;
		} catch (IOException e) {
			LOGGER.error("Error al generar el archivo de propiedades, causa: ", e);
		}
		return false;
	}

}
