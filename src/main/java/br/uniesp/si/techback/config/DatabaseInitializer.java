package br.uniesp.si.techback.config;

import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@Slf4j
@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Garante que o diretório de dados existe
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                boolean created = dataDir.mkdirs();
                if (created) {
                    log.info("Diretório de dados criado em: {}", dataDir.getAbsolutePath());
                }
            }

            String adminEmail = "tamiresoak@gmail.com";
            String adminPassword = "Admin@123"; // Senha padrão segura
            
            // Verifica se já existe um usuário admin
            if (!usuarioRepository.existsByEmail(adminEmail)) {
                try {
                    Usuario admin = new Usuario();
                    admin.setName("Admin");
                    admin.setEmail(adminEmail);
                    admin.setSenha(passwordEncoder.encode(adminPassword));
                    admin.setRole(Usuario.UserRole.ADMIN);
                    
                    usuarioRepository.save(admin);
                    log.info("===============================================");
                    log.info("USUÁRIO ADMIN CRIADO COM SUCESSO");
                    log.info("Email: {}", adminEmail);
                    log.info("Senha: {}", adminPassword);
                    log.info("===============================================");
                } catch (Exception e) {
                    log.error("ERRO AO CRIAR USUÁRIO ADMIN: {}", e.getMessage(), e);
                }
            } else {
                log.info("Usuário admin já existe no banco de dados.");
            }
        };
    }
}
