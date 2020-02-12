import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TapTest {
    private IOSDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("deviceName", "Songtao's iPhone");
        capabilities.setCapability("udid", "13758e2ca3677a2abf9b349f814e491c057581a7");
        capabilities.setCapability("bundleId", "com.robinhood.release.Robinhood");
        capabilities.setCapability("automationName", "XCUITest");
//        capabilities.setCapability("xcodeOrgId", "Songtao Sun (Personal Team)");
//        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
//        capabilities.setCapability("updatedWDABundleId", "abc.def.ghi.jkl.webdriverrunner");
//        capabilities.setCapability("appiumVersion", "1.13.0");

        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.launchApp();
    }

    @After
    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
    }

    @Test
    public void testFindingAnElement() throws InterruptedException {
        Thread.sleep(200);
        try {
            if (driver.findElementByAccessibilityId("Cancel").isDisplayed()) {
                driver.findElementByAccessibilityId("Cancel").click();
                driver.findElementByAccessibilityId("Log Out").click();
            }
        }
        catch(NoSuchElementException e) {

        }
        driver.findElementByXPath("//XCUIElementTypeButton[@name=\"robinhood.walkthrough.login\"]").click();
        driver.findElementByAccessibilityId("robinhood.login.password").click();

        String decodePwdStr = new String(Base64.getDecoder().decode("U3N0QDE5NzgwOTA2"));
        driver.findElementByAccessibilityId("robinhood.login.password").sendKeys(decodePwdStr);
        driver.findElementByAccessibilityId("robinhood.login.button").click();

        try {

            if (driver.findElementByName("Not Now").isEnabled()) {
                driver.findElementByName("Not Now").click();
            }
        }
        catch(NoSuchElementException e) {
        }

        driver.findElementByAccessibilityId("cash_management_tab").click();
        PointOption po = PointOption.point(171,320);
        for(int i=0;i<10000;i++) {
            (new TouchAction(driver)).tap(po).perform();
//            Thread.sleep(200);
//            try {
//                if (driver.findElementByName("OK").isDisplayed()) {
//                    driver.findElementByName("OK").click();
//                    break;
//                }
//            }
//            catch(NoSuchElementException e) {
//
//            }
        }
    }
}