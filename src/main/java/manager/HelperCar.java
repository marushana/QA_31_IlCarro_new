package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void submit() {
        click(By.xpath("//*[@type = 'submit']"));
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
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
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        System.out.println(now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        System.out.println(from);
        int diffMonth = from.getMonthValue()-month;
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        String locatorFrom = "//span[text() ='"+from.getDayOfMonth()+"']";
        click(By.xpath(locatorFrom));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyy"));
        diffMonth = to.getMonthValue()-from.getMonthValue();
        if (diffMonth >0) {
            clickNextMonthBtn(diffMonth);
        }
        String locatorTo = "//span[text() ='"+to.getDayOfMonth()+"']";
        click(By.xpath(locatorTo));

    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector(".rdrNextPrevButton.rdrNextButton"));

        }

    }

    public void searchAnyPeriod(String city, String dataFrom, String dataTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;
        //***from
        diffYear =from.getYear() - now.getYear() ;
        if (diffYear == 0) {
            diffMonth = from.getMonthValue()-now.getMonthValue();
        }else {
            diffMonth = 12-now.getMonthValue()+from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        String locatorFrom = "//span[text()='"+from.getDayOfMonth()+"']";
        click(By.xpath(locatorFrom));

        //****to
        diffYear= to.getYear()- from.getYear();
        if (diffYear==0){
            diffMonth = to.getMonthValue()-from.getMonthValue();
        }else {
            diffMonth = 12-from.getMonthValue()-to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        String locatorTo = "//span[text()='"+to.getDayOfMonth()+"']";
        click(By.xpath(locatorTo));
    }
}
