package com.eduardo.alticci.advices;

import com.eduardo.alticci.errors.BusinessException;
import com.eduardo.alticci.errors.ErrorModel;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorModel.class))
    )
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorModel> handleBusinessException(BusinessException exception) {
        var errorModel = new ErrorModel(exception.getMessage());
        return ResponseEntity.badRequest().body(errorModel);
    }
}
