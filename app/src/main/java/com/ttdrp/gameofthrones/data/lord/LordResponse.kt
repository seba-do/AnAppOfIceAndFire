package com.ttdrp.gameofthrones.data.lord

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LordResponse(
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
