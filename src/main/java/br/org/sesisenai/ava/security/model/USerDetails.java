package br.org.sesisenai.ava.security.model;

import br.org.sesisenai.ava.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class USerDetails implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToOne(mappedBy = "uSerDetails")
    private Usuario usuario;
    private  boolean enabled = true;
    private  boolean accountNonExpired = true;
    private  boolean accountNonLocked = true;
    private  boolean credentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
