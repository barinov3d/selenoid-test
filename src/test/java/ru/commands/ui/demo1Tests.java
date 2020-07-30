package ru.commands.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
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
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("group1")
public class demo1Tests {
    private final static String protocol = "http://";

/*    @BeforeEach
    public static void setUp() throws MalformedURLException {
        //Url удалённого веб драйвера
        Configuration.remote = "http://localhost:4444/wd/hub";
        //Определяем какой браузер будем использовать
        Configuration.browser = "chrome";
        //Размер окна браузера
        Configuration.browserSize = "1920x1080";
        //Создаём объект класса DesiredCapabilities, используется как настройка  вашей конфигурации с помощью пары ключ-значение
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Включить поддержку отображения экрана браузера во время выполнения теста
        capabilities.setCapability("enableVNC", true);
        //Включение записи видео в процессе выполнения тестов
        capabilities.setCapability("enableVideo", false);
        //Переопределяем Browser capabilities
        capabilities.setBrowserName("chrome");
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().enableLogs(LogType.BROWSER, Level.ALL).screenshots(true).savePageSource(false));
    }*/
    @BeforeEach
    public void setUp() throws MalformedURLException {
        final String url = "http://localhost:4444/wd/hub";
        WebDriver driver = new RemoteWebDriver(new URL(url), DesiredCapabilities.chrome());
        driver.manage().window().setSize(new Dimension(1920,1024));
        WebDriverRunner.setWebDriver(driver);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"ya.ru", "google.com"})
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
    @AfterEach
    public void stopDriver() {
        Optional.ofNullable(WebDriverRunner.getWebDriver()).ifPresent(WebDriver::quit);
    }
}
