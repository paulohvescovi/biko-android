package paulo.com.br.bico.service

import paulo.com.br.bico.entity.CategorieService

interface CategorieServiceService {

    fun allRemote(success: (List<CategorieService>) -> Unit, failure: () -> Unit = {})

    fun allLocal(success: (List<CategorieService>) -> Unit, failure: () -> Unit = {})

}