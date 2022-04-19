package com.ttdrp.gameofthrones.model

import com.ttdrp.gameofthrones.database.HouseDatabase
import com.ttdrp.gameofthrones.database.LordDatabase

data class LordResolved(
    val id: String,
    val url: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String>,
    val aliases: List<String>,
    val father: LordDatabase?,
    val mother: LordDatabase?,
    val spouse: LordDatabase?,
    val allegiances: List<HouseDatabase>?
)

fun LordDatabase.toResolved(
    father: LordDatabase?,
    mother: LordDatabase?,
    spouse: LordDatabase?,
    allegiances: List<HouseDatabase>?
) = LordResolved(
    id = id,
    url = url,
    name = name,
    gender = gender,
    culture = culture,
    born = born,
    died = died,
    titles = titles,
    aliases = aliases,
    father = father,
    mother = mother,
    spouse = spouse,
    allegiances = allegiances
)