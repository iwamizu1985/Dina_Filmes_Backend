package com.dinafilmes.backend;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.ArrayUtils;


import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;


@SuppressWarnings("unused")
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
            usuarioExistente.setSenha(obj.getSenha());
            usuarioExistente.setAtivo(obj.isAtivo());
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

@PostMapping("/api/usuario/{codigo}/foto")
public ResponseEntity<String> uploadFotoUsuario(@PathVariable int codigo, @RequestParam("fotoUsuario") MultipartFile file) {
    Optional<UsuarioEntity> usuarioOptional = repository.findById(codigo);
    
    if (!file.isEmpty() && usuarioOptional.isPresent()) {
        try {
            // Converte MultipartFile para Byte[] manualmente
            byte[] bytes = file.getBytes();
            Byte[] byteObjects = new Byte[bytes.length];
            
            for (int i = 0; i < bytes.length; i++) {
                byteObjects[i] = bytes[i];
            }

            // Inferir o MIME type a partir da extensão do arquivo
            String fotoUsuarioMimeType = file.getContentType();

            // Salva a foto e o MIME type no usuário
            UsuarioEntity usuario = usuarioOptional.get();
            usuario.setFotoUsuario(byteObjects);
            usuario.setFotoUsuarioMimeType(fotoUsuarioMimeType); // Armazena o MIME type
            repository.save(usuario);
            
            return ResponseEntity.ok("Foto enviada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();  // Log detalhado para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a foto.");
        }
    } else if (file.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arquivo de foto vazio.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }
}


@GetMapping("/api/usuario/{codigo}/foto")
public void renderFotoUsuario(@PathVariable int codigo, HttpServletResponse response) throws IOException {
    Optional<UsuarioEntity> usuarioOptional = repository.findById(codigo);
    
    if (usuarioOptional.isPresent() && usuarioOptional.get().getFotoUsuario() != null) {
        // Converte Byte[] para byte[] manualmente
        Byte[] fotoUsuario = usuarioOptional.get().getFotoUsuario();
        byte[] byteArray = new byte[fotoUsuario.length];
        
        for (int i = 0; i < fotoUsuario.length; i++) {
            byteArray[i] = fotoUsuario[i];
        }

        String fotoUsuarioMimeType = usuarioOptional.get().getFotoUsuarioMimeType();
        response.setContentType(fotoUsuarioMimeType);
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    } else {
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}

    
}