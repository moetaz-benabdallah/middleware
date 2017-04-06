package com.efacil.connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * FTPConnector Class.
 * A test class to upload files to an ftp server.
 * @version 1.0 04 Mar 2017
 * @author Moatez Ben Abdallah
 **/
public class FTPConnector {

	private static final int BUFFER_SIZE = 4096;
	
	public void uploadToFTPServer(){
		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "speedtest.tele2.net";
        String user = "anonymous";
        String pass = "anonymous";
        String filePath = "touch.txt";
        String uploadPath = "upload/touch.txt";
 
        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);
 
        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);
 
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            inputStream.close();
            outputStream.close();
 
            System.out.println("File uploaded");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
