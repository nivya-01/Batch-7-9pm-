package DDT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataFromMultipeRows {

	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\add_products.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s = wb.getSheet("Multiple_Rows");
		int rowcount = s.getLastRowNum();
		for(int i=1;i<=rowcount;i++)
		{
			Row r = s.getRow(i);
			String prod_cat = r.getCell(0).getStringCellValue();
			String prod_name = r.getCell(1).getStringCellValue();
			System.out.println(prod_cat+"--->"+prod_name);
		}
	}

}
