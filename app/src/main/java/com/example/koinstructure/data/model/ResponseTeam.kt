package com.example.koinstructure.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTeam(
    val data: MutableList<Team>
)

@Serializable
@Entity(tableName = "team")
data class Team(
    var code: String? = "",
    var country_id: Int ? = 0,
    @PrimaryKey
    var id: Int ?= 0,
    var image_path: String? = "",
    var name: String? = "",
    var national_team: Boolean? = false,
    var resource: String? = "",
    var updated_at: String? = ""
)