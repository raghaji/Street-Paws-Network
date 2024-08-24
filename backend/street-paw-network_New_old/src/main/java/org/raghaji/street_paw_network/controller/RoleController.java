package org.raghaji.street_paw_network.controller;

import org.raghaji.street_paw_network.model.Role;
import org.raghaji.street_paw_network.services.Impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @PostMapping("createnewRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return new ResponseEntity<Role>(roleServiceImpl.createNewRole(role), HttpStatus.OK);
    }
}
