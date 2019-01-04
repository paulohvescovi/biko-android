package paulo.com.br.bico.configuration

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class CoroutinesScope {

    val job = SupervisorJob ()
    val scopeAsync = CoroutineScope(Dispatchers.Default + job)
    val scopeUI = CoroutineScope(Dispatchers.Main)

}