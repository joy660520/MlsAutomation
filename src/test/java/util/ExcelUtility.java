package util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private static XSSFWorkbook excelWBook;
	private static XSSFSheet excelWSheet;

	/*
	 * 指定使用哪個表單：設置文件路徑 params-Excel路徑和表單名字
	 */
	public static void setExcelFile(String path, String sheetName) throws Exception { //指名excel文件
		try {
			FileInputStream excelFile = new FileInputStream(path); // Excel文件
			excelWBook = new XSSFWorkbook(excelFile); // 訪問excel表
			excelWSheet = excelWBook.getSheet(sheetName); //訪問Sheet
		} catch (Exception e) {
			throw (e);
		}
	}

	public static String[][] getTestData(String tableName) {
		String[][] testData = null;
		try {
			DataFormatter formatter = new DataFormatter(); // 處理單元格中的數字和字符串

			// boundaryCells 數組裡放的是第一列和最後一列兩個元素
			// 根據第一列和最後一列，可以知道讀哪些行數據
			XSSFCell[] boundaryCells = findCells(tableName); // 做為findCells這個方法的參數，這個方法是用來找出開始與結束的兩個單元格
			XSSFCell startCell = boundaryCells[0];
			XSSFCell endCell = boundaryCells[1];

			// 為起始/結束賦值
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			testData = new String[endRow - startRow + 1][endCol - startCol + 1]; // 建立二維物件

			for (int i = startRow; i < endRow + 1; i++) { //讀取每一行
				for (int j = startCol; j < endCol + 1; j++) {
					// 讀取每一行裡每一列的數據
					Cell cell = excelWSheet.getRow(i).getCell(j);
					testData[i - startRow][j - startCol] = formatter.formatCellValue(cell);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	public static XSSFCell[] findCells(String tableName) { // 找出表裡的那兩格invalid_login(tableName就是表裡的invalid_login)
		DataFormatter formatter = new DataFormatter();
		String pos = "begin"; // 初始化，檢查tableName這個變量的值
		XSSFCell[] cells = new XSSFCell[2]; // 定義一個數組，放的就是單元格類型數組，長度為2，就是兩個單元格
		for (Row row : excelWSheet) {
			for (Cell cell : row) {
				if (tableName.equals(formatter.formatCellValue(cell))) {
					if (pos.equalsIgnoreCase("begin")) {
						cells[0] = (XSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (XSSFCell) cell;
					}
				}
			}

		}
		return cells;
	}

}
