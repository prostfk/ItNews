package by.bntu.fitr.povt.prostrmk.ItNews.config;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "by.bntu.fitr.povt.prostrmk.ItNews")
@ComponentScan(value = {"by.bntu.fitr.povt.prostrmk.ItNews"})
@EnableJpaRepositories(value = "by.bntu.fitr.povt.prostrmk.ItNews")
@EntityScan(value = "by.bntu.fitr.povt.prostrmk.ItNews")
public class ItNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItNewsApplication.class, args);
	}


	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		System.setProperty("rootPath", context.getRealPath("/"));
	}

}
