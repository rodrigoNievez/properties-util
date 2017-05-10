package mx.infotec.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("fileManagerService")
public class FileManagerServiceImpl implements FileManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagerServiceImpl.class);
	
	@Value("${updated.comment}")
	private String updatedComment;

	@Override
	public Properties getProperties(String file) {
		File propertiesFile = new File(file);
		Properties properties = null;
		FileInputStream fileInput = null;
		if (propertiesFile.exists()) {
			try {
				fileInput = new FileInputStream(propertiesFile);
				properties = new Properties();
				properties.load(fileInput);
				fileInput.close();
			} catch (FileNotFoundException e) {
				LOGGER.error("Error al leer el archivo, causa: ", e);
			} catch (IOException e) {
				LOGGER.error("Error al leer las propiedades, causa: ", e);
			}
		}
		
		return properties;
	}

	@Override
	public boolean writeFile(Properties properties, String outputFile) {
		File file = new File(outputFile);		
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			Properties sortedProperties = sortProperties(properties);
			sortedProperties.store(fileOutput, updatedComment);
			fileOutput.close();
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("Error al leer el archivo, causa: ", e);
		} catch (IOException e) {
			LOGGER.error("Error al guardar el archivo de propiedades, causa: ", e);
		} 

		return false;
	}
	
	private Properties sortProperties(Properties properties) {
		Properties sortedProperties = new Properties() {
			private static final long serialVersionUID = 1L;

			@Override 
			public synchronized Enumeration<Object> keys() {
				return Collections.enumeration(new TreeSet<Object>(super.keySet()));
			}
		};
		sortedProperties.putAll(properties);
		return sortedProperties;
	}

}
