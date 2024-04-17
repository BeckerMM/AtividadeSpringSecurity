package br.org.sesisenai.ava.security.service;

import br.org.sesisenai.ava.entity.Usuario;
import br.org.sesisenai.ava.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUSerDetailsUsername(username);
        if (usuarioOptional.isPresent()){
            usuarioOptional.get().getUSerDetails().setEnabled(true);
            return usuarioOptional.get().getUSerDetails();
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
