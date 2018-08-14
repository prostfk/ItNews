package by.bntu.fitr.povt.prostrmk.ItNews.dao;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Component
public class DaoComponent {

    private static final Logger LOGGER = Logger.getLogger(DaoComponent.class);

    @Bean
    public Connection connection(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/db.properties"));
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
