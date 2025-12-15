package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperCar extends HelperBase{

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void submit() {
        click(By.xpath("//*[@type = 'submit']"));
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));

        //"12/25/2025", "12/31/2025"
        String[] from = dateFrom.split("/");
        String[] to = dateTo.split("/");

        String locatorFrom = "//span[text() ='"+from[1]+"']";
        click(By.xpath(locatorFrom));

        String locatorTo = "//span[text() ='"+to[1]+"']";
        click(By.xpath(locatorTo));


    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.id("city-suggestions"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));

    }
}
