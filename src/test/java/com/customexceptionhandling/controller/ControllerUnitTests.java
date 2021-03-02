package com.customexceptionhandling.controller;

import com.customexceptionhandling.constant.Constants;
import com.customexceptionhandling.dao.ProjectDAO;
import com.customexceptionhandling.error.restCustomExceptions.BadRequestException;
import com.customexceptionhandling.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerUnitTests {
    private final ProjectService projectService = mock(ProjectService.class);

    private final ProjectController projectController = new ProjectController(projectService);

    @Test
    void success_validInput_200Okay() {
        when(projectService.succeed())
                .thenReturn(Constants.SUCCESS);

        String response = projectService.succeed();

        verify(projectService).succeed();

        assertNotNull(response);
        assertEquals(Constants.SUCCESS, response);
    }

    @Test
    void badRequest_invalidInput_400BadRequest() {
        BadRequestException ex = new BadRequestException(Constants.REST_BAD_REQUEST, null);

        when(projectService.badRequest()).thenThrow(ex);

        BadRequestException response = assertThrows(BadRequestException.class, projectController::badRequest);

        verify(projectService).badRequest();

        assertNotNull(response);
        assertEquals(Constants.REST_BAD_REQUEST, response.getMessage());
    }
}
