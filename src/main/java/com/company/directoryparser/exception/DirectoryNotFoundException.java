package com.company.directoryparser.exception;

public class DirectoryNotFoundException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DirectoryNotFoundException(String msg)
	{
		throw new RuntimeException(msg);
	}


}
