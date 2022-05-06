package mercadolivro.events.listener


import mercadolivro.events.PurchaseEvent
import mercadolivro.service.BookService
import mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookListener (

    private val bookService: BookService
){
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        println("Atualizando status dos livros")
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}