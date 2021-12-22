package com.murilonerdx.aondeta.resources;

import com.murilonerdx.aondeta.dto.ProfileDTO;
import com.murilonerdx.aondeta.services.impl.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = "Endpoint de Profile")
@RestController
@RequestMapping("/profile")

/*
    Mudança do nome da controller por conta de confusão de sobrescrica na anotação.
 */
public class ProfileResource {

    @Autowired
    private ProfileService profileService;

    @ApiOperation(value = "Create a new profile")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ProfileDTO create(@RequestBody @Valid ProfileDTO profileDTO) {
        ProfileDTO pDTO = profileService.create(profileDTO);

        pDTO.add(linkTo(methodOn(ProfileResource.class).findById(pDTO.getId())).withSelfRel());
        return pDTO;
    }

    @ApiOperation(value = "Find all profiles")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<ProfileDTO> getList() {
        List<ProfileDTO> profiles = profileService.listAll();
        profiles
                .forEach(p -> p.add(
                                linkTo(methodOn(ProfileResource.class).findById(p.getId())).withSelfRel()
                        )
                );
        return profiles;
    }

    @ApiOperation(value = "Find a specific profile by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ProfileDTO findById(@PathVariable("id") Long id) {
        ProfileDTO profileDTO = profileService.findById(id);

        profileDTO.add(linkTo(methodOn(ProfileResource.class).findById(id)).withSelfRel());
        return profileDTO;
    }

    @ApiOperation(value = "Delete a specific profile by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        profileService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ProfileDTO profileDTO){
        ProfileDTO profileDTOUpdated = profileService.update(profileDTO, id);


        return ResponseEntity.ok().body(profileDTOUpdated);
    }


}
