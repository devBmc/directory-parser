package com.company.directoryparser.bean;

public class File {
	
	private String name;
	private String description;
	private long size;
	private String path;
	public String getName() {
		return name;
	}
	public File()
	{
		
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
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString()
	{
		return description;
		
	}
	

}
