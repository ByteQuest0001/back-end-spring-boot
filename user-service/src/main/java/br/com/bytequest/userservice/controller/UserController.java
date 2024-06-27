package br.com.bytequest.userservice.controller;

import br.com.bytequest.userservice.dto.CreateUserDTO;
import br.com.bytequest.userservice.dto.DetailUserDTO;
import br.com.bytequest.userservice.dto.UpdateUserDTO;
import br.com.bytequest.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuário", description = "Operações relacionadas entidade usuario")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;


    @Operation(
        summary = "Cadastrando um novo usuário",
        responses = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                content = {
                    @Content(schema = @Schema(implementation = DetailUserDTO.class), mediaType = "application/json")
                }
            ),
        }
    )
    @PostMapping
    public ResponseEntity<DetailUserDTO> createUser(@RequestBody CreateUserDTO json) {
        var user = service.create(json);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @Operation(
        summary = "Listando todos os usuarios",
        responses = {
            @ApiResponse(responseCode = "200", description = "Todos os usuários listados com sucesso",
                content = {
                    @Content(schema = @Schema(implementation = DetailUserDTO.class), mediaType = "application/json")
                }
            ),
        }
    )
    @GetMapping
    public ResponseEntity<List<DetailUserDTO>> listUsers() {
        var user = service.listAll();
        return ResponseEntity.ok(user);
    }


    @Operation(
        summary = "Detalhes do usuarios",
        responses = {
            @ApiResponse(responseCode = "200", description = "Detalhes do usuario listado com sucesso",
                content = {
                    @Content(schema = @Schema(implementation = DetailUserDTO.class), mediaType = "application/json")
                }
            ),
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DetailUserDTO> listUserById(@PathVariable Long id) {
        var user = service.listById(id);
        return ResponseEntity.ok(user);
    }


    @Operation(
        summary = "Atualizando o usuario",
        responses = {
            @ApiResponse(responseCode = "200", description = "Atualizando o usuario com sucesso",
                content = {
                    @Content(schema = @Schema(implementation = DetailUserDTO.class), mediaType = "application/json")
                }
            ),
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<DetailUserDTO> updateUser(@RequestBody UpdateUserDTO json, @PathVariable Long id) {
        var user = service.updateUser(json, id);
        return ResponseEntity.ok(user);
    }


    @Operation(
        summary = "Deletando o usuario",
        responses = {
            @ApiResponse(responseCode = "204", description = "Deletando o usuario com sucesso"),
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
