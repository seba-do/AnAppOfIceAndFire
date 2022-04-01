package com.ttdrp.gameofthrones.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lord(
    @PrimaryKey
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
