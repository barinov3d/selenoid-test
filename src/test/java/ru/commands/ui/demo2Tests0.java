package ru.commands.ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;

@Tag("group2")
public class demo2Tests0 {
    private final static String protocol = "http://";

    @BeforeAll
    public static void setUp() {
        //Url удалённого веб драйвера
        Configuration.remote = "http://localhost:4444/wd/hub";
        //Определяем какой браузер будем использовать
        Configuration.browser = "firefox";
        //Размер окна браузера
        Configuration.browserSize = "1920x1080";
        //Создаём объект класса DesiredCapabilities, используется как настройка  вашей конфигурации с помощью пары ключ-значение
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Включить поддержку отображения экрана браузера во время выполнения теста
        capabilities.setCapability("enableVNC", true);
        //Включение записи видео в процессе выполнения тестов
        capabilities.setCapability("enableVideo", false);
        //Переопределяем Browser capabilities
        Configuration.browserCapabilities = capabilities;
    }

    @ParameterizedTest()
    @ValueSource(strings = {"ya.ru", "google.com"})
    public void openPage(String path) {
        open(protocol + path);
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
}
