package br.unibh.bytequest.achivementeservice.persistencia;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.bytequest.achivementeservice.entidades.Achievement;
@EnableScan()
public interface AchievementRepository extends CrudRepository<Achievement, String> {
	

	
}
