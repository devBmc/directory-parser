package com.company.directoryparser.bean;

import java.io.File;
import java.util.List;

public class Directory {
	
	private String name;
	private String description;
	private List<com.company.directoryparser.bean.File> fileList;
	private List<Directory> dirList;
	private long dirSize;
	private String dirPath;
	private boolean isDir;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<com.company.directoryparser.bean.File> getFileList() {
		return fileList;
	}
	public void setFileList(List<com.company.directoryparser.bean.File> fileList) {
		this.fileList = fileList;
	}
	public List<Directory> getDirList() {
		return dirList;
	}
	public void setDirList(List<Directory> dirList) {
		this.dirList = dirList;
	}
	
	@Override
	public String toString()
	{
		return " name="+name+" description="+description+"directories"+dirList;
	}
	public long getDirSize() {
		return dirSize;
	}
	public void setDirSize(long dirSize) {
		this.dirSize = dirSize;
	}
	public String getDirPath() {
		return dirPath;
	}
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
	public boolean isDir() {
		return isDir;
	}
	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

}
