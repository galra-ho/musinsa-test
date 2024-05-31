package com.example.musinsa.presenter.request

import com.fasterxml.jackson.annotation.JsonProperty

data class AddBrandRequest(
    @JsonProperty("name")
    val name: String
)