package com.opentext.lhnqa.api.lib.utils;

import java.io.File;

import javax.annotation.Nonnull;

public class FileDetails {

	public String fileName;
	public File file;
	public String mimeType;
	public String controlName;
	
	public FileDetails(@Nonnull String fileName ,@Nonnull  File file,@Nonnull String mimeType,@Nonnull String controlName) {
		this.fileName = fileName;
		this.file = file;
		this.mimeType = mimeType;
		this.controlName = controlName;
	}
	
}
