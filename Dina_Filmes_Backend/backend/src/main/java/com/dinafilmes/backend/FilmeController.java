package com.dinafilmes.backend;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class FilmeController {
    @Autowired
    FilmeRepository repository;

        @GetMapping("/api/filme/{codigoFilme}")
    public ResponseEntity<FilmeEntity> 
        carregar(@PathVariable int codigoFilme){
        Optional<FilmeEntity> obj = repository.findById(codigoFilme);
            if(obj.isPresent())    
                return ResponseEntity.ok(obj.get());
            else
                return ResponseEntity.ok(new FilmeEntity()); 
    }

        @GetMapping("/api/filme")
    public ResponseEntity<List<FilmeEntity>> listar
    (@RequestParam(required = false) String search,
    @RequestParam(required = false) String categoria) {
        List<FilmeEntity> filmes;

        if ((search == null || search.isEmpty()) && (categoria == null || categoria.isEmpty())) {
            filmes = repository.findAll();
        } else if (search != null && !search.isEmpty()) {
            filmes = repository.findByNomeFilmeContainingIgnoreCase(search);
        } else if (categoria != null && !categoria.isEmpty()) {
            filmes = repository.findByGeneroContainingIgnoreCase(categoria);
        } else {
            // Se nenhum parâmetro for passado, retorna todos os produtos
            filmes = repository.findAll();
        }
    
        return ResponseEntity.ok(filmes);
    }

   
}
