package paulo.com.br.bico.service

import paulo.com.br.bico.entity.CategorieService
import paulo.com.br.bico.entity.CategorieServiceFull

interface CategorieServiceService {

    fun allRemote(success: (List<CategorieServiceFull>) -> Unit, failure: () -> Unit = {})

    fun allLocal(success: (List<CategorieServiceFull>) -> Unit, failure: () -> Unit = {})

}