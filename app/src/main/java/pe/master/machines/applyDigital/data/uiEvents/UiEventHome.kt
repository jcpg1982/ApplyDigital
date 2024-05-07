package pe.master.machines.applyDigital.data.uiEvents

import pe.master.machines.applyDigital.data.dataBase.entities.HitEntity


sealed interface UiEventHome {
    data object LoadDataCloud : UiEventHome
    data object LoadMoreDataCloud : UiEventHome
    data object LoadDataLocal : UiEventHome
    data class DeleteHit(val hitEntity: HitEntity) : UiEventHome
    data class DisplayData(val dataList: List<HitEntity>) : UiEventHome
    data class DisplayMoreData(val dataList: List<HitEntity>) : UiEventHome
}
