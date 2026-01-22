package com.example.rickmortyapp.domain.models

import kotlinx.serialization.SerialName

data class Character(
    val results: List<Result>
) {
    data class Result(
        val id : Int,
        val image: String,
        val name: String,
    )
}