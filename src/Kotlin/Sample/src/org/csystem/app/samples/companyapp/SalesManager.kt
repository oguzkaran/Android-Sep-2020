package org.csystem.app.samples.companyapp

open class SalesManager(name: String, citizenId: String, address: String, salary: Double, var extra: Double)
    : Manager(name, citizenId, address, salary) {
    override fun calculateInsurancePayment() = super.calculateInsurancePayment() + extra
}