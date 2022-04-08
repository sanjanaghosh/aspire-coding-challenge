package com.odoo.aspire.utils;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.odoo.aspire.config.AppConfigManager;
import com.odoo.aspire.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.OutputType.BYTES;

public class Utilities {

    public static String extractNumberFromString(String text) {
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(text);
        while(matcher.find()){
            return (matcher.group(1));
        }
        return null;
    }

    public static String getNumberFromString(String text) {
        String result = extractNumberFromString(text);
        result = String.valueOf(new Double(result).intValue());
        return result;
    }

    public static void setAllureEnvironmentInformation() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                        put("Test URL", AppConfigManager.configuration().url()).
                        put("Target execution", AppConfigManager.configuration().target()).
                        put("Global timeout", String.valueOf(AppConfigManager.configuration().timeout())).
                        put("Headless mode", String.valueOf(AppConfigManager.configuration().headless())).
                        put("Faker locale", AppConfigManager.configuration().faker()).
                        put("Local browser", AppConfigManager.configuration().browser()).
                        build());
    }
    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
    }
}
