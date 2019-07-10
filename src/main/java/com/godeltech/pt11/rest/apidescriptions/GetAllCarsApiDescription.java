package com.godeltech.pt11.rest.apidescriptions;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponses(
        value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 400, message = "Other error"),
                @ApiResponse(code = 500, message = "Server error")})
@ApiOperation(value = "Get all cars")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetAllCarsApiDescription {
}
