package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.dto.LocalDTO;
import com.murilonerdx.aondeta.dto.ProfileDTO;
import com.murilonerdx.aondeta.entities.Local;
import com.murilonerdx.aondeta.entities.Profile;
import com.murilonerdx.aondeta.repositories.LocalRepository;
import com.murilonerdx.aondeta.services.IService;
import com.murilonerdx.aondeta.util.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalService implements IService<LocalDTO, Long> {

    /*

     * Implementar regras de negocio e retornos de erros customizados
     * Implementar ainda o sistema de atualizar, também modificações para receber DTO
     * Converter DTO para Obj
     */

    @Autowired
    private LocalRepository localRepository;


    @Override
    public LocalDTO create(LocalDTO obj) {
        if(obj.getId()!=null)
            obj.setId(null);

        Local local = localRepository
                .save(DozerConverter.parseObject(obj, Local.class));

        return convertToLocalDTO(local);
    }

    @Override
    public LocalDTO update(LocalDTO obj, Long id) {
        Local local = localRepository.findById(id).orElseThrow(()-> new RuntimeException("ID " + id + " not found"));

        local.setAddress(obj.getAddress());
        local.setLatitude(obj.getLatitude());
        local.setLongitude(obj.getLongitude());

        Local localUpdated = localRepository
                .save(DozerConverter.parseObject(obj, Local.class));

        return convertToLocalDTO(localUpdated);
    }

    @Override
    public void deleteById(Long id) {
        localRepository.deleteById(id);
    }

    @Override
    public List<LocalDTO> listAll() {
        List<Local> locals = localRepository.findAll();

        return DozerConverter.parseListObjects(locals, LocalDTO.class);
    }

    @Override
    public LocalDTO findById(Long id) {
        return convertToLocalDTO(localRepository.findById(id).orElse(null));
    }

    public LocalDTO convertToLocalDTO(Local local){
        return DozerConverter.parseObject(local, LocalDTO.class);
    }
}
