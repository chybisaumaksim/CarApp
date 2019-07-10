package com.godeltech.pt11.rest.apidescriptions;

import io.swagger.annotations.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiImplicitParams(
        {@ApiImplicitParam(name = "request",
                value = "Request gets the car from database",
                required = true)})
@ApiResponses(
        value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Car with current colour not found"),
                @ApiResponse(code = 500, message = "Server Error")})
@ApiOperation(value = "Find all the cars by the colour")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FindCarByColourApiDescription {
}
