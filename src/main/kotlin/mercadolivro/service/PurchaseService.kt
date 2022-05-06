package mercadolivro.service

import mercadolivro.events.PurchaseEvent
import mercadolivro.model.PurchaseModel
import mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService (

    private val purchaseRepository : PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
){

    fun create(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)

        println("Evento de compra")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
        println("Fim do processamento")
    }

    fun update(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)
    }

}
