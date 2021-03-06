package com.murilonerdx.aondeta.services.impl;

import com.murilonerdx.aondeta.dto.ReportDTO;
import com.murilonerdx.aondeta.entities.Local;
import com.murilonerdx.aondeta.entities.Report;
import com.murilonerdx.aondeta.exceptions.ResourceNotFoundException;
import com.murilonerdx.aondeta.repositories.LocalRepository;
import com.murilonerdx.aondeta.repositories.ReportRepository;
import com.murilonerdx.aondeta.services.IService;
import com.murilonerdx.aondeta.util.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements IService<ReportDTO, Long> {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private LocalRepository localRepository;

    @Override
    public ReportDTO create(ReportDTO o) {
        if(o.getId()!=null)
            o.setId(null);

        Local local = localRepository.findById(o.getIdLocal()).orElseThrow(()->new ResourceNotFoundException("ID "+o.getIdLocal()+ "NOT FOUND"));

        Report report = DozerConverter.parseObject(o, Report.class);
        report.getLocals().add(local);

        return convertToReportDTO(reportRepository.save(report));
    }

    @Override
    public ReportDTO update(ReportDTO obj, Long id) {
        Report report = reportRepository.findById(id).orElseThrow(()-> new RuntimeException("ID " + id + " not found"));
        report.setDescription(obj.getDescription());
        report.setMomentEvent(obj.getMomentEvent());
        report.setName(obj.getName());
        report.setWhatWasStolen(obj.getWhatWasStolen());

        return convertToReportDTO(reportRepository.save(report));
    }

    @Override
    public void deleteById(Long aLong) {
        reportRepository.deleteById(aLong);
    }

    @Override
    public List<ReportDTO> listAll() {
        return DozerConverter.parseListObjects(reportRepository.findAll(), ReportDTO.class);
    }

    @Override
    public ReportDTO findById(Long aLong) {
        return convertToReportDTO(reportRepository.findById(aLong).orElse(null));
    }

    public ReportDTO convertToReportDTO(Report report){
        return DozerConverter.parseObject(report, ReportDTO.class);
    }

}
