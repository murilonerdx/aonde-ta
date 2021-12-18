package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.entities.Profile;
import com.murilonerdx.aondeta.entities.User;
import com.murilonerdx.aondeta.repositories.ProfileRepository;
import com.murilonerdx.aondeta.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements IService<Profile, Long> {

    /*

        * Implementar regras de negocio e retornos de erros customizados
        * Implementar ainda o sistema de atualizar, também modificações para receber DTO
        * Converter DTO para Obj
     */

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile create(Profile o) {
        return profileRepository.save(o);
    }

    @Override
    public Profile update(Profile o, Long aLong) {
        return profileRepository.save(o);
    }

    @Override
    public void deleteById(Long aLong) {
        profileRepository.deleteById(aLong);
    }

    @Override
    public List<Profile> listAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findById(Long aLong) {
        return profileRepository.findById(aLong).orElse(null);
    }
}
