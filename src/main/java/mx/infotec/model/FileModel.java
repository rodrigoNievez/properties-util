package mx.infotec.model;

import java.io.Serializable;

public class FileModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileFrom;
	
	private String fileTo;

	public String getFileFrom() {
		return fileFrom;
	}

	public void setFileFrom(String fileFrom) {
		this.fileFrom = fileFrom;
	}

	public String getFileTo() {
		return fileTo;
	}

	public void setFileTo(String fileTo) {
		this.fileTo = fileTo;
	}
	
	
}
