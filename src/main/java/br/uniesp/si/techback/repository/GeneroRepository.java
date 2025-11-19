package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);

    List<Genero> findAllByOrderByNomeAsc();

    List<Genero> findByNomeContainingIgnoreCase(String termo);

    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Long id);

}
