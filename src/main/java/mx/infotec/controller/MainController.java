package mx.infotec.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		boolean success = propertiesFileService.mergeMultiplePropertiesFromFile(fileModel.getFileFrom(),
				fileModel.getFileTo());
		if (success) {
			return "results/success";
		}
		return "results/error";
	}

	@GetMapping("/merge_skip")
	public String mergeSkip(Model model) {
		model.addAttribute(new FileModel());
		return "merge_skip";
	}

	@PostMapping("/merge_skip")
	public String mergeSkipSubmit(@ModelAttribute FileModel fileModel) {
		boolean success = propertiesFileService.mergeWithSkipProperties(fileModel.getFileFrom(),
				fileModel.getFileTo(), fileModel.getFileSkip());
		if (success) {
			return "results/success";
		}
		return "results/error";
	}

}
