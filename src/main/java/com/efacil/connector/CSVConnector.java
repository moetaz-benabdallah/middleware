package com.efacil.connector;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * CSVConnector Class.
 * A test class to write in CSV file.
 * @version 1.0 04 Mar 2017
 * @author Moatez Ben Abdallah
 **/
public class CSVConnector{
	
	public Boolean writeToCSV() throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(new File("test.csv"));
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("id");
        sb.append(',');
        sb.append("Name");
        sb.append('\n');

        sb.append("1");
        sb.append(',');
        sb.append("Prashant Ghimire");
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
        return true;
	}

}
