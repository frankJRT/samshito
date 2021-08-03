package com.hito.am.web.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PythonCallerService {
	private static final Logger logger = LoggerFactory.getLogger(CarteraService.class);

	@Value("${python.executer.prodDir}")
	private String prodPath;

	@Value("${python.executer.command}")
	private String executerCommand;

	Process mProcess;

	public String ejecuteScript(String pathScript) {
		logger.info("PythonCallerService : ejecuteScript   " + executerCommand +" "+ prodPath + pathScript);
		Process process;
		try {
			// process = Runtime.getRuntime().exec(new String[] { "python
			// C:\\Desarrollo\\test.py", "arg1", "arg2" });
			process = Runtime.getRuntime().exec(executerCommand +" "+ prodPath + pathScript);
			mProcess = process;
		} catch (Exception e) {
			System.out.println("Exception Raised" + e.toString());
		}

		try {
			InputStream stdout = mProcess.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("stdout: " + line);
			}
		} catch (IOException | NullPointerException e) {
			System.out.println("Exception in reading output" + e.toString());
		}
		return null;
	}

}
