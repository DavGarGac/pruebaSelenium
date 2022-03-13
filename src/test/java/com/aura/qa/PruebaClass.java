
package com.aura.qa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.qameta.allure.Step;

@TestMethodOrder(OrderAnnotation.class)
public class PruebaClass {

	private static Logger logger = LoggerFactory.getLogger(PruebaClass.class);

	private static WebDriver driver;

	@BeforeAll
	public static void setup() throws Exception {

		TestFunctions.loadConfiguration();
		driver = TestFunctions.configureDriver(Constants.DRIVER_SELECTED);
		driver.manage().window().maximize();
		TestFunctions.setTimeOut(driver);

		logger.info("*****************LOG******************");

		App app = new App();
		Properties prop = app.loadPropertiesFile("allure.properties");
		prop.forEach((k, v) -> System.out.println(k + ":" + v));

		logger.info("*****************END LOG******************");

	}

	@Test
	@DisplayName("Página Aura")
	@Tag("Acceso")
	@Step("Acceso directo a URL")
	@Order(1)
	public void testInicio() {

		String TEXT_TO_CHECK = "AURA GROUP | Consultoría tecnológica para empresas";
		String URL_TO_CHECK = "https://auragroup.es";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	
	@Test
	@DisplayName("Página QALovers")
	@Tag("Acceso")
	@Step("Acceso directo a URL")
	@Order(2)
	public void testQA() {
		String TEXT_TO_CHECK = "QALovers";
		String URL_TO_CHECK = "https://www.qalovers.com/";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}


	@Test
	@DisplayName("Pagina equipo")
	@Tag("Betis")
	@Step("Acceso directo a URL")
	@Order(6)
	public void testCambioEquipo() {

		String TEXT_TO_CHECK = "ACB.COM";
		String URL_TO_CHECK = "https://www.acb.com/";

		String xPathExpre = "//img[@alt=\"Coosur Real Betis\"]";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		
		WebElement langIcon = driver.findElement(By.xpath(xPathExpre));

		if (langIcon != null) {
			langIcon.click();
			TestFunctions.await(driver);
		}

		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}
	
	//Esta prueba si se ejecuta mas de una vez te pide rellenar un captcha.
	@Test
	@DisplayName("Página contacto")
	@Tag("Contacto")
	@Step("Acceso directo a URL")
	@Order(7)
	public void testContactoForm() {

		String TEXT_TO_CHECK = "Contactar con idealista — idealista";
		String URL_TO_CHECK = "https://www.idealista.com/info/contacto";
		String NAME_FIELD = "iName";
		String EMAIL_FIELD = "iEmail";
		String MENSAJE_FIELD = "iMessage";
		String SEND_BUTTON = "bSubmitHelp";

		driver.get(URL_TO_CHECK);

		WebElement nameText = driver.findElement(By.id(NAME_FIELD));
		nameText.clear();
		nameText.sendKeys("David");

		WebElement emailText = driver.findElement(By.id(EMAIL_FIELD));
		emailText.clear();
		emailText.sendKeys("emailDePrueba");

		WebElement mensajeText = driver.findElement(By.id(MENSAJE_FIELD));
		mensajeText.clear();
		mensajeText.sendKeys("Texto automatizado escrito con selenium");

		WebElement sendButton = driver.findElement(By.id(SEND_BUTTON));

		sendButton.click();


		assertEquals(TEXT_TO_CHECK, driver.getTitle());
		
		//Da error de ejecución ya que el email no es correcto, si metes un mail valido funciona.
	}
	
	
//Descomentar lo siguiente para que se cierre el navegador al terminar.
/*	

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}

	*/

}
