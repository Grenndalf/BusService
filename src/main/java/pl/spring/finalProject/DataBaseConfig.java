package pl.spring.finalProject;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("postgres")
    private String user;
    @Value("passw")
    private String pass;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(user);
        config.setPassword(pass);
        return new HikariDataSource(config);
    }
}