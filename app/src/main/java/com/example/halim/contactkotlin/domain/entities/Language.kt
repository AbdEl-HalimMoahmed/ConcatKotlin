package com.example.halim.contactkotlin.domain.entities

import java.util.*
import javax.inject.Inject


sealed class Language(val local: Locale)

class English @Inject constructor() : Language(Locale.ENGLISH)

class Arabic @Inject constructor() : Language(Locale("ar"))