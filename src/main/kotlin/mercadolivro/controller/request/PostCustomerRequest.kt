package mercadolivro.controller.request

import mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PostCustomerRequest (

    @field: NotEmpty(message ="Nome não pode ser vazio!")
    var name: String,

    @field: Email(message = "Formato de e-mail inválido")
    @EmailAvailable
    var email: String
)