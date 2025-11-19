package br.uniesp.si.techback.repository;
import br.uniesp.si.techback.model.Favoritos;
import br.uniesp.si.techback.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, Long> {

}
