package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.entities.Profile;
import com.murilonerdx.aondeta.entities.Report;
import com.murilonerdx.aondeta.repositories.ReportRepository;
import com.murilonerdx.aondeta.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements IService<Report, Long> {

    @Autowired
    private ReportRepository reportRepository;

    /*

     * Implementar regras de negocio e retornos de erros customizados
     * Implementar ainda o sistema de atualizar, também modificações para receber DTO
     * Converter DTO para Obj
     */


    @Override
    public Report create(Report o) {
        return reportRepository.save(o);
    }

    @Override
    public Report update(Report o, Long aLong) {
        return reportRepository.save(o);
    }

    @Override
    public void deleteById(Long aLong) {
        reportRepository.deleteById(aLong);
    }

    @Override
    public List<Report> listAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report findById(Long aLong) {
        return reportRepository.findById(aLong).orElse(null);
    }

}
