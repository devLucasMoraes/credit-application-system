package me.dio.creditapplicationsystem.dto.request

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

class CreditDTO(
    @field:NotNull
    val creditValue: BigDecimal,
    @field:Future
    val dayFirstOfInstallment: LocalDate,
    @field:Min(value = 1) @field:Max(value = 48)
    val numberOfInstallments: Int,
    @field:NotNull
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
