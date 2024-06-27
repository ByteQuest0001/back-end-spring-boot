package br.unibh.bytequest.achivementeservice.negocio;

import java.util.List;
import java.util.Optional;

import br.unibh.bytequest.achivementeservice.entidades.Achievement;
import br.unibh.bytequest.achivementeservice.persistencia.AchievementRepository;
import org.springframework.stereotype.Service;

/**
 * Classe contendo a lógica de negócio para Cotação
 * @author jhcru
 *
 */
@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository){
        this.achievementRepository= achievementRepository;
    }

    public List<Achievement> listAchievements(){
        var achievement = achievementRepository.findAll();
        return (List<Achievement>) achievement;
    }

    public Optional<Achievement> detailAchievement(String id){
        var achievement = achievementRepository.findById(id);
        return achievement;
    }

    public void createAchievement(Achievement achievement){
        achievementRepository.save(achievement);
    }

    public Achievement updateAchievement(String id, Achievement achievement){
        achievementRepository.findById(id);
        achievement.update();
        return achievementRepository.save(achievement);
    }

    public Optional<Achievement> completeAchievement(String id) {
        var achievement = achievementRepository.findById(id);
        if(!achievement.isPresent()){
            throw new RuntimeException();
        }
        achievement.get().complete();
        achievementRepository.save(achievement.get());
        return achievement;
    }

    public void deleteAchievement(String id){
        achievementRepository.deleteById(id);
    }

}