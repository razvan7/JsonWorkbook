package com.raz7.workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Class WorkbookFileHelper. Contains functionality for working with workbook
 * files.
 */
public class WorkbookFileHelper {

	/**
	 * The value of the {@code XLSX} file extension.
	 */
	private static final String XLSX_FILE_EXTENSION = ".xlsx";
	/**
	 * The default date format.
	 */
	private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * Private constructor since this is a helper.
	 */
	private WorkbookFileHelper() {
	}

	/**
	 * Writes the content of the given {@link Workbook} to the provided file.
	 * 
	 * @param workbook
	 *            The {@code Workbook} to be written.
	 * @param filename
	 *            The name of the file in which we are writing the workbook.
	 */
	public static void writeToFile(Workbook workbook, String filename) {
		File file = new File(filename);

		try {
			if (file.createNewFile()) {
				OutputStream outputStream = new FileOutputStream(file);
				workbook.write(outputStream);
				outputStream.flush();
				outputStream.close();
			}
		} catch (IOException e) {
			// TODO add logging/handle exception
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Returns the default name for a workbook file.
	 * 
	 * @return The default name for a workbook file.
	 */
	public static String getDefaultWorkbookFilename() {
		return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD).format(new Date())
				+ XLSX_FILE_EXTENSION;
	}

}
