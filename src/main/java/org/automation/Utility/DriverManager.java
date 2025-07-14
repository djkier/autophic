package org.automation.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class DriverManager {
    private static WebDriver driver;
    private static String mainTab;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void switchToMainTab() {
        driver.switchTo().window(mainTab);
    }

    public static WebDriver newTab() {
        return driver.switchTo().newWindow(WindowType.TAB);
    }

    public static WebDriver newTab(String url) {
        mainTab = driver.getWindowHandle();
        WebDriver eSoaPage = newTab();
        eSoaPage.get(url);
        return eSoaPage;
    }
}
