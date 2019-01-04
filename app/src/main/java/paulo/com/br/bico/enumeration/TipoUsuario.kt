package paulo.com.br.bico.enumeration

enum class TipoUsuario private constructor(var label: String?) {

    ADM("ADMINISTRADOR"),
    GE("GERENTE"),
    PR("PROMOTOR"),
    F("FRANQUIA"),
    IM("IMPLANTADOR"),
    CS("COORDENADOR DE SUPORTE"),
    CO("COLABORADOR"),
    TE("TELEMARKETING"),
    FIN("FINANCEIRO"),
    CIM("COORDENADOR DE IMPLANTAÇÃO"),
    CLI("CLIENTE"),
    CT("CONSULTOR TECNICO")

}
