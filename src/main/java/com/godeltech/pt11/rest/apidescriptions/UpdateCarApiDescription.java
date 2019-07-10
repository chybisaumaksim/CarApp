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
                @ApiResponse(code = 500, message = "Server Error")})
@ApiOperation(value = "Update car")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateCarApiDescription {
}
