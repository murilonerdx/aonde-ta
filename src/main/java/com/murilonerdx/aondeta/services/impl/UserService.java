package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.dto.AuthenticationDTO;
import com.murilonerdx.aondeta.dto.ProfileDTO;
import com.murilonerdx.aondeta.dto.ReportDTO;
import com.murilonerdx.aondeta.entities.Profile;
import com.murilonerdx.aondeta.entities.Report;
import com.murilonerdx.aondeta.entities.User;
import com.murilonerdx.aondeta.exceptions.EmailNotFoundError;
import com.murilonerdx.aondeta.exceptions.ResourceNotFoundException;
import com.murilonerdx.aondeta.repositories.UserRepository;
import com.murilonerdx.aondeta.services.IService;
import com.murilonerdx.aondeta.util.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, IService<AuthenticationDTO, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthenticationDTO create(AuthenticationDTO o) {
        User user = new User();
        user.setEmail(o.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(o.getPassword()));
        return convertToUserDTO(userRepository.save(user));
    }

    @Override
    public AuthenticationDTO update(AuthenticationDTO o, Long aLong) throws Exception {
        if(aLong==null){
            throw new Exception("ID não pode ser nulo");
        }
        User user = userRepository.findById(aLong).orElseThrow(()->new ResourceNotFoundException("ID não existe"));
        user.setPassword(o.getPassword());

        User userUpdated = userRepository.save(user);
        return convertToUserDTO(userRepository.save(userUpdated));
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public List<AuthenticationDTO> listAll() {
        List<User> users = userRepository.findAll();
        return convertToListProfileDTO(users);
    }

    @Override
    public AuthenticationDTO findById(Long aLong) {
        User user = userRepository.findById(aLong).orElseThrow(()->new ResourceNotFoundException("ID não existe"));
        return convertToUserDTO(user);
    }

    public AuthenticationDTO convertToUserDTO(User user){
        return DozerConverter.parseObject(user, AuthenticationDTO.class);
    }

    private List<AuthenticationDTO> convertToListProfileDTO(List<User> profiles) {
        return DozerConverter.parseListObjects(profiles, AuthenticationDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws RuntimeException {
        Optional<User> usuario = userRepository.findByEmail(email);
        if(usuario.isEmpty()) throw new EmailNotFoundError("Email não encontrado");
        return usuario.get();
    }
}
