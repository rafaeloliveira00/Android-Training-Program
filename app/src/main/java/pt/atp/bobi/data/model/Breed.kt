package pt.atp.bobi.data.model

import com.squareup.moshi.Json

data class Breed(
    @Json(name="bred_for")
    val bredFor: String,
    @Json(name="breed_group")
    val breedGroup: String,
    val height: Height,
    val id: Int,
    @Json(name="life_span")
    val lifeSpan: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val weight: Weight
)