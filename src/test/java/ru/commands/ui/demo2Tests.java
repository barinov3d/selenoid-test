package ru.commands.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("group2")
public class demo2Tests extends CommonTests {
    private final static String protocol = "http://";

    @ParameterizedTest()
    @ValueSource(strings = {"google.com"})
    public void openPage(String path) {
        open(protocol + path);
        $(By.xpath("//input[contains(@type,'submit')]")).shouldHave(Condition.value("Поиск в Google"));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"yahoo.com", "vk.com"})
    public void openPage2(String path) {
        open(protocol + path);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"mvnrepository.com", "junit.org"})
    public void openPage3(String path) {
        open(protocol + path);
    }

    @Test
    @DisplayName("Открываем существующую Issue")
    public void testIssue() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Открываем страницу с репозиторием", () -> {
            $x("//*[contains(@class, 'header-search-input')]").click();
            $x("//*[contains(@class, 'header-search-input')]").sendKeys("eroshenkoam/allure-example");
            $x("//*[contains(@class, 'header-search-input')]").submit();
            $x("//a[@href='/eroshenkoam/allure-example']").click();
        });
        step("Открываем страницу с задачами в репозитории", () -> {
            $x("//a[contains(@data-selected-links, 'repo_issues')]").click();
        });
        step("Проверяем title", () -> {
            $x("//a[@id='issue_12_link']").click();
            $x("//h1[contains(@class, 'gh-header-title')]").should(Condition.text("Hello, World!"));
        });
    }
}
