package tech.notzero.people.model

data class Result(
    var count:Int? = 0,
    var next:String? = null,
    var previous:Any? = null,
    var results:List<People> = emptyList()
)