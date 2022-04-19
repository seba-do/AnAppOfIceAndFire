package com.ttdrp.gameofthrones.data.lord

import com.ttdrp.gameofthrones.database.LordDatabase

data class LordResponse(
    val url: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String>,
    val aliases: List<String>,
    val father: String,
    val mother: String,
    val spouse: String,
    val allegiances: List<String>
)

fun LordDatabase.toResponseModel() = LordResponse(
    url,
    name,
    gender,
    culture,
    born,
    died,
    titles,
    aliases,
    father,
    mother,
    spouse,
    allegiances
)