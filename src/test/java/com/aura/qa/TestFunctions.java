
package com.aura.qa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.script.ScriptException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TestFunctions {

	private static Logger logger = LoggerFactory.getLogger(TestFunctions.class);

	public static void setTimeOut(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(Constants.TIMEOUT, TimeUnit.SECONDS);
	}

	public static WebDriver configureDriver(String name) {

		String operatingSystem = System.getProperty("os.name");

		logger.info(operatingSystem);

		logger.info("Driver: " + name);

		WebDriver driver = null;

		String driverPath = "";
		if (name.toLowerCase().contains(Constants.CHROME)) {

			if (operatingSystem.toLowerCase().contains(Constants.WINDOWS)) {
				driverPath = Constants.CHROME_DRIVER_PATH + Constants.EXTENSION_WINDOWS;
				System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, driverPath);
			} else {
				driverPath = Constants.CHROME_DRIVER_PATH;
				System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, driverPath);
			}
			driver = new ChromeDriver();
		}
		if (name.toLowerCase().contains(Constants.FIREFOX)) {

			if (operatingSystem.toLowerCase().contains(Constants.WINDOWS)) {
				driverPath = Constants.GECKO_DRIVER_PATH + Constants.EXTENSION_WINDOWS;
				System.setProperty(Constants.WEBDRIVER_GECKO_DRIVER, driverPath);
				driver = new FirefoxDriver();

			} else {
				driverPath = Constants.GECKO_DRIVER_PATH;
				System.setProperty(Constants.WEBDRIVER_GECKO_DRIVER, driverPath);
				FirefoxOptions options = new FirefoxOptions();
				// Esta linea se utiliza para que no levante firefox (evitar entorno grafico)
				options.addArguments(Constants.HEADLESS);
				// Fijamos idioma por defecto del navegador para que coincida con el nuestro
				options.addArguments("--lang=es-ES");
				options.addPreference("intl.accept_languages", "es-ES");
				driver = new FirefoxDriver(options);
			}
		}

		return driver;
	}

	public static void loadConfiguration() throws IOException, FileNotFoundException {
		Properties p = new Properties();
		p.load(new FileReader(Constants.TEST_CONFIG_PATH));

		String navegador = (String) p.get(Constants.CONFIG_NAVEGADOR);
		Constants.DRIVER_SELECTED = navegador;

		String timeout = (String) p.get(Constants.CONFIG_TIMEOUT);
		Constants.TIMEOUT = Integer.parseInt(timeout);

	}

	public static void await(WebDriver theDriver) {
		theDriver.manage().timeouts().implicitlyWait(Constants.TIMEOUT, TimeUnit.SECONDS);
	}

	public static void sleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			System.out.println("Error in sleep");
			e.printStackTrace();
		}
	}

	public static double evalOpp(String operation) throws ScriptException {

		Expression expression = new ExpressionBuilder(operation).build();
		return expression.evaluate();

	}

}
