package driverfactory;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String inputpath="E:\\9ClockTestNG\\Hybrid_NewFrameWork\\TestInput\\DataEngineHybrid.xlsx";
String outputpath="E:\\9ClockTestNG\\Hybrid_NewFrameWork\\TestOutput\\HybridResult.xlsx";
String TCSheet ="TestCases";
String TSSheet ="TestSteps";
ExtentReports report;
ExtentTest test;
@Test
public void startTest ()throws Throwable
{
	//define path to generate Html report
	report= new ExtentReports("./ExtentReports/HybridReports.html");
	boolean res =false;
	String tcres="";
	//createe object for excel file util
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in both sheet
	int TCCount=xl.rowCount(TCSheet);
	int TSCount=xl.rowCount(TSSheet);
	//iterate all rows in TCSheet
	for(int i=1;i<=TCCount;i++)
	{
		//read test case
		String ModuleName=xl.getCellData(TCSheet, i, 1);
		test=report.startTest(ModuleName);
		//read execution status cell
		String exeFlag =xl.getCellData(TCSheet, i, 2);
		if(exeFlag.equalsIgnoreCase("Y"))
		{
			//read tcid cell from TCSheet
			String tcid =xl.getCellData(TCSheet, i, 0);
			//iterate all rows in TSSheet
			for(int j=1;j<=TSCount;j++)
			{
				//read description cell
				String Description =xl.getCellData(TSSheet, j, 2);
				String tsid =xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					String keyword=xl.getCellData(TSSheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						res =FunctionLibrary.verifyLogin(para1, para2);
						test.log(LogStatus.INFO, Description);
					}
					else if(keyword.equalsIgnoreCase("NewBranch"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						String para3 =xl.getCellData(TSSheet, j, 7);
						String para4 =xl.getCellData(TSSheet, j, 8);
						String para5 =xl.getCellData(TSSheet, j, 9);
						String para6 =xl.getCellData(TSSheet, j, 10);
						String para7 =xl.getCellData(TSSheet, j, 11);
						String para8 =xl.getCellData(TSSheet, j, 12);
						String para9 =xl.getCellData(TSSheet, j, 13);
						FunctionLibrary.clickBranches();
						res =FunctionLibrary.verifyNewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						test.log(LogStatus.INFO, Description);
					}
					else if(keyword.equalsIgnoreCase("BranchUpdate"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						String para3 =xl.getCellData(TSSheet, j, 9);
						String para4 =xl.getCellData(TSSheet, j, 10);
						FunctionLibrary.clickBranches();
						res=FunctionLibrary.verifyBranchUpdate(para1, para2, para3, para4);
						test.log(LogStatus.INFO, Description);
						
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res =FunctionLibrary.verifyLogout();
						test.log(LogStatus.INFO, Description);
					}
					String tsres="";
					if(res)
					{
						//if true write a s pass into status cell
						tsres="Pass";
			          xl.setCellData(TSSheet, j, 4, tsres, outputpath);	
			          test.log(LogStatus.PASS, Description);
					}
					else
					{
						//if false write a s Fail into status cell
						tsres="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						test.log(LogStatus.FAIL, Description);
					}
					tcres=tsres;
				}
				report.endTest(test);
				report.flush();
			}
			//write as tcres into TCSheet
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			//write as blocked into TCSheet which are flag to N
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
	}
}







