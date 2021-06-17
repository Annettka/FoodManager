package by.it.academy.foodmanager.app.domain.converters

class MillisTimeConverter {

    fun millisToHMm(millis: Long): String {
        val h = ((millis / (1000 * 60 * 60)) % 24)
        val min = ((millis / (1000 * 60)) % 60)
        return String.format("%02d:%02d", h, min)
    }
}