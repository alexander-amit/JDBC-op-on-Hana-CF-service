package hana.config;
import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


//@Configuration
public class DbConfig extends AbstractCloudConfig {
	@Bean
	public DataSource dataSource() {
		return connectionFactory().dataSource();
	}

}