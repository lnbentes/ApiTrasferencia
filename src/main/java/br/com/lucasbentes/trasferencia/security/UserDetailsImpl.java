package br.com.lucasbentes.trasferencia.security;

import java.util.Collection;
import java.util.List;

import br.com.lucasbentes.trasferencia.model.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{  // Vai servir para o usuario logar

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(Cliente cliente) {
        this.userName = cliente.getCpf();
        this.password = cliente.getSenha();
    }

    public UserDetailsImpl() {  // manter um costrutor vazio para teste

    }


    // Fazendo com que retorne a nossa senha e usuario
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    // Metodos padroes do basic security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
