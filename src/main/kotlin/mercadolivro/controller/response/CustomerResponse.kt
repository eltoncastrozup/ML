package mercadolivro.controller.response

import mercadolivro.enums.CustomerStatus

data class CustomerResponse (


    var id: Int? = null,

    var name: String? = null,

    var email: String? = null,

    var status: CustomerStatus

 )
