package com.sarmady.contactkotlin.ui.animator


sealed class AnimationType
class TranslateX : AnimationType()
class TranslateY : AnimationType()
class ScaleX : AnimationType()
class ScaleY : AnimationType()
class Alpha : AnimationType()