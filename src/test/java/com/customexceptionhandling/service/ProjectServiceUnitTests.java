package com.customexceptionhandling.service;

import com.customexceptionhandling.constant.Constants;
import com.customexceptionhandling.dao.impl.ProjectDAO;
import com.customexceptionhandling.error.restCustomExceptions.BadRequestException;
import com.customexceptionhandling.service.impl.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceUnitTests {
    private final ProjectDAO projectDAO = mock(ProjectDAO.class);

    private final ProjectService projectService = new ProjectService(projectDAO);

    @Test
    void success_validInput_200Okay() {
        when(projectDAO.getProject(true))
                .thenReturn(Constants.SUCCESS);

        String response = projectService.succeed();

        verify(projectDAO).getProject(true);

        assertNotNull(response);
        assertEquals(Constants.SUCCESS, response);
    }

    @Test
    void success_invalidInput_400BadRequest() {
        BadRequestException ex = new BadRequestException(Constants.REST_BAD_REQUEST, null);

        when(projectDAO.getProject(true)).thenThrow(ex);

        BadRequestException response = assertThrows(BadRequestException.class, projectService::succeed);

        verify(projectDAO).getProject(true);

        assertNotNull(response);
        assertEquals(Constants.REST_BAD_REQUEST, response.getMessage());
    }
}
