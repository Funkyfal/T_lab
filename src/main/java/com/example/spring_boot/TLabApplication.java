package com.example.spring_boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class TLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(TLabApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/TDB");
		dataSource.setUsername("postgres");
		dataSource.setPassword("DBPASSWORD");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public CommandLineRunner testDatabaseConnection(JdbcTemplate jdbcTemplate) {
		return args -> {
			System.out.println("Testing database connection...");

			jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS translation_requests (id SERIAL PRIMARY KEY, ip_address VARCHAR(255), input_text TEXT, translated_text TEXT, timestamp TIMESTAMP)");

			jdbcTemplate.execute("INSERT INTO translation_requests (ip_address, input_text, translated_text, timestamp) VALUES ('test', 'Hello world', 'Привет мир', CURRENT_TIMESTAMP)");

			jdbcTemplate.query("SELECT id, ip_address, input_text, translated_text, timestamp FROM translation_requests",
							(rs, rowNum) -> String.format("ID: %d, IP Address: %s, Input Text: %s, Translated Text: %s, Timestamp: %s",
									rs.getLong("id"), rs.getString("ip_address"), rs.getString("input_text"), rs.getString("translated_text"), rs.getTimestamp("timestamp")))
					.forEach(System.out::println);

			System.out.println("Database connection tested successfully.");
		};
	}
}
