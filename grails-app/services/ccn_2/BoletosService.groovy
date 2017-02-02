package ccn_2

import grails.transaction.Transactional
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

import java.sql.Date

@Transactional
class BoletosService {

    def extrair() {

                String nome;
                String vencimento;
                def d_vencimento;
                String valor;
                Integer i_valor;
                int n_page = 53000;
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\rudsom.lima\\Downloads\\chromedriver_win32\\chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                //driver.manage().window().maximize();

                while(n_page<53200) {
                    String site_ccn = "http://www.sysca.com.br/ver_boleto.php?cod=$n_page";
                    driver.get(site_ccn);
                    nome = driver.findElement(By.xpath("//*[@id=\"boleto\"]/table[6]/tbody/tr[2]/td")).getText();
                    nome = nome.substring(nome.indexOf("-")+2, nome.length());
                    vencimento = driver.findElement(By.xpath("//*[@id=\"boleto\"]/table[8]/tbody/tr[2]/td[2]")).getText();
                    d_vencimento = Date.parse("dd/MM/yyyy",vencimento);
                    valor = driver.findElement(By.xpath("//*[@id=\"boleto\"]/table[11]/tbody/tr[2]/td[6]")).getText();
                    i_valor = valor.replaceAll(",",".").toDouble();


                    //String strPageTitle = driver.getTitle();
                    //System.out.println("Page title: - " + strPageTitle);
                    System.out.println("$nome - $valor - $vencimento");
                    Boletos boleto = new Boletos();
                    boleto.nome = nome;
                    //boleto.vencimento = d_vencimento;
                    //boleto.valor = i_valor;
                   if(!boleto.hasErrors()){
                       boleto.save(flush:true);
                       System.out.println("Salvo com sucesso!");
                   }else{
                       System.out.println("Erro ao salvar!");
                       //println boleto.errors.allErrors
                   }
                    n_page++;
                }
    }
}
