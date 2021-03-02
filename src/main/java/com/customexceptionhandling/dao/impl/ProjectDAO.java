package com.customexceptionhandling.dao.impl;

import com.customexceptionhandling.constant.Constants;
import com.customexceptionhandling.dao.ProjectDaoI;
import com.customexceptionhandling.error.restCustomExceptions.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class ProjectDAO implements ProjectDaoI {
    public String getProject(Boolean goodData) {
        if (!goodData) {
            throw new BadRequestException(Constants.REST_BAD_REQUEST, null);
        }

        return Constants.SUCCESS;
    }
}
