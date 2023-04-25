package genericLib;

import java.io.FileInputStream;
import java.util.Dictionary;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {
	public static Dictionary<String, String> getExcelFile(String Path, String SheetName) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			@SuppressWarnings("resource")
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
			XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = ExcelWSheet.getLastRowNum();
			// System.out.println("Row Count = "+rowCount);
			for (int i = 0; i < rowCount; i++) {
				XSSFRow row = ExcelWSheet.getRow(i);
				int colCount = row.getPhysicalNumberOfCells();
				// System.out.println("Column Count = "+colCount);
				String a[] = new String[2];
				for (int j = 0; j < colCount; j++) {
					XSSFCell cell = row.getCell(j);
					// System.out.println(cell);
					String getcelldata = cell.getStringCellValue();
					a[j] = getcelldata;
				}
				String parameterName = a[0];
				String parameterValue = a[1];
//				System.out.println("TestParameter Name = " + parameterName);
//				System.out.println("TestParameter Value = " + parameterValue);
				BaseTest.dataList.put(parameterName, parameterValue);
				// System.out.println("Parameter Value = "+dict.get(parameterName));
			}
		} catch (Exception e) {
			throw (e);
		}
		return BaseTest.dataList;
	}

//	public static Dictionary<String, String> readPageProperties(String Path, String SheetName) throws Exception {
//		try {
//			FileInputStream ExcelFile = new FileInputStream(Path);
//		// Access the required test data sheet
//		//	System.out.println("Excel Path = "+Path);
//		//	System.out.println("Excel Sheet Name = "+SheetName);
//			@SuppressWarnings("resource")
//			HSSFWorkbook ExcelWBook = new HSSFWorkbook(ExcelFile);
//			System.out.println("Sheet name for Page Objects "+SheetName);
//			HSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);
//			int rowCount = ExcelWSheet.getLastRowNum();
//			//System.out.println("Row Count = "+rowCount);
//			for(int i=0;i<rowCount;i++) {
//				HSSFRow row = ExcelWSheet.getRow(i); 
//				int colCount = row.getPhysicalNumberOfCells();
//		//		System.out.println("Column Count = "+colCount);
//				String a[]= new String[2];
//				for (int j=0;j<colCount;j++) {
//					HSSFCell cell = row.getCell(j);
//		//			System.out.println(cell);
//					String getcelldata = cell.getStringCellValue();
//					a[j] = getcelldata;
//				}
//				String parameterName = a[0];
//				String parameterValue = a[1];
////				System.out.println("TestParameter Name = "+parameterName);
////				System.out.println("TestParameter Value = "+parameterValue);
//				BaseTest.dictPageObjects.put(parameterName, parameterValue);
////				System.out.println("Parameter Value = "+GlobalVariables.dictPageObjects.get(parameterName));
//			}
//		}
//			catch (Exception e){
//				throw (e);
//			}
//		return BaseTest.dictPageObjects;
//	}
}
