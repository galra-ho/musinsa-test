package com.example.musinsa.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CommonResponse<T>(
    val data: T
) {
    companion object {
        fun <T> convert(data: T, status: HttpStatus = HttpStatus.OK): ResponseEntity<T> {
            return ResponseEntity.status(status)
                .body(data)
        }
    }
}