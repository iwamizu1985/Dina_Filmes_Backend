package com.dinafilmes.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

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
    
}
