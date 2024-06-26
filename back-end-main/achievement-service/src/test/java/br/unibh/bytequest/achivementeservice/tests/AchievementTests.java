package br.unibh.bytequest.achivementeservice.tests;
import  static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.unibh.bytequest.achivementeservice.persistencia.AchievementRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.bytequest.achivementeservice.entidades.Achievement;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, AchievementTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AchievementTests {

    private static Logger LOGGER = LoggerFactory.getLogger(AchievementTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    
    @Configuration
	@EnableDynamoDBRepositories(basePackageClasses = { AchievementRepository.class })
	public static class DynamoDBConfig {

		@Value("${amazon.aws.accesskey}")
		private String amazonAWSAccessKey;

		@Value("${amazon.aws.secretkey}")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}
    
	@Autowired
	private AchievementRepository repository;

	@Test
	public void testeListAll(){
		// Given / Arrange
		Date date = new Date();
		Achievement achievement = new Achievement(1L, "curso de java", "curso de java com spring", "10/10/2020", "JAVA",
				"FORMACAO_ACADEMICA", "EM_ANDAMENTO");
		Achievement achievement2 = new Achievement(2L, "analista de dados", "análise de dados com bigdata", "10/10/2020", "bigdata",
				"EXPERIENCIA_PROFISSIONAL", "EM_ANDAMENTO");

		// When / Act
		repository.save(achievement);
		repository.save(achievement2);
		List<Achievement> achievementList = (List<Achievement>) repository.findAll();


		// Then / Assert
		assertNotNull(achievementList.size());
		assertTrue(achievementList.size() >= 2);
	}


	
	@Test
	public void testeExclusao(){
		// Given / Arrange
		Achievement achievement = new Achievement(1L, "curso de java", "curso de java com spring", "10/10/2020", "JAVA",
				"FORMACAO_ACADEMICA", "EM_ANDAMENTO");
		var newAchievement = repository.save(achievement);

		// When / Act
		repository.deleteById(newAchievement.getId());

		Optional<Achievement> produtoById = repository.findById(newAchievement.getId());

		// Then / Assert
		assertTrue(produtoById.isEmpty(), () -> "Conquista não foi deletado corretamente");
	}
	
	
}
