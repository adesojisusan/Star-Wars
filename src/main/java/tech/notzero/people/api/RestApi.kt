package tech.notzero.people.api

import retrofit2.Call
import retrofit2.http.GET
import tech.notzero.people.model.Result

interface RestApi {
    @GET("people")
    fun getPeople():Call<Result>
}