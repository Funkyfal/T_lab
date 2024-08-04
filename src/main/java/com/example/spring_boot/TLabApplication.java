package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
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

			// Создаем тестовую таблицу
			jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS test_table (id SERIAL PRIMARY KEY, name VARCHAR(255))");

			// Вставляем тестовые данные
			jdbcTemplate.execute("INSERT INTO test_table (name) VALUES ('Test Name')");

			// Выполняем запрос и выводим результат
			jdbcTemplate.query("SELECT id, name FROM test_table",
							(rs, rowNum) -> "ID: " + rs.getLong("id") + ", Name: " + rs.getString("name"))
					.forEach(System.out::println);

			System.out.println("Database connection tested successfully.");
		};
	}

}