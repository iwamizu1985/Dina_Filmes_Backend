package com.dinafilmes.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("origins = *") //configuraçao de segurança libera qualquer site  para acessar a api, mas devemos colocar exatamente qual ip nosso frontend tem*
public class ComentariosController {
    
    @PostMapping("/api/comentario")
    public ResponseEntity<String> gravar (@RequestBody ComentariosEntity obj){
        String mensagem = "comentário gravado com sucesso ! " + obj.getComentario();
        return ResponseEntity.ok(mensagem);
    }

    @PutMapping("/api/comentario")
    public ResponseEntity<String> alterar (@RequestBody ComentariosEntity obj){
        String mensagem = "comentário alterado com sucesso ! " + obj.getComentario();
        return ResponseEntity.ok(mensagem);
    }


@GetMapping("/api/comentario/{codigo}")
    public ResponseEntity<ComentariosEntity> carregar(@PathVariable int codigo){
        ComentariosEntity obj = new ComentariosEntity();
        obj.setCodigoComentario(codigo);
        obj.setComentario("Testando método");
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/api/comentario/{codigo}")
    public ResponseEntity<String> remover (@PathVariable int codigo){
        String mensagem = "comentário removido com sucesso ! " + codigo;
        return ResponseEntity.ok(mensagem);
    }
}
