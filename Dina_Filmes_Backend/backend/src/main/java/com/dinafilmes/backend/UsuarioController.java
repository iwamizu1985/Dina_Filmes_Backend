package com.dinafilmes.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.Date;

@RestController
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    UsuarioRepository repository;

@PostMapping("/api/usuario")
public ResponseEntity<String>
    inserir(@RequestBody UsuarioEntity obj){
        repository.save(obj);
        String mensagem = "cadastro realizado com sucesso";
        return ResponseEntity.ok(mensagem);
    }

@PostMapping("/api/usuario/login")
public ResponseEntity<UsuarioEntity>
    fazerLogin(@RequestBody UsuarioEntity obj){
        Optional<UsuarioEntity> retorno = repository.fazerLogin(obj.getEmail(),obj.getSenha());
        if(retorno.isPresent()){
            return ResponseEntity.ok(retorno.get());
        } else {
            return ResponseEntity.ok(new UsuarioEntity());
        }
    }


@GetMapping("/api/usuario/verificar-email")
public ResponseEntity<Boolean> verificarEmail(@RequestParam String email) {
    boolean exists = repository.existsByEmail(email);
    return ResponseEntity.ok(exists);
}

    @PutMapping("/api/usuario")
    public ResponseEntity<String> alterar(@RequestBody UsuarioEntity obj) {
        try {
            // Verifica se o código do usuário é válido
            if (obj.getCodigoUsuario() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código do usuário inválido");
            }

            // Busca o usuário existente
            Optional<UsuarioEntity> usuarioOptional = repository.findById(obj.getCodigoUsuario());

            if (!usuarioOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }

            UsuarioEntity usuarioExistente = usuarioOptional.get();

            // Atualiza somente o campo nomeUsuario
            usuarioExistente.setNomeUsuario(obj.getNomeUsuario());
            //usuarioExistente.setDataAtualizacao(new Date()); // Atualiza a data de modificação

            // Salva a atualização
            repository.save(usuarioExistente);
            return ResponseEntity.ok("Cadastro atualizado");
        } catch (Exception e) {
            e.printStackTrace(); // Log da exceção para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o cadastro");
        }
    }
    
}
