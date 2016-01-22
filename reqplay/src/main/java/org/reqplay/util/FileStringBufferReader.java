package org.reqplay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.reqplay.ReqPlayException;

/**
 * Reads a file and returns a <code>StringBuffer</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 21/06/2012
 * 
 */
public class FileStringBufferReader {

	public StringBuffer readFile(File file) {
		FileReader fileReader;
		BufferedReader buffReader;
		String line = null;
		StringBuffer sb;
		try {
			fileReader = new FileReader(file);
			buffReader = new BufferedReader(fileReader);
			sb = new StringBuffer();
			line = buffReader.readLine();
			while (line != null) {
				sb.append(line);
				line = buffReader.readLine();
				if (line != null) {
					sb.append("\n");
				} else {
					break;
				}
			}
			fileReader.close();
			buffReader.close();
			return sb;
		} catch (FileNotFoundException e) {
			throw new ReqPlayException(e);
		} catch (IOException e) {
			throw new ReqPlayException(e);
		}
	}

}
