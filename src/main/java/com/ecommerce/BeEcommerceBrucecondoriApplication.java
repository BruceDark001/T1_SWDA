package com.ecommerce;

import com.ecommerce.entity.Usuario;
import com.ecommerce.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BeEcommerceBrucecondoriApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeEcommerceBrucecondoriApplication.class, args);
    }

    @Bean
    CommandLineRunner initUser(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.findByUsername("bruce").isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setUsername("bruce");
                usuario.setPassword(passwordEncoder.encode("123456"));
                usuario.setRol("ADMIN");
                repository.save(usuario);
            }
        };
    }
}