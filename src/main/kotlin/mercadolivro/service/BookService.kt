package mercadolivro.service

import mercadolivro.enums.BookStatus
import mercadolivro.enums.Errors
import mercadolivro.exception.NotFoundException
import mercadolivro.model.BookModel
import mercadolivro.model.CustomerModel
import mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService (
    val bookRepository: BookRepository
        ){


    fun create(book: BookModel){
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
    return bookRepository.findAll(pageable)
    }

    fun findActive(pageable: Pageable): Page<BookModel>{
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{NotFoundException(Errors.MLB01.message.format(id), Errors.MLB01.code)}

    }

    fun delete(id: Int) {
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) { //para cada registro na lista books, criamos a variavel book e iteramos por ela.
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

    fun findAllByIds(bookIds: Set<Int>): List<BookModel> {
        return bookRepository.findAllById(bookIds).toList()
    }

    fun purchase(books: MutableList<BookModel>) {
        books.map {
            it.status = BookStatus.VENDIDO
        }
        bookRepository.saveAll(books)
    }


}