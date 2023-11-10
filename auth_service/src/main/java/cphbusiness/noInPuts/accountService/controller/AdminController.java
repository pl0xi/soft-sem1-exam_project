package cphbusiness.noInPuts.accountService.controller;

import cphbusiness.noInPuts.accountService.dto.AdminDTO;
import cphbusiness.noInPuts.accountService.exception.UserDoesNotExistException;
import cphbusiness.noInPuts.accountService.exception.WrongCredentialsException;
import cphbusiness.noInPuts.accountService.service.AdminService;
import cphbusiness.noInPuts.accountService.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
public class AdminController {

    private final AdminService adminService;
    private final JwtService jwtService;

    @Autowired
    public AdminController(AdminService adminService, JwtService jwtService) {
        this.adminService = adminService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/admin/login", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody AdminDTO postAdminDTO) throws UserDoesNotExistException {
        AdminDTO adminUser;
        try {
            adminUser = adminService.login(postAdminDTO);
        } catch (WrongCredentialsException | UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String JwtToken = jwtService.tokenGenerator(adminUser.getId(), adminUser.getUsername(), "admin");

        Map<String, Object> response = new HashMap<>();
        response.put("user", adminUser);
        response.put("jwt", JwtToken);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
