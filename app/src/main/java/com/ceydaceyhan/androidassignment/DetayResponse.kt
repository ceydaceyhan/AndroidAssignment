package com.ceydaceyhan.androidassignment

import com.google.gson.annotations.SerializedName

data class DetayResponse(
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("support") var support: Support? = Support()
)
