package id.irfanirawansukirman.core.exception

import com.google.gson.annotations.SerializedName


/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
data class ErrorModel(
  @SerializedName("errors")
  val errors: List<String>?,
  @SerializedName("success")
  val success: Boolean,
  @SerializedName("status_code")
  val statusCode: String,
  @SerializedName("status_message")
  val statusMessage: String
)