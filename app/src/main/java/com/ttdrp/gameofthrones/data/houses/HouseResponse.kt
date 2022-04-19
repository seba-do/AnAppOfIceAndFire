package com.ttdrp.gameofthrones.data.houses

import com.ttdrp.gameofthrones.database.HouseDatabase

data class HouseResponse(
    val url: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String>,
    val seats: List<String>,
    val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    val diedOut: String,
    val ancestralWeapons: List<String>,
    val cadetBranches: List<String>,
    val swornMembers: List<String>
)

fun HouseDatabase.toResponseModel() = HouseResponse(
    url,
    name,
    region,
    coatOfArms,
    words,
    titles,
    seats,
    currentLord,
    heir,
    overlord,
    founded,
    founder,
    diedOut,
    ancestralWeapons,
    cadetBranches,
    swornMembers
)