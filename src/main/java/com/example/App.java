package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.time.Duration;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Updated URLs
        String[] urls = {
            "https://www.youtube.com",
            "https://github.com/login",
            "https://www.coursera.org/login",
            "https://www.instagram.com/accounts/login/"
        };

        // Open first tab
        driver.get(urls[0]);
        Thread.sleep(4000);

        // Open other tabs with actions
        for (int i = 1; i < urls.length; i++) {
            ((ChromeDriver) driver).executeScript("window.open();");
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(i));
            driver.get(urls[i]);
            Thread.sleep(5000); // Wait 5 seconds between tabs

            if (i == 1) { // GitHub
                driver.findElement(By.id("login_field")).sendKeys("Yoganand30");
                driver.findElement(By.id("password")).sendKeys("Yoganand@1234");
                driver.findElement(By.name("commit")).click();
                Thread.sleep(3000);
            }

            if (i == 2) { // Coursera
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-e2e='login-email-input']"))
                );
                emailField.sendKeys("yoganandmaskalle@gmail.com");

                WebElement passwordField = driver.findElement(By.cssSelector("input[data-e2e='login-password-input']"));
                passwordField.sendKeys("Yoganand@123");

                WebElement loginButton = driver.findElement(By.cssSelector("button[data-e2e='login-form-submit-button']"));
                loginButton.click();

                Thread.sleep(3000);
            }

            if (i == 3) { // Instagram
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

                WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("username"))
                );
                username.sendKeys("yoganandmaskalle@gmail.com");

                WebElement password = driver.findElement(By.name("password"));
                password.sendKeys("9902073592");

                WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
                loginBtn.click();

                Thread.sleep(5000);
            }
        }

        System.out.println("Tabs opened. GitHub, Coursera, and Instagram login attempts completed.");
    }
}
