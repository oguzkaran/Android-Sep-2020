package org.csystem.app.samples.companyapp

open class Manager(name: String, citizenId: String, address: String, var salary: Double)
    : Employee(name, citizenId, address) {
    override fun calculateInsurancePayment() = salary * 1.5
    //...
}