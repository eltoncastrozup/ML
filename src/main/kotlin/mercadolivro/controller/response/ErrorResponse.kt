package mercadolivro.controller.response

data class ErrorResponse (

    var httoCode: Int,
    var message: String,
    var internalCode: String,
    var errors: List<FieldErrorResponse>?

)
