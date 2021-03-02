package com.customexceptionhandling.constant;

import com.customexceptionhandling.model.ProjectDto;

import java.util.Date;

public final class Constants {
    // Project Statuses
    public static final String SUCCESS = "Success";

    // Rest Error Messages
    public static final String REST_BAD_REQUEST = "400: Bad Request";
    public static final String REST_UNAUTHORIZED = "401: Unauthorized";
    public static final String REST_FORBIDDEN = "403: Forbidden";
    public static final String REST_NOT_FOUND = "404: Not Found";
    public static final String REST_CONFLICT = "409: Conflict";
    public static final String REST_MEDIA_TYPE_NOT_SUPPORTED = "415: Media Type Not Supported";
    public static final String REST_INTERNAL_SERVER_ERROR = "500: Internal Server Error";
    public static final String REST_SERVICE_UNAVAILABLE = "503: Service Unavailable";

    // Detailed Error Messages
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request.";
    public static final String INVALID_JSON = "Invalid JSON sent in request.";
    public static final String NOT_PERMITTED_TO_SEE_THIS = "You're not permitted to see the requested resource.";
    public static final String INVALID_PROJECT_ID_SENT_IN_BODY = "Invalid projectId sent in request body.";
    public static final String INVALID_ID_SENT_IN_PATH = "Invalid id sent in request path.";
    public static final String PROJECT_NOT_FOUND = "The project was not found.";
    public static final String PROJECT_ALREADY_EXISTS = "The project already exists.";
    public static final String DONT_DIVIDE_BY_ZERO = "Don't divide by zero.";
    public static final String FILE_DOES_NOT_EXIST = "File does not exist.";

    // Detailed bad request error messages
    public static final String PROJECT_MUST_HAVE_NAME = "The project must have a name. ";
    public static final String PROJECT_MUST_HAVE_DESCRIPTION = "The project must have a description. ";
    public static final String PROJECT_MUST_HAVE_START_DATE = "The project must have a start date. ";

    // Log Messages
    public static String saveOrUpdateProjectMessage(ProjectDto project) {
        return "\nTime: " + new Date().toString() + ", \nSaved/Updated Project: " + project.toString();
    }
}
