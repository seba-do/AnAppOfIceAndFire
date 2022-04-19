package com.ttdrp.gameofthrones.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ttdrp.gameofthrones.data.houses.HouseResponse

@Entity(tableName = "Houses")
data class HouseDatabase(
    @PrimaryKey
    val id: String,
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

fun HouseResponse.toDatabaseModel() = HouseDatabase(
    id = url.split("/").last(),
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

fun List<HouseResponse>.toDatabaseModels() = map { it.toDatabaseModel() }