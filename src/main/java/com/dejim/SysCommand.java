package com.dejim;

import java.io.*;

public class SysCommand {

	public SysCommand() {

	}

	boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

	public static Process process;
	
	public String process(String command, String path) {
		StringBuffer response = new StringBuffer();
		String line;
		
		System.out.print(command);
		if (command.equals("repose")) {			
			command = "java -jar " + path + "valve-9.1.0.0-all.jar -c " + path + "repose";
		}

		try {
			if (isWindows) {
				process = Runtime.getRuntime().exec(String.format(command));
			} else {
				process = Runtime.getRuntime().exec(String.format(command));
			}
			process.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = reader.readLine()) != null) {
				response.append(line + "\n");
				System.out.print(line + "\n");
			}
			while ((line = error.readLine()) != null) {
				response.append(line + "\n");
				System.out.print(line + "\n");
			}
			
			process.destroy();
			process.getInputStream().close();
			process.getOutputStream().close();
			process.getErrorStream().close(); 
		    
		} catch (IOException e1) {
		} catch (InterruptedException e2) {
		}
		
		return response.toString();
	}
	
	public String stopProcess(String command) throws IOException {	
		process.destroy();		
		process.getInputStream().close();
		process.getOutputStream().close();
		process.getErrorStream().close(); 
		return "Repose Stopped";
	}
}
