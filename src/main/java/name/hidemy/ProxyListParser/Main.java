package name.hidemy.ProxyListParser;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","D:\\ProxyChecker\\chromedriver.exe");
        driver.get("https://hidemy.name/ru/proxy-list/?type=h");
        var ipTable = driver.findElements(By.className("table_block"));
        for (int i = 0; i <ipTable.size(); i++) {
            var trs = ipTable.get(i).findElements(By.tagName("tr"));
            for (int j = 0; j <trs.size(); j++) {
                var tr = trs.get(j);
                ///html/body/div[1]/div[4]/div/div[5]/table/tbody/tr[5]/td[1]
                var ip = tr.findElement(By.xpath(".//td[1]"));
                System.out.println(ip.getText());
                var port = tr.findElement(By.xpath(".//td[2]"));
                System.out.println(port.getText());



            }
        }
        driver.quit();

    }
}
