package guru.qa;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CssXpathExamples {
    void cssXpathExamples() {
        $("[data-testid=email]").setValue("some text");
    }
}
