package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalheService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetalheService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Converte a Role do seu modelo para a Authority do Spring Security
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha()) // A senha já está codificada
                .authorities(String.valueOf(usuario.getRole())) // Assumindo que getRole() retorna o nome da Role (ex: "ADMIN")
                .build();
    }
}
