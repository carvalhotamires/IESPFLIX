package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.UsuarioRequest;
import br.uniesp.si.techback.dto.UsuarioResponse;
import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.UsuarioRepository;
import br.uniesp.si.techback.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest,
                                                      UriComponentsBuilder uriBuilder) {
        log.info("Criando novo usuário com email: {}", usuarioRequest.getEmail());
        
        if (usuarioRepository.existsByEmail(usuarioRequest.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        Usuario usuario = new Usuario();
        usuario.setName(usuarioRequest.getName());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getPassword()));
        
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        
        URI uri = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuarioSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(UsuarioResponse.fromEntity(usuarioSalvo));
    }
    @GetMapping
    public List<Usuario> listarUsuarios() {
        log.info("Listando usuários com paginação:");
        return usuarioService.buscarUsuarios();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@PathVariable Long id) {
        log.info("Buscando usuário com id: {}", id);
        return usuarioRepository.findById(id)
                .map(UsuarioResponse::fromEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o ID: " + id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest usuarioRequest) {

        log.info("Atualizando usuário com id: {}", id);

        // Converte UsuarioRequest em Usuario
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setName(usuarioRequest.getName());
        usuarioAtualizado.setEmail(usuarioRequest.getEmail());
        if (usuarioRequest.getPassword() != null && !usuarioRequest.getPassword().isEmpty()) {
            usuarioAtualizado.setSenha(passwordEncoder.encode(usuarioRequest.getPassword()));
        }

        // Chama o service
        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);

        // Retorna a resposta com DTO
        return ResponseEntity.ok(UsuarioResponse.fromEntity(usuario));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id) {
        log.info("Deletando usuário com id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o ID: " + id));
        
        usuarioRepository.delete(usuario);
    }
}
