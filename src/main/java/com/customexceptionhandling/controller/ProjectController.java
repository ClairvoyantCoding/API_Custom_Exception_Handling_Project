package com.customexceptionhandling.controller;

import com.customexceptionhandling.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/success")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> success() {
        String success = projectService.succeed();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping(value = "/badrequest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> badRequest() {
        String success = projectService.badRequest();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping(value = "/unauthorized")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> unauthorized() {
        String success = projectService.unauthorized();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping(value = "/forbidden")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> forbidden() {
        String success = projectService.forbidden();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping(value = "/notfound")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> notFound() {
        String success = projectService.notFound();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping(value = "/conflict")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> conflict() {
        String success = projectService.conflict();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping(value = "/internalservererror")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> internalServerError() {
        String success = projectService.internalServerError();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping(value = "/serviceunavailable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> serviceUnavailable() {
        String success = projectService.serviceUnavailable();

        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
