package mercadolivro.controller.response

import mercadolivro.enums.BookStatus
import mercadolivro.model.CustomerModel
import java.math.BigDecimal


data class BookResponse (


    var id: Int? = null,

    var name: String? = null,

    var price: BigDecimal? = null,

    var customer: CustomerModel? = null,

    var status: BookStatus? = null

        )


