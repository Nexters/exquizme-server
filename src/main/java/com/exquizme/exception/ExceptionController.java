package com.exquizme.exception;

import com.exquizme.response.ServerResponse;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerResponse> handleException(Exception exception) throws Exception {
        log.error("[Exception] {}\n{}", exception.getMessage(), Throwables.getStackTraceAsString(exception));
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8).body(ServerResponse.error());
    }
}
