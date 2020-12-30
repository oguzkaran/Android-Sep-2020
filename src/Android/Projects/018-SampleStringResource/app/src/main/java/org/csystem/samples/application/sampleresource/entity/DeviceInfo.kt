package org.csystem.samples.application.sampleresource.entity

import java.io.Serializable

data class DeviceInfo(var name: String, var number: Long, var isOpen: Boolean) : Serializable