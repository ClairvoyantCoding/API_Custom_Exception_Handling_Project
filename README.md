# API Custom Exception Handling Article

Microservices have become a popular choice in designing solution architectures. However, when you work in an environment with many small applications that all depend on each other - it can get very messy. To mitigate this problem, we can create custom exceptions that we throw in place of real exceptions. We can still send the real exception to our application logger so that we have a record of exactly what error caused our problem. We can then provide our own error details in these custom exceptions and use them to populate a generic error response that will be customer-facing. This generic error response class can be shared across all of our applications so that all of our errors are consistent. Since we have consistent errors, creating Unit and Integration tests for all of our applications becomes a breeze since all of our errors are clean and easy to interpret.

## Creating Custom Exceptions

```java
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BadRequestException extends RuntimeException {
    private final String message;
    private final Throwable cause;
}

// Unauthorized, Forbidden, Not Found, Conflict, Media Type Not Supported, Internal Server Error...

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ServiceUnavailableException extends RuntimeException {
    private final String message;
    private final Throwable cause;
}
```

## Using Our Customer Exceptions In Our Service

```java
public ProjectDto getProjectById(UUID projectId) {
    try {
        return projectDao.findProjectById(projectId);
    } catch (IllegalArgumentException ex) {
        throw new NotFoundException(Constants.PROJECT_NOT_FOUND, null);
    }
}
```

## Logging Our Real Errors & Designing Our Customer Facing Errors

```java
@ExceptionHandler(value = { BadRequestException.class })
protected ResponseEntity<ErrorResponseModel> handleBadRequestException(Exception ex) {
    logger.error(Constants.REST_BAD_REQUEST, ex);

    ErrorResponseModel errorDetails = new ErrorResponseModel(
            400,
            dateFormat.format(new Date()),
            Constants.REST_BAD_REQUEST,
            ex.getMessage());

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
}
```

## Generic Error Response Class

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseModel {
    private int statusCode;
    private String date;
    private String restErrorMessage;
    private String detailedErrorMessage;
}
```

The above error class contains four fields. The int **statusCode** can be used to store either an HTTP Error Response code, or a custom error code from an internal error document your QA team maintains. The String **date** can be used to get the exact time the error occurred. I use a String type rather than a Date or Timestamp type because the string allows me to format how my date displays into "yyyy-MM-dd HH:mm:ss.SSSSSS". The String **restErrorMessage** is to display the type of Http Error Response that occurred such as: Bad Request, Unauthorized, and so on. Lastly, the String **detailedErrorMessage** displays the specific error message that caused the problem. This could include: "User already exists", "Required field not provided", "The application is experiencing an issue. Please try again later".

## Seeing The Result In Action




***

In this article I cover:

- Creating custom application exceptions to be thrown in place of any exception you would not want a user to see
- Utilizing logging to persist the ugly error messages you wouldn't want users to see
- Using Controller Advice on a Spring Controller to catch the errors thrown by incoming requests
- Effective utilization of a global constant file

In this project I'll use Java 11, but this code should run in Java 8 with very little modification. It could also be ported over to Kotlin fairly easily as well.
