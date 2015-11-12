package com.raz7.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.raz7.json.JSONParser;
import com.raz7.json.model.JSONEntry;
import com.raz7.workbook.WorkbookManager;

/**
 * Driver code for testing the parsing of JSON and creation of the XLSX
 * workbook.
 */
public class Main {

	private static final String JSON_FILENAME = "json.txt";

	public static void main(String[] args) throws JsonSyntaxException,
			FileNotFoundException, IOException {
		// currently reading the JSON from .txt file for test purposes
		String json = IOUtils.toString(new FileInputStream(new File(
				JSON_FILENAME)));

		JSONParser jsonParser = new JSONParser();
		List<JSONEntry> jsonEntries = jsonParser.parse(json,
				new TypeToken<List<JSONEntry>>() {
				});

		WorkbookManager workbookManager = new WorkbookManager();
		workbookManager.createWorkbook(jsonEntries);
	}
}