package com.example.rickmortyapp.domain.models

import com.example.rickmortyapp.data.model.LocationDto
import com.example.rickmortyapp.data.model.OriginDto
import kotlinx.serialization.SerialName

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDto?,
    val name: String,
    val origin: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
