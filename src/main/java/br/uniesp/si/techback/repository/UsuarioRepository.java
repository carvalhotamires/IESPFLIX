package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.model.Usuario.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByNameContainingIgnoreCase(String name);
    
    List<Usuario> findByRole(UserRole role);
    
    List<Usuario> findByNameContainingIgnoreCaseAndRole(String name, UserRole role);
    
    Page<Usuario> findAllByOrderByName(Pageable pageable);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) LIKE LOWER(concat('%', :email, '%'))")
    List<Usuario> buscarPorEmailParcial(@Param("email") String email);
    

    @Query("SELECT u FROM Usuario u WHERE " +
           "LOWER(u.name) LIKE LOWER(concat('%', :termo, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(concat('%', :termo, '%'))")
    List<Usuario> buscarPorNomeOuEmail(@Param("termo") String termo);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.senha = :novaSenha WHERE u.id = :id")
    void atualizarSenha(@Param("id") Long id, @Param("novaSenha") String novaSenha);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.role = :novoRole WHERE u.id = :id")
    void atualizarRole(@Param("id") Long id, @Param("novoRole") Usuario.UserRole novoRole);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.email = :email AND u.id <> :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

    long countByRole(UserRole role);

    List<Usuario> findFirst10ByOrderByName();
}
