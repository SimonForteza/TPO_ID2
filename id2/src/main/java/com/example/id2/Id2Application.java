package com.example.id2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class
})
@EnableNeo4jRepositories(basePackages = "com.example.id2.repository.neo")
@EnableMongoRepositories(basePackages = "com.example.id2.repository.mongo")
@EnableCassandraRepositories(basePackages = "com.example.id2.repository.cassandra")
public class Id2Application {

	public static void main(String[] args) {
		SpringApplication.run(Id2Application.class, args);
	}

}
