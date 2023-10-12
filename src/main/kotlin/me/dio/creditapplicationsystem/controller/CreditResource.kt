package me.dio.creditapplicationsystem.controller

import me.dio.creditapplicationsystem.dto.CreditDTO
import me.dio.creditapplicationsystem.dto.CreditView
import me.dio.creditapplicationsystem.dto.CreditViewList
import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.service.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody creditDTO: CreditDTO): ResponseEntity<String> {
        val credit: Credit = this.creditService.save(creditDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
        val credits = this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewList(credit) }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(credits)
    }

    @GetMapping("/{creditCode}")
    fun findAllByCreditCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findAllByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}