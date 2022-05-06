package mercadolivro.extension

import mercadolivro.controller.request.PostBookRequest
import mercadolivro.controller.request.PostCustomerRequest
import mercadolivro.controller.request.PutBookRequest
import mercadolivro.controller.request.PutCustomerRequest
import mercadolivro.controller.response.BookResponse
import mercadolivro.controller.response.CustomerResponse
import mercadolivro.enums.BookStatus
import mercadolivro.enums.CustomerStatus
import mercadolivro.model.BookModel
import mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel{
    return CustomerModel(id = previousValue.id
        , name = this.name, email = this.email, status = previousValue.status)  //<<<<<
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(bookOld: BookModel): BookModel {
    return BookModel(
        id = bookOld.id,
        name = this.name ?: bookOld.name, //name é nulo? se for, vai receber o nome do livro antigo
        price = this.price ?: bookOld.price,
        status = bookOld.status,
        customer = bookOld.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )

}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )

}



























