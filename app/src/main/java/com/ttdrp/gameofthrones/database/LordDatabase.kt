package com.ttdrp.gameofthrones.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ttdrp.gameofthrones.data.lord.LordResponse

@Entity(tableName = "Lords")
data class LordDatabase(
    @PrimaryKey
    val id: String,
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

fun LordResponse.toDatabaseModel() = LordDatabase(
    id = url.split("/").last(),
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