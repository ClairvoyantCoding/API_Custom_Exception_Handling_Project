package com.customexceptionhandling.service;

import com.customexceptionhandling.ProjectTrackerApplication;
import com.customexceptionhandling.constants.Constants;
import com.customexceptionhandling.error.restCustomExceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceUnitTests {
    private final Logger logger = mock(Logger.class);

    private final ProjectService projectService = new ProjectService();

    @Test
    void success_validInput_200Okay() {
        String response = projectService.succeed();

        assertNotNull(response);
        assertEquals(Constants.SUCCESS, response);
    }

    @Test
    void badRequest_validInput_400BadRequest() {
        String badRequestMsg = "";
        BadRequestException ex = new BadRequestException(badRequestMsg, null);
        String response = projectService.badRequest();

        //when(logger.error(Constants.REST_CONFLICT, ex)).thenThrow(ex);

        assertNotNull(response);
        assertEquals(Constants.SUCCESS, response);
    }
}
