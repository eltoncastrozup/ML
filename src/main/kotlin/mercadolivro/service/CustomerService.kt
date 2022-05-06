package mercadolivro.service

import mercadolivro.controller.request.PostCustomerRequest
import mercadolivro.controller.request.PutCustomerRequest
import mercadolivro.enums.CustomerStatus
import mercadolivro.enums.Errors
import mercadolivro.exception.NotFoundException
import mercadolivro.model.CustomerModel
import mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class CustomerService (
    val customerRepository: CustomerRepository,
    val bookService: BookService
        ){

    //val customers = mutableListOf<CustomerModel>()
    //var index = 1

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContainingIgnoreCase(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {

        //var id = index.toString()

        //index = index + 1 forma onde novo registro não recebe um id já utilizado


//         val id = if(customers.isEmpty()) {
//            1
//
//        } else {
//            customers.last().id!!.toInt() + 1
//        }
//
//        customer.id = id

//        customers.add(customer)
        customerRepository.save(customer)
    }

    fun findtById(id: Int): CustomerModel{
        //return customers.filter{ it.id == id }.first()
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.MLC01.message.format(id), Errors.MLC01.code) }
    }


    fun update(customer: CustomerModel){
        if (!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
//        customers.filter {it.id == customer.id }.first().let{
//            it.name = customer.name
//            it.email = customer.email
//        }
    }


    fun delete(id: Int){
      //  customers.removeIf {it.id == id }

//        if (!customerRepository.existsById(id)){
//            throw Exception()
//        }

        val customer = findtById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
       return !customerRepository.existsByEmail(email)
    }


}