
package com.dinafilmes.backend;

import java.util.List;

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

@RestController
@CrossOrigin(origins = "*")
public class ListaController {

    @Autowired
    ListaRepository repository;

    @GetMapping("/api/favoritos")
    public ResponseEntity<List<FilmeEntity>> listarFavoritos(@RequestParam int codigoUsuario) {
        List<FilmeEntity> favoritos = repository.carregarListaFavoritos(codigoUsuario);
        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(favoritos);
        }
    }

    @PutMapping("/api/lista/favoritar")
    public ResponseEntity<?> adicionarFavorito(@RequestBody ListaEntity listaEntity) {
        try {
            // Verifica se o filme já está na lista de favoritos do usuário
            Optional<ListaEntity> favoritoExistente = repository.verificarFilmeFavorito(listaEntity.getCodigoUsuario(), listaEntity.getCodigoFilme());
            
            if (favoritoExistente.isPresent()) {
                // Se já existe, busca a entidade e atualiza o estado do favorito
                ListaEntity favorito = favoritoExistente.get();
                favorito.setFilmeFavorito(true); // Favoritar o filme
                // Salva a atualização
                repository.save(favorito); 
                return ResponseEntity.ok(favorito); // Retorna o objeto atualizado
            } else {
                // Se não existe, cria uma nova entrada
                listaEntity.setFilmeFavorito(true);
                // Certifique-se de que as outras propriedades são inicializadas corretamente
                ListaEntity savedEntity = repository.save(listaEntity);
                return ResponseEntity.ok(savedEntity); // Retorna a nova entrada
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/api/lista/favorito")
    public ResponseEntity<Boolean> verificarSeFavorito(
    @RequestParam int codigoUsuario, @RequestParam int codigoFilme) {
    
    Optional<ListaEntity> favorito = repository.verificarFilmeFavorito(codigoUsuario, codigoFilme);
    return ResponseEntity.ok(favorito.isPresent()); // Retorna true se o filme for favoritado, false caso contrário
}


@PutMapping("/api/lista/desfavoritar")
public ResponseEntity<ListaEntity> desfavoritarFilme(@RequestBody ListaEntity listaEntity) {
    Optional<ListaEntity> favoritoExistente = repository.verificarFilmeFavorito(listaEntity.getCodigoUsuario(), listaEntity.getCodigoFilme());
    
    if (favoritoExistente.isPresent()) {
        ListaEntity favorito = favoritoExistente.get();
        favorito.setFilmeFavorito(false); // Desfavoritar o filme
        repository.save(favorito); // Salva a atualização
        return ResponseEntity.ok(favorito); // Retorna o objeto atualizado
    } else {
        return ResponseEntity.notFound().build();
    }
}

}