package com.example.musinsa.common

data class NotFoundException(
    override val errorCode: ErrorCode,
    override val message :String = errorCode.message
) : RuntimeException(), CommonException
