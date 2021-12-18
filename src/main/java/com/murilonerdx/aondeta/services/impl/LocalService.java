package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.entities.Local;
import com.murilonerdx.aondeta.repositories.LocalRepository;
import com.murilonerdx.aondeta.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService implements IService<Local, Long> {

    /*

     * Implementar regras de negocio e retornos de erros customizados
     * Implementar ainda o sistema de atualizar, também modificações para receber DTO
     * Converter DTO para Obj
     */

    @Autowired
    private LocalRepository localRepository;


    @Override
    public Local create(Local obj) {
        return localRepository.save(obj);
    }

    @Override
    public Local update(Local obj, Long id) {
        return localRepository.save(obj);
    }

    @Override
    public void deleteById(Long id) {
        localRepository.deleteById(id);
    }

    @Override
    public List<Local> listAll() {
        return localRepository.findAll();
    }

    @Override
    public Local findById(Long id) {
        return localRepository.findById(id).orElse(null);
    }
}
