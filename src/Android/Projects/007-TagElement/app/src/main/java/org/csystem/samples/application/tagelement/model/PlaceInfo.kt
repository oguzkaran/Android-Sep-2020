package org.csystem.samples.application.tagelement.model

data class PlaceInfo(val name: String, val latitude: Double, val longitude: Double) {
    override fun toString(): String = name
}
