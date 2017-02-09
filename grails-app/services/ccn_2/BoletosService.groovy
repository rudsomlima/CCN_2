package ccn_2

import grails.transaction.Transactional
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

@Transactional
class BoletosService {

    def extrair() {


                String nome;
                String vencimento;
                def d_vencimento;
                String raiz_ccn= "http://www.sysca.com.br/ver_boleto.php?cod="
                String site_ccn;
                String valor;
                Integer i_valor;
                int flag_fim=0;
                int n_page = 53550;
                System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                //driver.manage().window().maximize();
                try {
                    n_page = Boletos.last().site.toInteger();
                } catch (Exception e1) {
                    n_page = 53550;
                }
                println site_ccn;

                while(flag_fim==0) {
                    n_page++;
                    def today = new Date().format("MM");
                    //println today;
                    site_ccn = "$raiz_ccn$n_page";
                    println site_ccn;
                    driver.get(site_ccn);
                    String fim = ""
                    def site_banco;
                    //site_banco = Boletos.findBySite(site_ccn);
                    //println site_banco;
                    if(site_banco) {
                        flag_fim=1
                        driver.close();
                    }else {
                        try {
                            fim = driver.findElement(By.xpath("/html/body/h2")).getText();
                            println fim;
                            if (fim == "BOLETO NÃO ENCONTRADO.") {

                                println "nao achou boleto";
                                driver.close();
                                flag_fim = 1;
                            }

                        } catch (Exception e) {

                            nome = driver.findElement(By.xpath("//*[@id=\"boleto\"]/table[6]/tbody/tr[2]/td")).getText();
                            nome = nome.substring(nome.indexOf("-") + 2, nome.length());
                            vencimento = driver.findElement(By.xpath("//*[@id=\"boleto\"]/table[8]/tbody/tr[2]/td[2]")).getText();
                            //d_vencimento = Date.parse("dd/MM/yyyy", vencimento);
                            valor = driver.findElement(By.xpath("//*[@id=\"boleto\"]/table[11]/tbody/tr[2]/td[6]")).getText();
                            //i_valor = valor.replaceAll(",", ".").toFloat();

                            //String strPageTitle = driver.getTitle();
                            //System.out.println("Page title: - " + strPageTitle);
                            System.out.println("$nome - $valor - $vencimento");
                            Boletos boleto = new Boletos();
                            boleto.nome = nome;
                            boleto.vencimento = vencimento;
                            boleto.valor = valor;
                            boleto.site = n_page;
                            if (!boleto.hasErrors()) {
                                boleto.save(flush: true);
                                System.out.println("Salvo com sucesso!");
                            } else {
                                System.out.println("Erro ao salvar!");
                                println boleto.errors.allErrors
                            }

//                            if (nome.contains("RUDSOM"))
//                                sendMail {
//                                    to "rudsomlima@gmail.com"
//                                    subject "Boleto CCN - R\$ $valor"
//                                    body site_ccn
//                                }
                        }
                    }
                }
    }
}
