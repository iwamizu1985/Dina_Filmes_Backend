// package com.dinafilmes.backend;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.ByteArrayInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.time.LocalDateTime;
// import java.util.Optional;
// import java.util.UUID;

// @SuppressWarnings("unused")
// @RestController
// @CrossOrigin("*")
// public class UsuarioController {

//     @Autowired
//     private UsuarioRepository repository;

//     // @Autowired
//     // private EmailService emailService;

//     @PostMapping("/api/usuario")
//     public ResponseEntity<String> inserir(@RequestBody UsuarioEntity obj) {
//         repository.save(obj);
//         String mensagem = "Cadastro realizado com sucesso";
//         return ResponseEntity.ok(mensagem);
//     }

//     @PostMapping("/api/usuario/login")
//     public ResponseEntity<UsuarioEntity> fazerLogin(@RequestBody UsuarioEntity obj) {
//         Optional<UsuarioEntity> retorno = repository.fazerLogin(obj.getEmail(), obj.getSenha());
//         if (retorno.isPresent()) {
//             return ResponseEntity.ok(retorno.get());
//         } else {
//             return ResponseEntity.ok(new UsuarioEntity());
//         }
//     }

    // @PostMapping("/usuario/recuperar-senha")
    // public String solicitarRecuperacaoSenha(@RequestParam String email) {
    //     UsuarioEntity usuario = repository.findByEmail(email);

    //     if (usuario == null) {
    //         return "Usuário não encontrado";
    //     }

        // Gerar um token único
        // String token = UUID.randomUUID().toString();
        // usuario.setResetPasswordToken(token);
        // usuario.setTokenExpirationDate(LocalDateTime.now().plusHours(1)); // Expira em 1 hora
        // repository.save(usuario);

        // Enviar e-mail com o link de recuperação de senha
    //     String resetUrl = "http://localhost:4200.com/recuperar-senha?token=" + token;
    //     String mensagem = "Clique no link para redefinir sua senha: " + resetUrl;

    //     emailService.sendEmail(usuario.getEmail(), "Recuperação de Senha", mensagem);

    //     return "Instruções de recuperação de senha enviadas para o e-mail.";
    // }

    // @PostMapping("/resetar-senha")
    // public String redefinirSenha(@RequestParam String token, @RequestParam String novaSenha) {
    //     UsuarioEntity usuario = repository.findByResetPasswordToken(token);
    
    //     if (usuario == null || usuario.getTokenExpirationDate().isBefore(LocalDateTime.now())) {
    //         return "Token inválido ou expirado";
    //     }
    
    //     // Atualizar a senha do usuário
    //     usuario.setSenha(novaSenha); // Alterado para setSenha
    //     usuario.setResetPasswordToken(null);
    //     usuario.setTokenExpirationDate(null);
//     //     repository.save(usuario);
    
//         return "Senha redefinida com sucesso";
// }

// }

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
import java.util.Optional;
import java.util.UUID;


@SuppressWarnings("unused")
@RestController
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    UsuarioRepository repository;

    
    @Autowired
    private EmailService emailService;

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

    
    @PostMapping("/api/usuario/recuperar-senha")
    public String solicitarRecuperacaoSenha(@RequestParam String email) {
        UsuarioEntity usuario = repository.findByEmail(email);

        if (usuario == null) {
            return "Usuário não encontrado";
        }

        // Gerar um token único
        String token = UUID.randomUUID().toString();
        usuario.setResetPasswordToken(token);
        usuario.setTokenExpirationDate(LocalDateTime.now().plusHours(1)); // Expira em 1 hora
        repository.save(usuario);

        // Enviar e-mail com o link de recuperação de senha
        String resetUrl = "http://localhost:4200/nova-senha?token=" + token;
        // String resetUrl = "http://localhost:4200 .com /recuperar-senha?token=" + token; original, antes de modificar
        String mensagem = "Clique no link para redefinir sua senha: " + resetUrl;

        emailService.sendEmail(usuario.getEmail(), "Recuperação de Senha", mensagem);

        return "Instruções de recuperação de senha enviadas para o e-mail.";
    }

//     @PostMapping("/api/usuario/resetar-senha")
//     public String redefinirSenha(@RequestParam String token, @RequestParam String novaSenha) {
//         UsuarioEntity usuario = repository.findByResetPasswordToken(token);
    
        


//         if (usuario == null || usuario.getTokenExpirationDate().isBefore(LocalDateTime.now())) {
//             return "Token inválido ou expirado";
//         }
    
//         // Atualizar a senha do usuário
//         usuario.setSenha(novaSenha); // Alterado para setSenha
//         usuario.setResetPasswordToken(null);
//         usuario.setTokenExpirationDate(null);
//         repository.save(usuario);
    
//         return "Senha redefinida com sucesso";
// }

@PostMapping("/api/usuario/resetar-senha")
public ResponseEntity<String> redefinirSenha(@RequestBody ResetSenhaRequest request) {
    String token = request.getToken();
    String novaSenha = request.getNovaSenha();

    UsuarioEntity usuario = repository.findByResetPasswordToken(token);

    if (usuario == null || usuario.getTokenExpirationDate().isBefore(LocalDateTime.now())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado");
    }

    // Atualizar a senha do usuário
    usuario.setSenha(novaSenha);
    usuario.setResetPasswordToken(null);
    usuario.setTokenExpirationDate(null);
    repository.save(usuario);

    return ResponseEntity.ok("Senha redefinida com sucesso");
}

}