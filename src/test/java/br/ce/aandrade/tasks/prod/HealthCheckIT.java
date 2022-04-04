package br.ce.aandrade.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
	
	@Test
	public void healthCheck() throws MalformedURLException {
		ChromeOptions cap = new ChromeOptions();
		WebDriver driver = new RemoteWebDriver(new URL("http://10.0.0.105:4444/wd/hub"), cap);
		try {
		driver.navigate().to("http://10.0.0.105:9999/tasks");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String version = driver.findElement(By.id("version")).getText();
		System.out.println(version);
		Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}
	}
}
