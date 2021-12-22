package com.murilonerdx.aondeta.resources;

import com.murilonerdx.aondeta.dto.ProfileDTO;
import com.murilonerdx.aondeta.services.impl.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @PostMapping(produces = {"application/json", "application/x-yaml"},
            consumes = {"application/json", "application/x-yaml"})
    public ProfileDTO create(@RequestBody @Valid ProfileDTO profileDTO, UriComponentsBuilder components) {
        ProfileDTO pDTO = profileService.create(profileDTO);

        pDTO.add(linkTo(methodOn(ProfileResource.class).findById(pDTO.getId(), components)).withSelfRel());
        return pDTO;
    }

    @ApiOperation(value = "Find all profiles")
    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public List<ProfileDTO> getList(UriComponentsBuilder components) {
        List<ProfileDTO> profiles = profileService.listAll();
        profiles
                .forEach(p -> p.add(
                                linkTo(methodOn(ProfileResource.class).findById(p.getId(), components)).withSelfRel()
                        )
                );
        return profiles;
    }

    @ApiOperation(value = "Find a specific profile by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<?> findById(@PathVariable("id") Long id, UriComponentsBuilder components) {
        ProfileDTO profileDTO = profileService.findById(id);

//        profileDTO.add(linkTo(methodOn(ProfileResource.class).findById(id)).withSelfRel());
//        return profileDTO;

        URI uri = components
                .path("/local/{id}")
                .buildAndExpand(profileDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
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
