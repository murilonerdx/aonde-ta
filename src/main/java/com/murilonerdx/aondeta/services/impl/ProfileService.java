package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.dto.ProfileDTO;
import com.murilonerdx.aondeta.entities.Profile;
import com.murilonerdx.aondeta.entities.User;
import com.murilonerdx.aondeta.exceptions.EmailExistException;
import com.murilonerdx.aondeta.exceptions.EmailNotFoundError;
import com.murilonerdx.aondeta.repositories.ProfileRepository;
import com.murilonerdx.aondeta.repositories.UserRepository;
import com.murilonerdx.aondeta.services.IService;
import com.murilonerdx.aondeta.util.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService implements IService<ProfileDTO, Long>  {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ProfileDTO create(ProfileDTO o) {
        try{
            Profile profile = profileRepository.
                    save(DozerConverter.parseObject(o, Profile.class));
            return convertToProfileDTO(profile);
        }catch(DataIntegrityViolationException e){
            throw new EmailExistException("E-mail já cadastrado no banco");
        }
    }

    @Override
    public ProfileDTO update(ProfileDTO obj, Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(()-> new RuntimeException("ID " + id + " not found"));
        profile.setDescription(obj.getDescription());
        profile.setPhone(obj.getPhone());
        profile.setName(obj.getName());

        return convertToProfileDTO(profile);
    }

    @Override
    public void deleteById(Long aLong) {
        profileRepository.deleteById(aLong);
    }

    @Override
    public List<ProfileDTO> listAll() {
        List<Profile> profiles = profileRepository.findAll();

        return convertToListProfileDTO(profiles);
    }

    private List<ProfileDTO> convertToListProfileDTO(List<Profile> profiles) {
        return DozerConverter.parseListObjects(profiles, ProfileDTO.class);
    }

    @Override
    public ProfileDTO findById(Long aLong) {
        return convertToProfileDTO(profileRepository.findById(aLong).orElse(null));
    }

    public ProfileDTO convertToProfileDTO(Profile profile){
        return DozerConverter.parseObject(profile, ProfileDTO.class);
    }

}
