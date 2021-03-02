package com.customexceptionhandling.service;

import com.customexceptionhandling.dao.ProjectDAO;
import com.customexceptionhandling.error.restCustomExceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

import static com.customexceptionhandling.constant.Constants.*;

@Service
public class ProjectService {
    @Autowired
    private final ProjectDAO projectDAO;

    public ProjectService(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public String succeed() {
        return this.projectDAO.getProject(true);
    }

    public String badRequest() {
        String badRequestMsg = "";

        if(true) {
            badRequestMsg += PROJECT_MUST_HAVE_NAME;
        }

        if(true) {
            badRequestMsg += PROJECT_MUST_HAVE_DESCRIPTION;
        }

        if(true) {
            badRequestMsg += PROJECT_MUST_HAVE_START_DATE;
        }

        if(true) {
            throw new BadRequestException(badRequestMsg, null);
        }

        return SUCCESS;
    }

    public String unauthorized() {
        if(true) {
            throw new UnauthorizedException(UNAUTHORIZED_REQUEST, null);
        }

        return SUCCESS;
    }

    public String forbidden() {
        if(true) {
            throw new ForbiddenException(NOT_PERMITTED_TO_SEE_THIS, null);
        }

        return SUCCESS;
    }

    public String notFound() {
        if(true) {
            throw new NotFoundException(PROJECT_NOT_FOUND, null);
        }

        return SUCCESS;
    }

    public String conflict() {
        if(true) {
            throw new ConflictException(PROJECT_ALREADY_EXISTS, null);
        }

        return SUCCESS;
    }

    public String internalServerError() {
        int numerator = 1;
        int denominator = 0;
        int success;

        try {
            success = numerator / denominator;
        } catch (ArithmeticException ex) {
            throw new InternalServerErrorException(DONT_DIVIDE_BY_ZERO, ex);
        }

        return String.valueOf(success);
    }

    public String serviceUnavailable() {
        try
        {
            FileInputStream in = new FileInputStream("input.txt");
        } catch (IOException ex)
        {
            throw new ServiceUnavailableException(FILE_DOES_NOT_EXIST, ex);
        }

        return SUCCESS;
    }
}
