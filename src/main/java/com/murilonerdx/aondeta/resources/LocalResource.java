package com.murilonerdx.aondeta.resources;

import com.murilonerdx.aondeta.dto.LocalDTO;
import com.murilonerdx.aondeta.services.impl.LocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(tags = "Endpoint de Local")
@RestController
@RequestMapping("/local")
public class LocalResource {

    @Autowired
    private LocalService localService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @ApiOperation(value = "Find all locals")
    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<List<LocalDTO>> getAll(){
        List<LocalDTO> localDTOs = localService.listAll();

        return ResponseEntity.ok().body(localDTOs);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @ApiOperation(value = "Find local by id")
    @GetMapping(value="/{id}",produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<LocalDTO> findById(@PathVariable("id") Long id){
        LocalDTO localDTO = localService.findById(id);

        return ResponseEntity.ok().body(localDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @ApiOperation(value = "Create a new local")
    @PostMapping(produces = {"application/json", "application/x-yaml"},
            consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<?> create(@RequestBody @Valid LocalDTO localDTO, UriComponentsBuilder components){
        LocalDTO localDTOCreate = localService.create(localDTO);

        URI uri = components
                .path("/local/{id}")
                .buildAndExpand(localDTOCreate.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> update(@PathVariable("id") Long id, @RequestBody @Valid LocalDTO localDTO){
        LocalDTO localDTOUpdated = localService.update(localDTO, id);

        return ResponseEntity.ok().body(localDTOUpdated);
    }


}
