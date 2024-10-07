package com.dinafilmes.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface ListaRepository extends JpaRepository<ListaEntity, Integer> {
    
    @Query("SELECT f FROM FilmeEntity f JOIN ListaEntity l ON f.codigoFilme = l.codigoFilme WHERE l.codigoUsuario = ?1 AND l.filmeFavorito = true")
    List<FilmeEntity> carregarListaFavoritos(int codigoUsuario);



    @Query("SELECT l FROM ListaEntity l WHERE l.codigoUsuario = ?1 AND l.codigoFilme = ?2")
    Optional<ListaEntity> verificarFilmeFavorito(int codigoUsuario, int codigoFilme);

    
    @Query("SELECT f FROM FilmeEntity f JOIN ListaEntity l ON f.codigoFilme = l.codigoFilme WHERE l.codigoUsuario = ?1 AND l.filmeAssistido = true")
    List<FilmeEntity> carregarListaAssistidos(int codigoUsuario);



    @Query("SELECT l FROM ListaEntity l WHERE l.codigoUsuario = ?1 AND l.codigoFilme = ?2")
    Optional<ListaEntity> verificarFilmeAssistido(int codigoUsuario, int codigoFilme);


    @Query("SELECT f FROM FilmeEntity f JOIN ListaEntity l ON f.codigoFilme = l.codigoFilme WHERE l.codigoUsuario = ?1 AND l.filmeAssistir = true")
    List<FilmeEntity> carregarListaAAssistir(int codigoUsuario);



    @Query("SELECT l FROM ListaEntity l WHERE l.codigoUsuario = ?1 AND l.codigoFilme = ?2")
    Optional<ListaEntity> verificarFilmeAAssistir(int codigoUsuario, int codigoFilme);
}

