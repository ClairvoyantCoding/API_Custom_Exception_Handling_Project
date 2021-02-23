package com.customexceptionhandling.error;

import com.customexceptionhandling.constants.Constants;
import com.customexceptionhandling.error.restCustomExceptions.*;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger;

    public ControllerAdviceExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        logger.error(Constants.REST_BAD_REQUEST, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_BAD_REQUEST,
                Constants.INVALID_JSON);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<ErrorResponseModel> handleBadRequestException(Exception ex) {
        logger.error(Constants.REST_BAD_REQUEST, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_BAD_REQUEST,
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { UnauthorizedException.class })
    protected ResponseEntity<ErrorResponseModel> handleUnauthorizedException(Exception ex) {
        logger.error(Constants.REST_UNAUTHORIZED, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_UNAUTHORIZED,
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { ForbiddenException.class })
    protected ResponseEntity<ErrorResponseModel> handleForbiddenException(Exception ex) {
        logger.error(Constants.REST_FORBIDDEN, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_FORBIDDEN,
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    protected ResponseEntity<ErrorResponseModel> handleNotFoundException(Exception ex) {
        logger.error(Constants.REST_NOT_FOUND, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_NOT_FOUND,
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ConflictException.class })
    protected ResponseEntity<ErrorResponseModel> handleConflictException(Exception ex) {
        logger.error(Constants.REST_CONFLICT, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_CONFLICT,
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    // IMPORTANT NOTE: This also covers invalid UUIDs sent as path parameters which
    //  causes an internal server error that I translate to a Bad Request
    @ExceptionHandler(value = { InternalServerErrorException.class, RuntimeException.class })
    protected ResponseEntity<ErrorResponseModel> handleInternalServerError(Exception ex) {
        logger.info(Constants.REST_INTERNAL_SERVER_ERROR, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_INTERNAL_SERVER_ERROR,
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { ServiceUnavailableException.class, Exception.class })
    protected ResponseEntity<ErrorResponseModel> handleServiceUnavailableError(Exception ex) {
        logger.info(Constants.REST_SERVICE_UNAVAILABLE, ex);

        ErrorResponseModel errorDetails = new ErrorResponseModel(
                new Date(),
                Constants.REST_SERVICE_UNAVAILABLE,
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
