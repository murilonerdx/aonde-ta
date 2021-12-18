package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.entities.User;
import com.murilonerdx.aondeta.repositories.UserRepository;
import com.murilonerdx.aondeta.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService, IService<User, Long> {

    /*

     * Pegar e-mail do user e passar para profile
     * Ao salvar um profile registrar com e-mail d user.
     * Converter DTO para Obj
     */

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> usuario = userRepository.findByEmail(email);
        if(usuario.isEmpty()) throw new UsernameNotFoundException("Email não encontrado");
        return usuario.get();
    }

    @Override
    public User create(User o) {
        return userRepository.save(o);
    }

    @Override
    public User update(User o, Long id) {
        return userRepository.save(o);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email).get();
    }
}
