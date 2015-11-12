package com.raz7.workbook;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.raz7.json.model.JSONEntry;

/**
 * Class WorkbookManager. Contains creating and managing workbooks
 * functionality.
 */
public class WorkbookManager {

	/**
	 * The value of the {@code timecreated} column.
	 */
	private static final String HEADER_TIMECREATED_VALUE = "timecreated";
	/**
	 * The value of the {@code finger 1} column.
	 */
	private static final int HEADER_FINGER_1_VALUE = 1;
	/**
	 * The value of the {@code finger 2} column.
	 */
	private static final int HEADER_FINGER_2_VALUE = 2;
	/**
	 * The value of the {@code finger 3} column.
	 */
	private static final int HEADER_FINGER_3_VALUE = 3;
	/**
	 * The value of the {@code finger 4} column.
	 */
	private static final int HEADER_FINGER_4_VALUE = 4;
	/**
	 * The value of the {@code finger 5} column.
	 */
	private static final int HEADER_FINGER_5_VALUE = 5;

	/**
	 * Default date format for the {@code timecreated} column. If we need to
	 * also display the milliseconds we will use the
	 * {@code yyyy-MM-dd HH:mm:ss,SSS} format.
	 */
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * The default time zone.
	 */
	private static final String DEFAULT_TIME_ZONE = "UTC+2";
	/**
	 * The default decimal format.
	 */
	private static final String DEFAULT_DECIMAL_FORMAT = ".##";

	/**
	 * The name of the file associated to this {@link WorkbookManager}.
	 */
	private String filename;

	/**
	 * Creates a new instance of {@link WorkbookManager}.
	 */
	public WorkbookManager() {
	}

	/**
	 * Creates a new instance of {@link WorkbookManager} based on the provided
	 * file name.
	 * 
	 * @param filename
	 *            The name of the file associated to this workbook manager.
	 */
	public WorkbookManager(String filename) {
		this.filename = filename;
	}

	/**
	 * Creates a new workbook in the associated file based on the list of
	 * {@link JSONEntry}.
	 * 
	 * @param jsonEntries
	 *            The list of {code JsonEntry} used to create the workbook.
	 */
	public void createWorkbook(List<JSONEntry> jsonEntries) {
		Map<String, List<JSONEntry>> sheetsMap = groupByUsername(jsonEntries);

		if (sheetsMap.isEmpty()) {
			return;
		}

		// create new workbook
		Workbook workbook = new XSSFWorkbook();

		// for each username we create a new work-sheet
		for (Map.Entry<String, List<JSONEntry>> workSheetEntry : sheetsMap
				.entrySet()) {
			Sheet sheet = workbook.createSheet(workSheetEntry.getKey());
			createHeaderRow(sheet);

			int rowIndex = 1;
			// save last time created to know when we need to create a new row
			String lastTimecreated = null;
			for (JSONEntry jsonEntry : workSheetEntry.getValue()) {

				Row row = null;

				if (jsonEntry.getTimecreated().equals(lastTimecreated)) {
					row = sheet.getRow(rowIndex - 1);
				} else {
					// create a new row if we have different timestamp
					row = createRow(sheet, rowIndex++,
							jsonEntry.getTimecreated());
					lastTimecreated = jsonEntry.getTimecreated();
				}

				updateForce(row, jsonEntry);
			}

		}

		WorkbookFileHelper.writeToFile(workbook, filename);
	}

	/**
	 * Creates the header row for the specified {@link Sheet}.
	 * 
	 * @param sheet
	 *            The {@code Sheet} for which we are creating the header row.
	 * @return The newly created {@code Row}.
	 */
	private Row createHeaderRow(Sheet sheet) {
		Row headerRow = createEmptyRow(sheet, 0);

		// set the value of the header columns
		headerRow.getCell(0).setCellValue(HEADER_TIMECREATED_VALUE);
		headerRow.getCell(1).setCellValue(HEADER_FINGER_1_VALUE);
		headerRow.getCell(2).setCellValue(HEADER_FINGER_2_VALUE);
		headerRow.getCell(3).setCellValue(HEADER_FINGER_3_VALUE);
		headerRow.getCell(4).setCellValue(HEADER_FINGER_4_VALUE);
		headerRow.getCell(5).setCellValue(HEADER_FINGER_5_VALUE);

		return headerRow;
	}

	/**
	 * Creates a new row for the specified {@link Sheet} and row index.
	 * 
	 * @param sheet
	 *            The {@code Sheet} for which we are creating the row.
	 * @param index
	 *            The index of the row.
	 * @param timecreated
	 *            The value of the {@code timecreated} row.
	 * @return
	 */
	private Row createRow(Sheet sheet, int index, String timecreated) {
		Row row = createEmptyRow(sheet, index);
		// set the time created for the row
		row.getCell(0).setCellValue(formatDate(timecreated));

		return row;
	}

	/**
	 * Updates the corresponding finger column of the given {@link Row} based on
	 * the provided {@link JSONEntry}.
	 * 
	 * @param row
	 *            The {@code Row} to be updated.
	 * @param jsonEntry
	 *            The {@code JsonEntry} used to update the row.
	 */
	private void updateForce(Row row, JSONEntry jsonEntry) {
		// update the corresponding finger column
		int columnIndex = Integer.parseInt(jsonEntry.getFinger());
		String force = new DecimalFormat(DEFAULT_DECIMAL_FORMAT).format(Double
				.valueOf(jsonEntry.getForce()));

		row.getCell(columnIndex).setCellValue(Double.valueOf(force));
	}

	/**
	 * Creates an empty {@link Row}.
	 * 
	 * @param sheet
	 *            The {@code Sheet} for which we are creating thr row.
	 * @param index
	 *            The index of the row.
	 * @return The newly created row.
	 */
	private Row createEmptyRow(Sheet sheet, int index) {
		Row row = sheet.createRow(index);

		row.createCell(0);
		row.createCell(1);
		row.createCell(2);
		row.createCell(3);
		row.createCell(4);
		row.createCell(5);

		return row;
	}

	/**
	 * Groups the list of {@link JSONEntry} for each distinct username.
	 * 
	 * @param jsonEntries
	 *            The list of {@code JsonEntry} to be separated.
	 * @return The {@link Map} containing the entries separated by distinct
	 *         username.
	 */
	private Map<String, List<JSONEntry>> groupByUsername(
			List<JSONEntry> jsonEntries) {
		Map<String, List<JSONEntry>> groupByMap = new LinkedHashMap<String, List<JSONEntry>>();

		for (JSONEntry jsonEntry : jsonEntries) {
			String key = jsonEntry.getUsername();

			if (groupByMap.containsKey(key)) {
				List<JSONEntry> keyEntries = groupByMap.get(key);
				keyEntries.add(jsonEntry);
			} else {
				List<JSONEntry> keyEntries = new ArrayList<JSONEntry>();
				keyEntries.add(jsonEntry);

				groupByMap.put(key, keyEntries);
			}
		}

		return groupByMap;
	}

	/**
	 * Formats the given string date.
	 * 
	 * @param date
	 *            The date to be formatted.
	 * @return The formatted date.
	 */
	private String formatDate(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
		calendar.setTimeInMillis(Long.valueOf(date) * 1000L);

		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(calendar
				.getTime());
	}
}
