package com.tricentis.tests.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.tricentis.tests.config.ConfigProvider;
import com.tricentis.tests.helpers.AllureAttach;
import com.tricentis.tests.helpers.DriverConfiguration;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestsConfiguration {
    @BeforeAll
    static void settings() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverConfiguration.configure();


        ConfigProvider config = new ConfigProvider();
        config.setConfiguration("remote"); // конфиг для удаленного запуска
        //config.setConfiguration("local"); // раскомментить для локального запуска

    }

    @AfterEach
    void addAttachments() {
        AllureAttach.screenshotAs("Screenshot");
        AllureAttach.pageSource();
        AllureAttach.browserConsoleLogs();
        if ((System.getProperty("selenide.remote") != null)) {
            AllureAttach.addVideo();
        }
    }
}
