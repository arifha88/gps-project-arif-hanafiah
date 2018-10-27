package com.basekotlin.apps.data.model.movie

import io.realm.RealmObject

data class ResponseMovie (
        val page: Int? = null,
        val total_pages: Int? = null,
        val results: List<ResultsItem>,
        val total_results: Int? = null
)
