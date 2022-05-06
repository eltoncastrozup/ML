package mercadolivro.repository

import mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: CrudRepository<CustomerModel, Int> {

    fun findByNameContainingIgnoreCase(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean

}