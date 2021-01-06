package org.csystem.samples.application.samplelistview

data class CityInfo(var name: String, var plate: Int, var phoneCode: Int) {
    override fun toString() = name
}