package com.example.avaliacaomobilegorjeta

class Calculos {
    fun calcularGorjeta(amount: Float, customTip: Float): Float {

        return (amount * customTip /100)
    }
    fun calcularTotalGorjeta(amount: Float, customTip: Float): Float{
        return ((amount * customTip /100) + amount)
    }
}