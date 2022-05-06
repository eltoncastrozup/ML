package mercadolivro.model

import mercadolivro.enums.BookStatus
import mercadolivro.enums.Errors
import mercadolivro.exception.BadRequestExcepition
import java.lang.Exception
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class BookModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String? = null,

    @Column
    var price: BigDecimal? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

){
    //afim de bloquear que status como cancelado || deletado sofram alteração, retira o status do construtor padrão e sobreescreve utilizando set.
    //livro recebe status e valida se for cancelado ou deletado lança uma excessão, senão, recebe o novo valor
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.DELETADO || field == BookStatus.CANCELADO)
                throw BadRequestExcepition(Errors.MLB02.message.format(field), Errors.MLB02.code)

            field = value
        }
    // Novo construtor

    constructor(id: Int? = null, name: String?, price: BigDecimal?, customer: CustomerModel? = null, status: BookStatus?): this(id, name, price, customer){
      this.status = status
    }

}