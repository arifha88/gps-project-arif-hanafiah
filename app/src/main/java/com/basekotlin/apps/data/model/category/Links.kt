package com.basekotlin.apps.data.model.category

data class Links(
	val wpPostType: List<WpPostTypeItem?>? = null,
	val curies: List<CuriesItem?>? = null,
	val about: List<AboutItem?>? = null,
	val self: List<SelfItem?>? = null,
	val collection: List<CollectionItem?>? = null
)
