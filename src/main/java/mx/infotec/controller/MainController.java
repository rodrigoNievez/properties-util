package mx.infotec.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.infotec.model.FileModel;
import mx.infotec.service.ManagePropertiesFilesService;

@Controller
public class MainController {

	@Autowired
	private ManagePropertiesFilesService propertiesFileService;
	
	@GetMapping("/merge_files")
	public String mergeFiles(Model model) {
		model.addAttribute(new FileModel());
		return "merge_files";
	}
	
	
	@PostMapping("/merge_files")
	public String mergeFilesSubmit(@ModelAttribute FileModel fileModel) {
		boolean success = propertiesFileService.mergeMultiplePropertiesFromFile(fileModel.getFileFrom(), fileModel.getFileTo());
		if (success) {
			return "results/success";
		}
		return "reults/error";
	}
	
	@PostMapping("/test")
	public String testFiles(@RequestParam("testFile")File testFile) {
		System.out.println(testFile.getAbsolutePath());
		return "error";
	}

}
