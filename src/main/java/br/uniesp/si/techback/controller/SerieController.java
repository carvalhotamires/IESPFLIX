package br.uniesp.si.techback.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import br.uniesp.si.techback.model.Serie;
import br.uniesp.si.techback.service.SerieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class SerieController {

    private final SerieService serieService;

    @GetMapping
    public List<Serie> listar() {
        return serieService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serieService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Serie> criar(@Valid @RequestBody Serie serie) {
        Serie serieSalva = serieService.salvar(serie);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serieSalva.getId())
                .toUri();
        return ResponseEntity.created(location).body(serieSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Serie> atualizar(@PathVariable Long id, @Valid @RequestBody Serie serie) {
        try {
            Serie serieAtualizada = serieService.atualizar(id, serie);
            return ResponseEntity.ok(serieAtualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            serieService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
