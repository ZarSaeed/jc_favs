package edu.uchicago.gerber.favs.data.models

data class Paginate(
    var query: String = "",
    var location: String = "",
    var maxResult: Int = 10,
    var startIndex: Int = 1,
)
