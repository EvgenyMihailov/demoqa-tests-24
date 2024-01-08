package guru.qa;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        //Name
        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Mihailov");
        //Email
        $("#userEmail").setValue("zh.mihailov@yandex.ru");
        //Gender
        $(byText("Male")).click();
        //Mobile
        $("#userNumber").setValue("89113642480");
        //Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("1");
        $(".react-datepicker__day--016").click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue("1995");
        $("#dateOfBirth-label").click();
        //Subjects
        $("[id=subjectsInput]").setValue("a").sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        //Hobbies
        $(byText("Sports")).ancestor(".custom-checkbox").click();
        $(byText("Reading")).ancestor(".custom-checkbox").click();
        //Picture
        $("#uploadPicture").uploadFromClasspath("after.jpg");
        //Current Address
        $("#currentAddress").setValue("Some street 1");

        //State and city
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        //Submit
        $("#submit").click();

        //Result
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Alex Mihailov"));
        $(".table-responsive").shouldHave(text("zh.mihailov@yandex.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("8911364248"));
        $(".table-responsive").shouldHave(text("16 February,2024"));
        $(".table-responsive").shouldHave(text("Arts"));

        //Hobbies
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("Reading"));



        $(".table-responsive").shouldHave(text("after.jpg"));
        $(".table-responsive").shouldHave(text("Some street 1"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));
    }
}