package me.dio.creditapplicationsystem.dto.request

import jakarta.validation.constraints.NotEmpty
import me.dio.creditapplicationsystem.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDTO(
    @field:NotEmpty
    val firstName: String,
    @field:NotEmpty
    val lastName: String,
    @field:NotEmpty
    val income: BigDecimal,
    @field:NotEmpty
    val zipCode: String,
    @field:NotEmpty
    val street: String
) {
    fun toEntity(customer: Customer) : Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return  customer
    }
}
