package br.com.lucasbentes.trasferencia.security;

import java.util.Optional;

import br.com.lucasbentes.trasferencia.model.Cliente;
import br.com.lucasbentes.trasferencia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String userCpf) throws UsernameNotFoundException{

        Optional<Cliente> cliente = clienteRepository.findByCpf(userCpf);
        cliente.orElseThrow(() -> new UsernameNotFoundException(userCpf + " Este CPF não foi encontrado"));

        return cliente.map(UserDetailsImpl::new).get();
    }
}
