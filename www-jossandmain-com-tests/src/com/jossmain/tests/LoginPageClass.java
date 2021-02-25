package com.jossmain.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class LoginPageClass {

    public static WebDriver driver;


    public static void openLoginPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();

        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        try {
            assertEquals(driver.getTitle(), "Joss & Main | Style is what you make it. Make it yours.");
            System.out.println("Test passed! - Actual Title Page is as expected ");
        } catch (Exception e) {
            System.out.println("TEST FAILED! - Title Page is NOT MATCH expected result");
        }
        // Нашла елемент Account button и нажала на него
        WebElement clickAccountButton = driver.findElement(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]"));
        clickAccountButton.click();
    }


    @Test(description = "[ET-01] Verification of the search \"SQA\"", priority = 1, enabled = true)
    public static void googleSearchSQA() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();

        // Open WebPage
        String baseUrl = "https://google.com";
        driver.get(baseUrl);

        // Verification Title Page
        try {
            assertEquals(driver.getTitle(), "Google");
            System.out.println("TEST PASSED - Actual Title Page is as expected.");
        } catch (Exception e) {
            System.out.println("TEST FAILED! - Title Page is NOT MATCH expected result");
        }

        WebElement typeTextSQA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search']")));
        typeTextSQA.sendKeys("SQA");

        WebElement clickButtonGoogleSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='gNO89b'])[2]")));
        clickButtonGoogleSearch.click();

        // Verification of visibility the specific element on the page
        try {
            WebElement visible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='People also ask']")));
            if (visible.isDisplayed()) {
                System.out.println("TEST PASSED - Element ”People also ask” visible on the page.");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED! - WebDriver couldn't locate the element ”People also ask”.");
        }

        driver.quit();
    }


    @Test(description = "[TC-111] Verify user can loggin")//#1
    public static void happyLogin() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        try {
            assertEquals(driver.getTitle(), "Joss & Main | Style is what you make it. Make it yours.");
            System.out.println("Test passed! - Actual Title Page is as expected ");
        } catch (Exception e) {
            System.out.println("TEST FAILED! - Title Page is NOT MATCH expected result");
        }
        // Нашла елемент Account button и нажала на него
        WebElement clickAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]")));
        clickAccountButton.click();

        //Нашла елемент и написала в нем емеил
        WebElement enterUserEmail = wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("textInput-0"))));
        enterUserEmail.sendKeys("ilisca2104@gmail.com");

        //Нашла елемент Continue Button и нажала на него
        WebElement clickContinueButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickContinueButton.click();

        //Нашла елемент и написала в нем Password
        WebElement enterUserPassword = wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("password"))));
        enterUserPassword.sendKeys("qwertyu123");

        //Нашла елемент Click Button и нажала на него
        WebElement clickSignInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickSignInButton.click();
        //сделала assortion для того чтоб удостоверится что я нахожусь на правильной странице
        try {
            WebElement pageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='pl-Text']")));
            if (pageElement.isDisplayed()) {
                System.out.println("TEST PASSED - Element ”Account Balance is visible on the page.");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED! - WebDriver couldn't locate the element ”Account Balance");
        }
        driver.quit();
    }


    @Test(description = "[TC-112] Verify user cannot login with valid username and invalid password")//#2
    public static void loginInvalidPassword() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement accountButtonClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]")));
        accountButtonClick.click();

        WebElement validUserEmail = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("textInput-0"))));
        validUserEmail.sendKeys("ilisca2104@gmail.com");

        WebElement continueButtonClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        continueButtonClick.click();

        WebElement invalidPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("textInput-1")));
        invalidPassword.sendKeys("qwerty123");

        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        signInButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String message = driver.findElement(By.xpath("//p[contains(text(),'Password is incorrect. Please try again.')]")).getText();
        String expectedMessage = "Password is incorrect. Please try again.";
        try {
            Assert.assertTrue(message.contains(expectedMessage));
            System.out.println("TEST PASSED: THE validate massage - Password is incorrect. Please try again. is displayed");
        } catch (Exception e) {
            System.out.println("\"TEST FAILED: validate message Password is incorrect. Please try again. is not displayed");
        }
        driver.quit();

    }

    @Test(description = "[TC-112] Verify user cannot login with invalid username and valid password")//#3
    public static void invalidPassword() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement accountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]")));
        accountButton.click();

        WebElement invalidEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("textInput-0")));
        invalidEmail.sendKeys("ilisca2104@gmail.co");

        WebElement clickContinueButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[@type='submit']"))));
        clickContinueButton.click();

        WebElement validPassword = wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("password"))));
        validPassword.sendKeys("qwertyu123");

        WebElement clickCreateAccount = driver.findElement(By.xpath("//button[@type='submit']"));
        clickCreateAccount.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String text = driver.findElement(By.xpath("//h1[normalize-space()='Add a Mobile Phone Number']")).getText();
        String displayedText = "Add a Mobile Phone Number";
        try {
            Assert.assertTrue(text.contains(displayedText));
            System.out.println("TEST PASSED - Add a Mobile Number is displayed");
        } catch (Exception e) {
            System.out.println("TEST FAILED! - Add a Mobile Number is not displayed");
        }
        driver.quit();
    }

    @Test(description = "[TC114] After successful login user navigate to Furniture tub")//#4
    public static void furnitureTub() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);//this is explicit waits
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement clickAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]")));
        clickAccountButton.click();

        WebElement enterUserEmail = wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("textInput-0"))));
        enterUserEmail.sendKeys("ilisca2104@gmail.com");

        WebElement clickContinueButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickContinueButton.click();

        WebElement enterUserPassword = wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("password"))));
        enterUserPassword.sendKeys("qwertyu123");

        //Нашла елемент Click Button и нажала на него
        WebElement clickSignInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickSignInButton.click();
//сделала assortion для того чтоб удостоверится что я нахожусь на правильной странице
        try {
            WebElement pageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='pl-Text']")));
            if (pageElement.isDisplayed()) {
                System.out.println("TEST PASSED - Element ”Account Balance is visible on the page.");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED! - WebDriver couldn't locate the element ”Account Balance");
        }

        WebElement furnitureTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='DepartmentItem-Furniture']")));
        furnitureTab.isSelected();


    }

    @Test(description = "[TC115] user can navigate to Living Room menu")//#5
    public static void livingRoomFurnitureMenu() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement clickAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]")));
        clickAccountButton.click();

        WebElement enterUserEmail = wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("textInput-0"))));
        enterUserEmail.sendKeys("ilisca2104@gmail.com");

        WebElement clickContinueButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickContinueButton.click();

        WebElement enterUserPassword = wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("password"))));
        enterUserPassword.sendKeys("qwertyu123");

        //Нашла елемент Click Button и нажала на него
        WebElement clickSignInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickSignInButton.click();
//сделала assortion для того чтоб удостоверится что я нахожусь на правильной странице
        try {
            WebElement pageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='pl-Text']")));
            if (pageElement.isDisplayed()) {
                System.out.println("TEST PASSED - Element ”Account Balance is visible on the page.");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED! - WebDriver couldn't locate the element ”Account Balance");
        }

//    driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
//    Select furnitureDropDown = new Select(driver.findElement(By.cssSelector("#DepartmentItem-Furniture")));
//    furnitureDropDown.selectByVisibleText("Living Room");
        WebElement furnitureTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#DepartmentItem-Furniture")));
        furnitureTab.click();

        WebElement livingRoomFurnitureMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Living Room Furniture')]")));
        livingRoomFurnitureMenu.click();
        driver.quit();
    }

    @Test(description = "[TC115] user select Sofas & Sectionals")//#6
    public static void sofaAndSectionalsMenu() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement clickAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]")));
        clickAccountButton.click();

        WebElement enterUserEmail = wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("textInput-0"))));
        enterUserEmail.sendKeys("ilisca2104@gmail.com");

        WebElement clickContinueButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickContinueButton.click();

        WebElement enterUserPassword = wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("password"))));
        enterUserPassword.sendKeys("qwertyu123");

        //Нашла елемент Click Button и нажала на него
        WebElement clickSignInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickSignInButton.click();
//сделала assortion для того чтоб удостоверится что я нахожусь на правильной странице
        try {
            WebElement pageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='pl-Text']")));
            if (pageElement.isDisplayed()) {
                System.out.println("TEST PASSED - Element ”Account Balance is visible on the page.");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED! - WebDriver couldn't locate the element ”Account Balance");
        }

        WebElement furnitureTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#DepartmentItem-Furniture")));
        furnitureTab.click();

        WebElement livingRoomFurnitureMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Living Room Furniture')]")));
        livingRoomFurnitureMenu.click();

        WebElement sofaAndSectionalsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='doc3']/div[@data-hypernova-key=" +
                "'desktop_category_page_container']/main[@class='pl-Wrapper']/div[@class='Category']/div[@id='bd']/div[@class='pl-Box--mb-6']/section[@class=" +
                "'SubCategoryGroup']/div[@class='pl-Box--display-flex pl-Box--fw-wrap pl-Grid pl-Grid--gutterFullBleed']/div[1]/div[1]/a[1]/div[1]/div[1]/div[1]/img[1]")));
        sofaAndSectionalsMenu.click();
        driver.quit();
    }

    @Test(description = "[TC116] user click on sale menu")//#7
    public static void saleMenu() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement clickAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/nav[1]/div[3]/div[1]/a[1]/span[1]")));
        clickAccountButton.click();

        WebElement enterUserEmail = wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("textInput-0"))));
        enterUserEmail.sendKeys("ilisca2104@gmail.com");

        WebElement clickContinueButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickContinueButton.click();

        WebElement enterUserPassword = wait.until((ExpectedConditions.visibilityOfElementLocated(By.name("password"))));
        enterUserPassword.sendKeys("qwertyu123");

        //Нашла елемент Click Button и нажала на него
        WebElement clickSignInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickSignInButton.click();
//сделала assortion для того чтоб удостоверится что я нахожусь на правильной странице
        try {
            WebElement pageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='pl-Text']")));
            if (pageElement.isDisplayed()) {
                System.out.println("TEST PASSED - Element ”Account Balance is visible on the page.");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED! - WebDriver couldn't locate the element ”Account Balance");
        }

        WebElement furnitureTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#DepartmentItem-Furniture")));
        furnitureTab.click();

        WebElement livingRoomFurnitureMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Living Room Furniture')]")));
        livingRoomFurnitureMenu.click();

        WebElement sofaAndSectionalsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='doc3']/div[@data-hypernova-key=" +
                "'desktop_category_page_container']/main[@class='pl-Wrapper']/div[@class='Category']/div[@id='bd']/div[@class='pl-Box--mb-6']/section[@class=" +
                "'SubCategoryGroup']/div[@class='pl-Box--display-flex pl-Box--fw-wrap pl-Grid pl-Grid--gutterFullBleed']/div[1]/div[1]/a[1]/div[1]/div[1]/div[1]/img[1]")));
        sofaAndSectionalsMenu.click();
        WebElement saleMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='SubCategoryCard']//img[@class='ImageComponent-image " +
                "ImageComponent-image--fullWidth ImageComponent-image--preventVerticalPopping']")));
        saleMenu.click();
        String text = driver.findElement(By.xpath("//span[@class='BrowseHeaderWithSort-roadsign']")).getText();
        String displayedText = "Sofas & Sectionals Sale";
        try {
            Assert.assertTrue(text.contains(displayedText));
            System.out.println("TEST PASSED - Sofas & Sectionals Sale");
        } catch (Exception e) {
            System.out.println("TEST FAILED! - Sofas & Sectionals Sale is not displayed");
        }
    }

    @Test(description = "[TC117] user can navigate to furniture tab without login")//#8
    public static void furnitureTabNotLogin() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement furnitureTabNotLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#DepartmentItem-Furniture")));
        furnitureTabNotLogin.click();

        try {
            WebElement furniture = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Furniture']")));
            if (furniture.isDisplayed()) {
                System.out.println("TEST PASSED - element Furniture is displayed on the page");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED - element Furniture is not displayed on the page");
        }
        driver.quit();
    }

    @Test(description = "[TC118] verify user can navigate to Bedding & Bath section without login")//#9
    public static void beddingBath() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Irina\\xxxxxxxxxxxxxxxxxxx\\www-jossandmain-com-tests\\src\\resourses\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        String baseUrl = "https://www.jossandmain.com/";
        driver.get(baseUrl);

        WebElement beddingBath = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='DepartmentItem-Bedding & Bath']")));
        beddingBath.click();

        try {
            WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Bedding & Bath']")));
            if (text.isDisplayed()) {
                System.out.println("TEST PASSED! Bedding & Bath is displayed on the page!");
            }
        } catch (Exception e) {
            System.out.println("TEST FAILED - Bedding & Bath is not displayed on the page!");

        }
        driver.quit();
    }
}
