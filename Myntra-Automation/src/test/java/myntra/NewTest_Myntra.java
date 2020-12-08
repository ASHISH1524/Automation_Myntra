package myntra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class NewTest_Myntra {
	
	
	WebDriver driver;
	
	
	By profile = By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-headerUser']");
	By login =By.xpath("//a[@class=\'desktop-linkButton\' ]");
	By mobileNo =By.xpath("//input[@class=\"form-control mobileNumberInput\"]");
	By next =By.xpath("//div[@class=\"submitBottomOption\"]");
	By pass =By.xpath("//span[text()=' Password ']");
	By sendpass = By.xpath("//input[@class=\"form-control has-feedback\"]");
	By loginbtn =By.xpath("//button[@class=\"btn primary  lg block submitButton\"]");
	By men =By.xpath("//a[@data-reactid=\"21\" and text()='Men']");
	By levis = By.xpath("//img[@class=\"image-image undefined image-hand\" and @src=\"https://assets.myntassets.com/w_196,c_limit,fl_progressive,dpr_2.0/assets/images/2020/8/31/3fa337a0-c792-4038-8d12-50d463c189a11598892377363-Levis.jpg\"]");
	By pant =By.xpath("(//h4[text()=\"Men 65504 Skinny Fit Jeans\"])[2]");
	By size =By.xpath("(//button[@class=\"size-buttons-size-button size-buttons-size-button-default\"])[2]");
	By bag =By.xpath("//*[@id=\"mountRoot\"]/div/div/div/main/div[2]/div[2]/div[3]/div/div[1]");
	By men2 = By.xpath("(//a[text()='Men'])[1]");
	By uspolo  = By.xpath("(//img[@class=\"image-image undefined image-hand\" ])[1]");
	By shirts=By.xpath("//*[@id=\"mountRoot\"]/div/div/main/div[3]/div[1]/section/div/div[3]/ul/li[1]/label");
	By black_shirt =By.xpath("//*[@id=\"desktopSearchResults\"]/div[2]/section/ul/li[8]/a/div[2]");
	By shirt_size =By.xpath("(//div[@class=\"size-buttons-sku-price\"])[2]");
	By shirt_bag =By.xpath("//*[@id=\"mountRoot\"]/div/div/div/main/div[2]/div[2]/div[3]/div/div[1]");
	
	@BeforeTest
	public void browser_properties()
	{
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@Test(dataProvider ="ExcelData")
	public void testcase1(String url ,String username, String password, String phoneno) throws InterruptedException
	{
		driver.get(url);
		driver.findElement(profile).click();
		driver.findElement(login).click();
		driver.findElement(mobileNo).sendKeys(phoneno);
		driver.findElement(next).click();
		driver.findElement(pass).click();
		//byte[] encodedString=Base64.encodeBase64(password.getBytes());
		//driver.findElement(sendpass).sendKeys(new String(encodedString));
		driver.findElement(sendpass).sendKeys(password);
		driver.findElement(loginbtn).click();
		driver.findElement(men).click();
		driver.findElement(levis).click();
		Thread.sleep(1000);
		driver.findElement(pant).click();
		
		Set<String> windowId = driver.getWindowHandles();     
		Iterator<String> iter = windowId.iterator();            
		String mainWindow = iter.next();                      
		String childWindow = iter.next();	
		String child2Window =iter.next();
		
		driver.switchTo().window(childWindow);
		
		driver.findElement(size).click();
		driver.findElement(bag).click();
		driver.findElement(men2).click();
		driver.findElement(uspolo).click();
		driver.findElement(shirts).click();
		driver.findElement(black_shirt).click();
		
		driver.switchTo().window(child2Window);
		
		driver.findElement(shirt_size).click();
		driver.findElement(shirt_bag).click();
	}
	
	@DataProvider(name="ExcelData")
	public Object[][] getdata() throws BiffException
	{
		Object[][] data = getExcelData("C:\\kjkj\\javaprg\\Myntra-Automation\\src\\test\\MyntraTestData.xls","Sheet1");
		return data;
		
	}
	
	public String[][] getExcelData(String fileName , String sheetName) throws BiffException
	{
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];

			for (int i = 1; i < totalNoOfRows; i++) {

				for (int j = 0; j < totalNoOfCols; j++) {
					arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return arrayExcelData;
		
	}
	
	@AfterTest                                                        //This is after test method
	public void closeBrowser()
	{
	//	driver.quit();
	}
}


