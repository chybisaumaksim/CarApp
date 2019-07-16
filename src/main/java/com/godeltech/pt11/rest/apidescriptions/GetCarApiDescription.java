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
                @ApiResponse(code = 400, message = "Bad Request"),
                @ApiResponse(code = 404, message = "Car with current ID not found"),
                @ApiResponse(code = 500, message = "Server Error")})
@ApiOperation(value = "Get car by ID")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetCarApiDescription {
}
