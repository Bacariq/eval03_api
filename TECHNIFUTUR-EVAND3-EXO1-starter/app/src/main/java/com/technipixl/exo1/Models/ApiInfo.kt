package com.technipixl.exo1.Models

import com.technipixl.exo1.Service.HashGenerator
import java.util.Date

class ApiInfo {

}

val timeStamp = Date().time
val privateKey = "cb822137b38f6fc4d416919de635173acb2d3427"
val publicKey = "c8af9e05e64158932173e004953fb516"
val hashExample = HashGenerator.generateHash(timeStamp, privateKey, publicKey)