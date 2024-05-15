package com.blueishincolour.apm

sealed class Routes(val route:String) {

    object AllScreenByIdScreen:Routes("AllScreenByIdScreen")

    object OneScreenByIdScreen:Routes("OneScreenByIdScreen")
}