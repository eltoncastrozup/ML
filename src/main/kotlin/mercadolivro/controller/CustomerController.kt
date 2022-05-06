package mercadolivro.controller

import mercadolivro.controller.request.PostCustomerRequest
import mercadolivro.controller.request.PutCustomerRequest
import mercadolivro.controller.response.CustomerResponse
import mercadolivro.extension.toCustomerModel
import mercadolivro.extension.toResponse
//import mercadolivro.model.CustomerModel
import mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("customers")
class CustomerController (

    val customerService: CustomerService
        ){


    //metódo responsável por buscar os dados
    //buscando todos os customers
    //utilizado requestParam para realizar consulta passando nome ou parte do nome. O "name?" é para garantir que se for diferente de vazio, possa entrar na consulta
    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
    return customerService.getAll(name).map { it.toResponse() }

    }

    //metódo responsável por criar novos dados
    //Criar variavel de index na classe
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }
    //metódo para realizar busca de valores passando o id. ( deve retornar somente o valor referenciado).
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse{
        return customerService.findtById(id).toResponse()
    }

    //metódo para atualizar dados
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest){
        val customerSaved = customerService.findtById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    //método para deletar dados
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        customerService.delete(id)
    }
}