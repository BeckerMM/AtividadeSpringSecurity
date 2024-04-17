package br.org.sesisenai.ava.dto.implementation.usuario;

import br.org.sesisenai.ava.dto.abstraction.CriacaoRequestConversorDTO;
import br.org.sesisenai.ava.entity.Usuario;
import br.org.sesisenai.ava.security.model.USerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestDTO implements CriacaoRequestConversorDTO<Usuario> {

    private String nome;
    private String senha;
    private String email;
    private USerDetails uSerDetails;


    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setUSerDetails(uSerDetails);
        usuario.setDataCadastro(LocalDateTime.now());
        return usuario;
    }


}