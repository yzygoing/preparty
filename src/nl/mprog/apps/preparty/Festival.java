package nl.mprog.apps.preparty;

import java.io.Serializable;

public class Festival implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	String name;
	String date;
	String time;
	String genre;
	String location;
	
	// constructor
	public Festival() {}
}
