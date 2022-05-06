package mercadolivro.controller.mapper

import mercadolivro.controller.request.PostPurchaseRequest
import mercadolivro.model.PurchaseModel
import mercadolivro.service.BookService
import mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper (
    private val bookService: BookService,
    private val customerService: CustomerService
){

    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findtById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return PurchaseModel(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price !!}
        )
    }
}