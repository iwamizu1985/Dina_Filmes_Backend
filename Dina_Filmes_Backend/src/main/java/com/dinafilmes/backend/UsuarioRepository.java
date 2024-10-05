package com.dinafilmes.backend;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>, JpaSpecificationExecutor<UsuarioEntity> {

        @Query(value = "select * from usuario where email=?1 and senha=?2 ",
        nativeQuery = true)
        Optional<UsuarioEntity> fazerLogin(String email, String senha);

        boolean existsByEmail(String email);
    
}
