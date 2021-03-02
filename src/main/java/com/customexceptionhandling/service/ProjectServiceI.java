package com.customexceptionhandling.service;

public interface ProjectServiceI {
    String succeed();
    String badRequest();
    String unauthorized();
    String forbidden();
    String notFound();
    String conflict();
    String internalServerError();
    String serviceUnavailable();
}
