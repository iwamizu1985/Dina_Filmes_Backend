
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

@SuppressWarnings("unused")
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



@GetMapping("/api/assistidos")
    public ResponseEntity<List<FilmeEntity>> listarAssistidos(@RequestParam int codigoUsuario) {
        List<FilmeEntity> assistidos = repository.carregarListaAssistidos(codigoUsuario);
        if (assistidos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(assistidos);
        }
    }

    @PutMapping("/api/lista/assistido")
    public ResponseEntity<?> adicionarAssistido(@RequestBody ListaEntity listaEntity) {
        try {
            // Verifica se o filme já está na lista de assistido do usuário
            Optional<ListaEntity> assistidoExistente = repository.verificarFilmeAssistido(listaEntity.getCodigoUsuario(), listaEntity.getCodigoFilme());
            
            if (assistidoExistente.isPresent()) {
                // Se já existe, busca a entidade e atualiza o estado do assistido
                ListaEntity assistido = assistidoExistente.get();
                assistido.setFilmeAssistido(true); // Colocar filme na lista de assistido
                // Salva a atualização
                repository.save(assistido); 
                return ResponseEntity.ok(assistido); // Retorna o objeto atualizado
            } else {
                // Se não existe, cria uma nova entrada
                listaEntity.setFilmeAssistido(true);
                // Certifique-se de que as outras propriedades são inicializadas corretamente
                ListaEntity savedEntity = repository.save(listaEntity);
                return ResponseEntity.ok(savedEntity); // Retorna a nova entrada
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/api/lista/assistido")
    public ResponseEntity<Boolean> verificarSeAssistido(
    @RequestParam int codigoUsuario, @RequestParam int codigoFilme) {
    
    Optional<ListaEntity> assistido = repository.verificarFilmeAssistido(codigoUsuario, codigoFilme);
    return ResponseEntity.ok(assistido.isPresent()); // Retorna true se o filme for assistido, false caso contrário
}


@PutMapping("/api/lista/desassistido")
public ResponseEntity<ListaEntity> desassistirFilme(@RequestBody ListaEntity listaEntity) {
    Optional<ListaEntity> assistidoExistente = repository.verificarFilmeAssistido(listaEntity.getCodigoUsuario(), listaEntity.getCodigoFilme());
    
    if (assistidoExistente.isPresent()) {
        ListaEntity assistido = assistidoExistente.get();
        assistido.setFilmeAssistido(false); // Desassistir  o filme
        repository.save(assistido); // Salva a atualização
        return ResponseEntity.ok(assistido); // Retorna o objeto atualizado
    } else {
        return ResponseEntity.notFound().build();
    }
}







@GetMapping("/api/assistir")
    public ResponseEntity<List<FilmeEntity>> listarAassistir(@RequestParam int codigoUsuario) {
        List<FilmeEntity> assistir = repository.carregarListaAAssistir(codigoUsuario);
        if (assistir.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(assistir);
        }
    }

    @PutMapping("/api/lista/assistir")
    public ResponseEntity<?> adicionarAAssisitir(@RequestBody ListaEntity listaEntity) {
        try {
            // Verifica se o filme já está na lista à assistir do usuário
            Optional<ListaEntity> assistirExistente = repository.verificarFilmeAAssistir(listaEntity.getCodigoUsuario(), listaEntity.getCodigoFilme());
            
            if (assistirExistente.isPresent()) {
                // Se já existe, busca a entidade e atualiza o estado de à assistir
                ListaEntity assistir = assistirExistente.get();
                assistir.setFilmeFavorito(true); // Colocar  o filme na lista de à assistir
                // Salva a atualização
                repository.save(assistir); 
                return ResponseEntity.ok(assistir); // Retorna o objeto atualizado
            } else {
                // Se não existe, cria uma nova entrada
                listaEntity.setFilmeAAssistir(true);
                // Certifique-se de que as outras propriedades são inicializadas corretamente
                ListaEntity savedEntity = repository.save(listaEntity);
                return ResponseEntity.ok(savedEntity); // Retorna a nova entrada
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/api/lista/assistir")
    public ResponseEntity<Boolean> verificarSeAAssistido(
    @RequestParam int codigoUsuario, @RequestParam int codigoFilme) {
    
    Optional<ListaEntity> assistir = repository.verificarFilmeFavorito(codigoUsuario, codigoFilme);
    return ResponseEntity.ok(assistir.isPresent()); // Retorna true se o filme for colocado na lista à assistir, false caso contrário
}


@PutMapping("/api/lista/desaassitir")
public ResponseEntity<ListaEntity> desaassistirFilme(@RequestBody ListaEntity listaEntity) {
    Optional<ListaEntity> aassistirExistente = repository.verificarFilmeAAssistir(listaEntity.getCodigoUsuario(), listaEntity.getCodigoFilme());
    
    if (aassistirExistente.isPresent()) {
        ListaEntity aassistir = aassistirExistente.get();
        aassistir.setFilmeFavorito(false); // Desfavoritar o filme
        repository.save(aassistir); // Salva a atualização
        return ResponseEntity.ok(aassistir); // Retorna o objeto atualizado
    } else {
        return ResponseEntity.notFound().build();
    }
}













}