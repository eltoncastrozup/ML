package mercadolivro.enums

enum class Errors (val code: String, val message: String) {

    //Legenda: ML = Mercado Livro, erros rgenéricos
    ML01("ML-01", "Invalid Request"),


    //Legenda: MLB = Mercado Livro Book, erros relacioados ao book
    MLB01("MLB-01", "Book de ID:[%s], not exist!"),
    MLB02("MLB-02", "Cannot update book with status [%s]"),

    //Legenda: MLC = Mercado Livro Customer, erros relacioados ao customer
    MLC01("MLC-01", "Customer de ID:[%s], not exist!")
}