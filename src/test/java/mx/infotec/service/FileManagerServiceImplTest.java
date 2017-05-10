package mx.infotec.service;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import junit.framework.Assert;
import mx.infotec.PropertiesUtilApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(PropertiesUtilApplication.class)
public class FileManagerServiceImplTest {
	
	@Autowired
	private ManagePropertiesService managePropertiesService;
	
	@SuppressWarnings("deprecation")
	@Test
//	@Ignore
	public void testManageFiles() {
		Assert.assertTrue(managePropertiesService.setNewPropertie("key3.test","value3","/home/rodrigo/file.properties"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testManageFilesUpdate() {
		
		Assert.assertTrue(managePropertiesService.updatePropertie("key3.test","value4","/home/rodrigo/file.properties"));
	}

}
