package mercadolivro.controller

import mercadolivro.controller.request.PostBookRequest
import mercadolivro.controller.request.PutBookRequest
import mercadolivro.controller.response.BookResponse
import mercadolivro.extension.toBookModel
import mercadolivro.extension.toResponse
//import mercadolivro.model.BookModel
import mercadolivro.service.BookService
import mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("books")
class BookController (

    val bookService: BookService,
    val customerService: CustomerService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest){
        val customer = customerService.findtById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable : Pageable): Page<BookResponse> {
       return bookService.findAll(pageable).map { it.toResponse() };
    }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable : Pageable): Page<BookResponse> {
        return bookService.findActive(pageable).map { it.toResponse() }
    }

//    Outra forma de se fazer, pois nesse caso, é uma função simples, única linha e já retorna o proprio valor
//    @GetMapping("/active")
//    fun findActives(): List<BookModel> =
//        return bookService.findActive()
//

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse{
        return bookService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        return bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest){
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))

    }
}