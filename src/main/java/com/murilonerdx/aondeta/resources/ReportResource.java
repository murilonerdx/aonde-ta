package com.murilonerdx.aondeta.resources;

import com.murilonerdx.aondeta.dto.ReportDTO;
import com.murilonerdx.aondeta.entities.Report;
import com.murilonerdx.aondeta.services.impl.LocalService;
import com.murilonerdx.aondeta.services.impl.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Endpoint de Report")
@RestController
@RequestMapping("/report")
public class ReportResource {

    @Autowired
    private ReportService reportService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @ApiOperation(value = "Create a report")
    @GetMapping(value="/local-report/{id}",produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<ReportDTO> create(@PathVariable("id") Long id, @RequestBody @Valid ReportDTO reportDTO){
        reportDTO.setIdLocal(id);
        ReportDTO report = reportService.create(reportDTO);

        return ResponseEntity.ok().body(report);
    }
}
