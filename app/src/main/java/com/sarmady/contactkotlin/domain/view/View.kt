package com.sarmady.contactkotlin.domain.view


interface View {

    fun showRetryDialog(onRetry: () -> Unit, onClose: () -> Unit) {}
    fun closeView() {}
}