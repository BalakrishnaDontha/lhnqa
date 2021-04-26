package com.opentext.lhnqa.api.lib.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class Filebuilder {

	  public final List<FileDetails> files = new ArrayList<>();
	  
	  public Filebuilder upload(@Nonnull final String fileName, @Nonnull final String filePath, @Nonnull final String mimeType, @Nonnull final String controlName) throws IOException
	    {
		  	files.add(new FileDetails(fileName,new File(filePath), mimeType,controlName));
	        return this;
	    }
	
}
