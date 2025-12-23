package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{
    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Haifa", "12/25/2025", "12/31/2025");
        app.getHelperCar().getScreen("src/test/screenshoots/currentMonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Haifa", "2/25/2025", "12/30/2025");
        app.getHelperCar().getScreen("src/test/screenshoots/currentYear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriod(){
        app.getHelperCar().searchAnyPeriod("Haifa", "2/25/2026", "5/10/2026");
        app.getHelperCar().getScreen("src/test/screenshoots/any.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }
}
