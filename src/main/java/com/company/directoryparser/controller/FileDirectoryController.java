package com.company.directoryparser.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.directoryparser.bean.Directory;
import com.company.directoryparser.exception.DirectoryNotFoundException;
import com.company.directoryparser.util.FileConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * This class is basically the controller of the REST calls
 * This supports two REST endpoints as part of assignment
 * @author raghosh
 *
 */
@CrossOrigin(origins = "http://localhost:8585")
@RestController()
@RequestMapping(value = "/controller" )
public class FileDirectoryController {
	private static final Logger logger = LoggerFactory.getLogger(FileDirectoryController.class);

	@RequestMapping(value = "/getdir", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * This method is called for listing directories and files
	 * 
	 * @param dirPath : Complete path needs to be passed as part of request body
	 * Below is example
	 * {
  	 * "path":"C:\\\\sample"
	 *  }
	 * @return
	 */
	public Directory getFilesDir(@RequestBody String dirPath) {
		String path = "";
		logger.info("dir", "calling get files dir");
		ObjectMapper obj = new ObjectMapper();
		try {

			if (null!=dirPath && dirPath.contains("path") && !"".equals(dirPath)) {
				Map<String, String> pathMap = obj.readValue(dirPath, Map.class);
				path = pathMap.get(FileConstants.PATH);
			} else if(null!=dirPath && !"".equals(dirPath)){
				path = dirPath;
				path=path.substring(1, path.length()-1);
			}else
			{
				throw new DirectoryNotFoundException("Request body cannot be null or empty");
			}
			if(!path.contains(File.separator))
			{
				throw new DirectoryNotFoundException("Directory or file path is expected !");	
			}
			logger.debug("path found in request body value= " + path);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			logger.error("failed to find the path in the request body , Please check request body passed");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error("failed to parse JSON request, Please check if the request body format is correct");
		}
		Directory dir = new Directory();
		// List<Directory> dirList = new ArrayList<Directory>();
		dir=processDir(path, dir);
		return dir;
	}

	
	private Directory processDir(String dirPath, Directory dir) {

		if (null != dirPath && !dirPath.isEmpty()) {

			File folder = new File(dirPath);
			dir.setName(folder.getName());
			dir.setDirPath(folder.getAbsolutePath());
			dir.setDirSize(folder.getUsableSpace());
			List<Directory> dirList = new ArrayList<Directory>();
			List<com.company.directoryparser.bean.File> fileList = new ArrayList<com.company.directoryparser.bean.File>();
			// List<Directory> dirList = new ArrayList<Directory>();
			if (folder.isDirectory()) {
				dir.setDir(true);
				File[] fileNames = folder.listFiles();

				for (File file : fileNames) {
					if (file.isDirectory()) {

						dirList.add(processDir(file.getAbsolutePath(), new Directory()));
					} else {
						com.company.directoryparser.bean.File file1 = new com.company.directoryparser.bean.File();
						file1.setName(file.getName());
						file1.setDescription(file.getName());
						file1.setPath(file.getAbsolutePath());
						file1.setSize(file.getUsableSpace());
						fileList.add(file1);
					}

				}
				dir.setFileList(fileList);
				dir.setDirList(dirList);

			}

		} else {
			// throw error msg path cannot be empty
		}
		return dir;
	}

	@RequestMapping(value = "/getfile", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * This method is called for listing file details and permission
	 * 
	 * @param filePath : Complete path needs to be passed as part of request body
	 * Below is example
	 * {
  	 * "path":"C:\\\\sample\\fike.txt"
	 *  }
	 * @return
	 */
	public com.company.directoryparser.bean.File getFile(@RequestBody String filePath) {
		String path = "";
		logger.info("getfile", "calling get files ");
		com.company.directoryparser.bean.File retFile=new com.company.directoryparser.bean.File();
		ObjectMapper obj = new ObjectMapper();
		try {
			
			if (null!=filePath && filePath.contains("path") && !"".equals(filePath)) {
				Map<String, String> pathMap = obj.readValue(filePath, Map.class);
				path = pathMap.get(FileConstants.PATH);
			} else if(null!=filePath && !"".equals(filePath)){
				path = filePath;
				path=path.substring(1, path.length()-1);
			}else
			{
				throw new DirectoryNotFoundException("Request body cannot be null or empty");
			}
			
			if(!path.contains(File.separator))
			{
				throw new DirectoryNotFoundException("Directory or file path is expected !");	
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(path);
		if (null != file && file.isFile() && file.exists()) {
			retFile.setName(file.getName());
			try {
				retFile.setDescription("File canonical path :"+file.getCanonicalPath()+" File permission :Read="+file.canRead()+" ; write="+file.canWrite()+ " ; execute="+file.canExecute());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retFile.setSize(file.getUsableSpace());
			retFile.setPath(file.getAbsolutePath());
		} else {
			
			return null;
		}
		// List<Directory> dirList = new ArrayList<Directory>();
		return retFile;
	}
}
