package com.example.rickmortyapp.data.mappers

import com.example.rickmortyapp.data.model.CharacterDto
import com.example.rickmortyapp.domain.models.Character

fun CharacterDto.toChatacter(): Character = Character(
    results = this.results.map{it.toResult()}
)
fun CharacterDto.Result.toResult() : Character.Result = Character.Result(
    image = this.image,
    name = this.name,
    id = this.id
)