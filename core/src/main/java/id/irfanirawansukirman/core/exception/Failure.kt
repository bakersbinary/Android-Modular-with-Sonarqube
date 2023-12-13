package id.irfanirawansukirman.core.exception

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
sealed class Failure {
  data class RemoteFailure(val message: String) : Failure()
  data class ServerFailure(val message: String) : Failure()
  data class CommonFailure(val message: String) : Failure()
}

class RemoteException(
  val errorModel: ErrorModel
) : Exception()
