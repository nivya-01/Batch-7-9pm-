package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExceFileUtility {
	
	public String toGetDataFromExceFile(String sheetname, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\create_campaign.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;	
	}
	public String toGetDataFromExcelFileForProducts(String sheetname, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\add_products.xlsx");
		Workbook wb1 = WorkbookFactory.create(fis1);
		String data1 = wb1.getSheet(sheetname).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb1.close();
		return data1;
	}
	public int toGetLastRowNum(String sheetname) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\create_campaign.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetname).getLastRowNum();
		wb.close();
		return rowCount;
	}


}
