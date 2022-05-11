package br.com.elo7.sonda.candidato.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

	@Bean
	public MongoClient mongo() {
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");

		MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
				.applyToSocketSettings(builder -> {
					builder.connectTimeout(2000, TimeUnit.MILLISECONDS);
					builder.readTimeout(2000, TimeUnit.MILLISECONDS);})
				.applyToClusterSettings(builder -> builder.serverSelectionTimeout(2000, TimeUnit.MILLISECONDS))
				//.applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);

	}

//	@Bean
//	public MongoTemplate mongoTemplate() throws Exception {
//		return new MongoTemplate(mongo(), "test");
//	}

}
