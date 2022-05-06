package mercadolivro.exception

import mercadolivro.controller.response.ErrorResponse
import mercadolivro.controller.response.FieldErrorResponse
import mercadolivro.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestExcepition::class)
    fun handleBadRequestException(ex: BadRequestExcepition, request: WebRequest): ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            Errors.ML01.message,
            Errors.ML01.code,
            ex.bindingResult.fieldErrors.map{ FieldErrorResponse(it.defaultMessage ?: "Invalid", it.field) }
        )

        return ResponseEntity(erro, HttpStatus.UNPROCESSABLE_ENTITY)
    }

//    @ExceptionHandler(BadRequestExcepition::class)
//    fun handleBadRequestException(ex: BadRequestExcepition, request: WebRequest): ResponseEntity<ErrorResponse>{
//        val erro = ErrorResponse(
//            HttpStatus.BAD_REQUEST.value(),
//            ex.message,
//            ex.errorCode,
//            null
//        )
//
//        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
//    }
}