package mercadolivro.exception

class BadRequestExcepition(override val message: String, val errorCode: String): Exception() {
}