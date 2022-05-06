package mercadolivro.model

import mercadolivro.enums.CustomerStatus
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String? = null,

    @Column
    var email: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus
)