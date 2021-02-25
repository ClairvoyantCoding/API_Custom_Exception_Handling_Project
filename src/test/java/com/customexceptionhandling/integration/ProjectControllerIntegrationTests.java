package com.customexceptionhandling.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customexceptionhandling.constants.Constants;
import com.customexceptionhandling.error.ErrorResponseModel;
import com.customexceptionhandling.util.SpringCommandLineProfileResolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(resolver = SpringCommandLineProfileResolver.class)
public class ProjectControllerIntegrationTests {
    @Autowired
    TestRestTemplate testRestTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void successEndpoint_validInput_200Success() {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/success", HttpMethod.GET, null, String.class);

        assertNotNull(response.getBody());
        assertEquals(Constants.SUCCESS, response.getBody());
    }

    @Test
    void badRequestEndpoint_validInput_400BadRequest() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/badrequest", HttpMethod.GET, null, String.class);
        ErrorResponseModel errorResponse = this.objectMapper.readValue(response.getBody(), ErrorResponseModel.class);
        String expectedDetailedErrorMsg = Constants.PROJECT_MUST_HAVE_NAME + Constants.PROJECT_MUST_HAVE_DESCRIPTION + Constants.PROJECT_MUST_HAVE_START_DATE;

        assertNotNull(response);
        assertNotNull(errorResponse.getDate());
        assertEquals(Constants.REST_BAD_REQUEST, errorResponse.getRestErrorMessage());
        assertEquals(expectedDetailedErrorMsg, errorResponse.getDetailedErrorMessage());
    }

    @Test
    void unauthorizedEndpoint_validInput_401Unauthorized() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/unauthorized", HttpMethod.GET, null, String.class);
        ErrorResponseModel errorResponse = this.objectMapper.readValue(response.getBody(), ErrorResponseModel.class);

        assertNotNull(response);
        assertNotNull(errorResponse.getDate());
        assertEquals(Constants.REST_UNAUTHORIZED, errorResponse.getRestErrorMessage());
        assertEquals(Constants.UNAUTHORIZED_REQUEST, errorResponse.getDetailedErrorMessage());
    }

    @Test
    void forbiddenEndpoint_validInput_403Forbidden() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/forbidden", HttpMethod.GET, null, String.class);
        ErrorResponseModel errorResponse = this.objectMapper.readValue(response.getBody(), ErrorResponseModel.class);

        assertNotNull(response);
        assertNotNull(errorResponse.getDate());
        assertEquals(Constants.REST_FORBIDDEN, errorResponse.getRestErrorMessage());
        assertEquals(Constants.NOT_PERMITTED_TO_SEE_THIS, errorResponse.getDetailedErrorMessage());
    }

    @Test
    void notFoundEndpoint_validInput_404NotFound() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/notfound", HttpMethod.GET, null, String.class);
        ErrorResponseModel errorResponse = this.objectMapper.readValue(response.getBody(), ErrorResponseModel.class);

        assertNotNull(response);
        assertNotNull(errorResponse.getDate());
        assertEquals(Constants.REST_NOT_FOUND, errorResponse.getRestErrorMessage());
        assertEquals(Constants.PROJECT_NOT_FOUND, errorResponse.getDetailedErrorMessage());
    }

    @Test
    void conflictEndpoint_validInput_409Conflict() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/conflict", HttpMethod.GET, null, String.class);
        ErrorResponseModel errorResponse = this.objectMapper.readValue(response.getBody(), ErrorResponseModel.class);

        assertNotNull(response);
        assertNotNull(errorResponse.getDate());
        assertEquals(Constants.REST_CONFLICT, errorResponse.getRestErrorMessage());
        assertEquals(Constants.PROJECT_ALREADY_EXISTS, errorResponse.getDetailedErrorMessage());
    }

    @Test
    void internalServerErrorEndpoint_validInput_500InternalServerError() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/internalservererror", HttpMethod.GET, null, String.class);
        ErrorResponseModel errorResponse = this.objectMapper.readValue(response.getBody(), ErrorResponseModel.class);

        assertNotNull(response);
        assertNotNull(errorResponse.getDate());
        assertEquals(Constants.REST_INTERNAL_SERVER_ERROR, errorResponse.getRestErrorMessage());
        assertEquals(Constants.DONT_DIVIDE_BY_ZERO, errorResponse.getDetailedErrorMessage());
    }

    @Test
    void serviceUnavailableEndpoint_validInput_500ServiceUnavailable() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.exchange("/project/serviceunavailable", HttpMethod.GET, null, String.class);
        ErrorResponseModel errorResponse = this.objectMapper.readValue(response.getBody(), ErrorResponseModel.class);

        assertNotNull(response);
        assertNotNull(errorResponse.getDate());
        assertEquals(Constants.REST_SERVICE_UNAVAILABLE, errorResponse.getRestErrorMessage());
        assertEquals(Constants.FILE_DOES_NOT_EXIST, errorResponse.getDetailedErrorMessage());
    }
}
