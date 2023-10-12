package me.dio.creditapplicationsystem.service.impl

import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.repository.CreditRepository
import me.dio.creditapplicationsystem.service.CreditService
import me.dio.creditapplicationsystem.service.CustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class CreditServiceImpl(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : CreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.creditRepository.findAllByCustomerId(customerId)
    }

    override fun findAllByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode) ?:
            throw RuntimeException("CreditCode $creditCode not found")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
    }
}