package com.murilonerdx.aondeta.resources;

import com.murilonerdx.aondeta.dto.AuthenticationDTO;
import com.murilonerdx.aondeta.dto.ProfileDTO;
import com.murilonerdx.aondeta.dto.RequestProfileLogin;
import com.murilonerdx.aondeta.entities.User;
import com.murilonerdx.aondeta.services.impl.ProfileService;
import com.murilonerdx.aondeta.services.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

public class ProfileResource {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create a new profile")
    @PostMapping(produces = {"application/json", "application/x-yaml"},
            consumes = {"application/json", "application/x-yaml"})
    public ProfileDTO create(@RequestBody @Valid RequestProfileLogin login, UriComponentsBuilder components) {
        userService.create(login.getAuthenticationDTO());
        ProfileDTO pDTO = profileService.create(login.getProfileDTO());

        return pDTO;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @ApiOperation(value = "Find all profiles")
    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public List<ProfileDTO> getList(UriComponentsBuilder components) {
        List<ProfileDTO> profiles = profileService.listAll();

        return profiles;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @ApiOperation(value = "Find a specific profile by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<?> findById(@PathVariable("id") Long id, UriComponentsBuilder components) {
        ProfileDTO profileDTO = profileService.findById(id);
        URI uri = components
                .path("/local/{id}")
                .buildAndExpand(profileDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @ApiOperation(value = "Delete a specific profile by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        profileService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ProfileDTO profileDTO){
        ProfileDTO profileDTOUpdated = profileService.update(profileDTO, id);

        return ResponseEntity.ok().body(profileDTOUpdated);
    }


}
