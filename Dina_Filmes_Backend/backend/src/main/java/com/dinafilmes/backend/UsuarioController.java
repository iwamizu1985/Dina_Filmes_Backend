package com.dinafilmes.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.Date;
import java.time.LocalDateTime;


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
        Optional<UsuarioEntity> usuarioOptional = repository.findById(obj.getCodigoUsuario());

        if (usuarioOptional.isPresent()) {
            UsuarioEntity usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNomeUsuario(obj.getNomeUsuario());
            usuarioExistente.setDataAtualizacao(LocalDateTime.now());
            repository.save(usuarioExistente);
            return ResponseEntity.ok(("Cadastro atualizado com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o cadastro");
    }
}

@GetMapping("/api/usuario/{codigo}")
public ResponseEntity<UsuarioEntity> 
    carregar(@PathVariable int codigo){
    Optional<UsuarioEntity> obj = repository.findById(codigo);
    if(obj.isPresent())    
        return ResponseEntity.ok(obj.get());
    else
        return ResponseEntity.ok(new UsuarioEntity());
}
    
}
