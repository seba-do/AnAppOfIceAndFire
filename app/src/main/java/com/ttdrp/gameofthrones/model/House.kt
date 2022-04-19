package com.ttdrp.gameofthrones.model

import com.ttdrp.gameofthrones.database.HouseDatabase
import com.ttdrp.gameofthrones.database.LordDatabase

data class HouseResolved(
    val id: String,
    val url: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String>,
    val seats: List<String>,
    val currentLord: LordDatabase?,
    val heir: LordDatabase?,
    val overlord: HouseDatabase?,
    val founded: String,
    val founder: LordDatabase?,
    val diedOut: String,
    val ancestralWeapons: List<String>,
    val cadetBranches: List<HouseDatabase>?,
    val swornMembers: List<LordDatabase>?
)

fun HouseDatabase.toResolved(
    currentLord: LordDatabase?,
    overlord: HouseDatabase?,
    heir: LordDatabase?,
    cadetBranches: List<HouseDatabase>?,
    swornMembers: List<LordDatabase>?,
    founder: LordDatabase?
) = HouseResolved(
    id = id,
    url = url,
    name = name,
    region = region,
    coatOfArms = coatOfArms,
    words = words,
    titles = titles,
    seats = seats,
    currentLord = currentLord,
    heir = heir,
    overlord = overlord,
    founded = founded,
    diedOut = diedOut,
    ancestralWeapons = ancestralWeapons,
    cadetBranches = cadetBranches,
    swornMembers = swornMembers,
    founder = founder
)
