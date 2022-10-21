package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
XSSFWorkbook wb;
//constructor for reading excel path
public ExcelFileUtil(String excelpath)throws Throwable
{
	FileInputStream fi = new FileInputStream(excelpath);
	wb = new XSSFWorkbook(fi);
}
//method for row count
public int rowCount(String sheetName)
{
	return wb.getSheet(sheetName).getLastRowNum();
}
//method for cell count
public int cellCount(String sheetName)
{
	return wb.getSheet(sheetName).getRow(0).getLastCellNum();
}
//method for cell data
public String getCellData(String sheetName,int row,int column)
{
	String data="";
	if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata =(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		data=String.valueOf(celldata);
	}
	else
	{
		data =wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//method for setcelldata
public void setCellData(String sheetName,int row,int column,String status,String writeExcel)throws Throwable
{
	//get sheet from wb
	XSSFSheet ws =wb.getSheet(sheetName);
	//get row from sheet
	XSSFRow rowNum =ws.getRow(row);
	//create cell
	XSSFCell cell =rowNum.createCell(column);
	//write status in a cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
	XSSFCellStyle style =wb.createCellStyle();
	XSSFFont font =wb.createFont();
	font.setColor(IndexedColors.GREEN.getIndex());
	font.setBold(true);
	font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	style.setFont(font);
	rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style =wb.createCellStyle();
		XSSFFont font =wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		XSSFCellStyle style =wb.createCellStyle();
		XSSFFont font =wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fo = new FileOutputStream(writeExcel);
	wb.write(fo);
}
	public static void main(String[] args)throws Throwable {
		ExcelFileUtil xl = new ExcelFileUtil("D://Book1.xlsx");
		//count no of rows in sheet
		int rc =xl.rowCount("Login");
		//count no of cells in first row
		int cc =xl.cellCount("Login");
		System.out.println(rc+"   "+cc);
		for(int i=1;i<=rc;i++)
		{
			String user =xl.getCellData("Login", i, 0);
			String pass =xl.getCellData("Login", i, 1);
			System.out.println(user+"   "+pass);
			//write as pass into results cell
			//xl.setCellData("Login", i, 2, "Pass", "D://Results.xlsx");
			//xl.setCellData("Login", i, 2, "Fail", "D://Results.xlsx");
			xl.setCellData("Login", i, 2, "Blocked", "D://Results.xlsx");
			
		}
		

	}

}

