package com.example.musinsa.common

data class NotFoundException(
    override val errorCode: ErrorCode
) : RuntimeException(), CommonException
