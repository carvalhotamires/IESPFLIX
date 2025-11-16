package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Serie;
import br.uniesp.si.techback.repository.SerieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SerieService {

    private final SerieRepository serieRepository;

    public List<Serie> listar() {
        return serieRepository.findAll();
    }

    public Serie buscarPorId(Long id) {
        return serieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Série não encontrada com o ID: " + id));
    }

    public Serie salvar(Serie serie) {
        return serieRepository.save(serie);
    }

    public Serie atualizar(Long id, Serie serieAtualizada) {
        return serieRepository.findById(id)
                .map(serie -> {
                    serie.setTitulo(serieAtualizada.getTitulo());
                    serie.setSinopse(serieAtualizada.getSinopse());
                    serie.setDataLancamento(serieAtualizada.getDataLancamento());
                    serie.setGenero(serieAtualizada.getGenero());
                    serie.setNumeroTemporadas(serieAtualizada.getNumeroTemporadas());
                    serie.setClassificacaoIndicativa(serieAtualizada.getClassificacaoIndicativa());
                    serie.setEmAndamento(serieAtualizada.getEmAndamento());
                    return serieRepository.save(serie);
                })
                .orElseThrow(() -> new EntityNotFoundException("Série não encontrada com o ID: " + id));
    }

    public void deletar(Long id) {
        if (!serieRepository.existsById(id)) {
            throw new EntityNotFoundException("Série não encontrada com o ID: " + id);
        }
        serieRepository.deleteById(id);
    }
}
