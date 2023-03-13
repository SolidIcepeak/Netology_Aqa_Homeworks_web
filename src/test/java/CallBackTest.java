import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallBackTest {
    private WebDriver driver;

    @BeforeAll

    static void configurationWebDriver() {
        System.setProperty("web-driver.chrome.driver", "./driver/win/chromedriver");
    }

    @BeforeEach
    void createBrowser() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @Test
    void happyPathTest() {
        driver.get("http://localhost:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Морозов Александр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79321299187");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] span")).click();
        driver.findElement(By.className("button")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText.strip());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
}
