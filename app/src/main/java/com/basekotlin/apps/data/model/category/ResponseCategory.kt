package com.basekotlin.apps.data.model.category

data class ResponseCategory(
	val parent: Int? = null,
	val links: Links? = null,
	val meta: List<Any?>? = null,
	val count: Int? = null,
	val link: String? = null,
	val name: String? = null,
	val description: String? = null,
	val id: Int? = null,
	val taxonomy: String? = null,
	val slug: String? = null
)
