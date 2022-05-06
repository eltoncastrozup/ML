package mercadolivro.repository

import mercadolivro.enums.BookStatus
import mercadolivro.model.BookModel
import mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.awt.print.Pageable

@Repository
interface BookRepository: JpaRepository<BookModel, Int> {

    abstract fun findByStatus(status: BookStatus, pageable: org.springframework.data.domain.Pageable): Page<BookModel>
    abstract fun findByCustomer(customer: CustomerModel): List<BookModel>

  //  fun findAll(pageable: org.springframework.data.domain.Pageable): Page<BookModel>

    //fun findByNameContainingIgnoreCase(name: String): List<CustomerModel>

}