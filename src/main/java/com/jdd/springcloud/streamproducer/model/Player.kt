package com.jdd.springcloud.streamproducer.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Player constructor(@JsonProperty("player_id") val playerId: String = "") {

    @JsonProperty("player_first_name")
    val playerFirstName: String = ""

    @JsonProperty("player_last_name")
    val playerLastName: String = ""

    @JsonProperty("player_number")
    val playerNumber: String = ""
}