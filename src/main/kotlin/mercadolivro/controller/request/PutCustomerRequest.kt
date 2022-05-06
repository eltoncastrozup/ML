package mercadolivro.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PutCustomerRequest (

    @field:NotEmpty(message ="Nome não pode ser vazio!")
    var name: String,

    @field:Email(message = "Formato de e-mail inválido")
    var email: String
)