package com.example.musinsa.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundException(exception: CommonException): ResponseEntity<String> {
        return CommonResponse.convert(
            exception.errorCode.message, HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun illegalArgumentException(exception: CommonException): ResponseEntity<String> {
        return CommonResponse.convert(
            exception.errorCode.message, HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(value = [Exception::class])
    fun exception(exception: Exception): ResponseEntity<String?> {
        return CommonResponse.convert(
            exception.message ?: HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}