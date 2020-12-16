package org.csystem.app.samples.companyapp

abstract class Employee(var name: String, var citizenId: String, var address: String) {
    abstract fun calculateInsurancePayment() : Double
}