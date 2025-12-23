package manager;

import com.google.common.io.Files;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator,String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if(text!=null){
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element){
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void clearTextBox(By locator){
        WebElement element = wd.findElement(locator);
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os.startsWith("Win")){
            element.sendKeys(Keys.CONTROL, "a");
        }else{
            element.sendKeys(Keys.COMMAND, "a");
        }
        element.sendKeys(Keys.DELETE);
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size()>0;
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File temp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(temp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
