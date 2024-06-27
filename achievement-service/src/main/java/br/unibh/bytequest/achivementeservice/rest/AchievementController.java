package br.unibh.bytequest.achivementeservice.rest;

import java.util.List;
import java.util.Optional;

import br.unibh.bytequest.achivementeservice.entidades.Achievement;
import br.unibh.bytequest.achivementeservice.negocio.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/achievement")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService){
        this.achievementService = achievementService;
    }

    @Operation(
            summary = "Cadastrando uma nova conquista",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Conquista cadastrada com sucesso"
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<Achievement> createAchievement(@RequestBody Achievement achievement) throws Exception {
        achievementService.createAchievement(achievement);
        return ResponseEntity.status(HttpStatus.CREATED).body(achievement);
    }

    @Operation(
            summary = "Atualizando uma conquista",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conquista atualizada com sucesso"
                    ),
            }
    )
    @PutMapping("/{id}")
    public Achievement updateAchievement(@PathVariable String id, @RequestBody Achievement achievement){
        var newAchievement = achievementService.updateAchievement(id, achievement);
        return ResponseEntity.ok(newAchievement).getBody();
    }

    @Operation(
            summary = "Listando todas as conquistas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Todos as conquistas listados com sucesso"
                    ),
            }
    )
    @GetMapping(value = "")
    public ResponseEntity<List<Achievement>> listAchievements(){
        var achievement = achievementService.listAchievements();
        return ResponseEntity.ok(achievement);
    }

    @Operation(
            summary = "Detalhe da conquista",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalhe da conquista listada com sucesso"
                    ),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Achievement>> detailAchievement(@PathVariable String id){
        var achievement = achievementService.detailAchievement(id);
        return ResponseEntity.ok(achievement);
    }

    @Operation(
            summary = "Completando uma conquista",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conquista completada com sucesso"
                    ),
            }
    )
    @PutMapping("complete/{id}")
	public ResponseEntity<Optional<Achievement>> completeAchievement(@PathVariable String id){
		var achievement = achievementService.completeAchievement(id);
		return ResponseEntity.ok(achievement);
	}


    @Operation(
            summary = "Deletando uma conquista",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Conquista deletada com sucesso"),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable String id){
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
    
}