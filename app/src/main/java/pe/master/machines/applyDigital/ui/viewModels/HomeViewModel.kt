package pe.master.machines.applyDigital.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.master.machines.applyDigital.data.dataBase.entities.HitEntity
import pe.master.machines.applyDigital.data.emuns.Action
import pe.master.machines.applyDigital.data.models.response.Hit
import pe.master.machines.applyDigital.data.uiEvents.UiEventHome
import pe.master.machines.applyDigital.data.uiState.UiState
import pe.master.machines.applyDigital.ui.usesCase.HitUsesCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val hitUsesCase: HitUsesCase) : ViewModel() {

    private val TAG = HomeViewModel::class.java.simpleName

    private var _getCloudHit: MutableStateFlow<UiState<List<HitEntity>>> =
        MutableStateFlow(UiState.Loading(false, ""))
    val getCloudHit = _getCloudHit.asStateFlow()

    private var _loadSkeleton: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loadSkeleton = _loadSkeleton.asStateFlow()
    private var _loadingData: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingData = _loadingData.asStateFlow()
    private var _dataList: MutableStateFlow<List<HitEntity>> = MutableStateFlow(listOf())
    val dataList = _dataList.asStateFlow()

    var page = 0
    private val blackList = mutableListOf<HitEntity>()
    private val List<Hit>.whiteList
        get() = this.filterNot { hit ->
            hit.storyId in blackList.map { it.storyId }
        }.map { hit -> HitEntity(hit) }.distinctBy { it.storyId }

    init {
        handleEvent(UiEventHome.LoadDataCloud)
    }

    fun handleEvent(event: UiEventHome) {
        when (event) {
            UiEventHome.LoadDataCloud -> {
                page = 0
                getCloudHit()
            }

            UiEventHome.LoadMoreDataCloud -> {
                page += 1
                getCloudHit()
            }

            UiEventHome.LoadDataLocal -> {
                page = 0
                getLocalHit()
            }

            is UiEventHome.DeleteHit -> deleteLocalHit(event.hitEntity)
            is UiEventHome.DisplayData -> _dataList.update { event.dataList }
            is UiEventHome.DisplayMoreData -> _dataList.update { it + event.dataList }
        }
    }

    fun updateLoadSkeleton(value: Boolean) {
        _loadSkeleton.update { value }
    }

    fun updateLoadingData(value: Boolean) {
        _loadingData.update { value }
    }

    private fun getCloudHit() {
        viewModelScope.launch {
            hitUsesCase.invoke("mobile", page).flowOn(Dispatchers.IO).onStart {
                Log.d(TAG, "getCloudHit onStart: ")
                _getCloudHit.update { UiState.Loading(true, "") }
            }.catch { e ->
                Log.d(TAG, "getCloudHit catch: ${e.message}")
                handleEvent(UiEventHome.LoadDataLocal)
            }.collect { result ->
                Log.d(TAG, "getCloudHit collect")
                _getCloudHit.update {
                    if (result.isSuccessful) {
                        val list = result.body()?.hits?.whiteList ?: listOf()
                        saveLocalHit(list)
                        when (page) {
                            0 -> handleEvent(UiEventHome.DisplayData(list))
                            else -> handleEvent(UiEventHome.DisplayMoreData(list))
                        }
                        page = result.body()?.page ?: 0
                        UiState.Success(listOf())
                    } else {
                        UiState.NotSuccess(
                            Throwable(result.errorBody().toString())
                        )
                    }
                }
            }
        }
    }

    private fun getLocalHit() {
        viewModelScope.launch {
            hitUsesCase.invoke().flowOn(Dispatchers.IO).onStart {
                Log.d(TAG, "getLocalHit onStart: ")
                _getCloudHit.update { UiState.Loading(true, "") }
            }.catch { e ->
                Log.d(TAG, "getLocalHit catch: ${e.message}")
                _getCloudHit.update { UiState.NotSuccess(e) }
            }.map { pagingData ->
                val hitList = mutableListOf<HitEntity>()
                pagingData.map {
                    hitList.add(it)
                }
                hitList
            }.collect { list ->
                Log.d(TAG, "getLocalHit collect")
                val newList = list.filterNot { hit ->
                    hit.storyId in blackList.map { it.storyId }
                }.distinctBy { it.storyId }
                _getCloudHit.update {
                    UiState.Success(newList)
                }
                handleEvent(UiEventHome.DisplayData(newList))
            }
        }
    }

    private fun saveLocalHit(list: List<HitEntity>) {
        viewModelScope.launch {
            hitUsesCase.invoke(list, Action.INSERT).flowOn(Dispatchers.IO).onStart {
                Log.d(TAG, "saveLocalHit onStart: ")
            }.catch { e ->
                Log.d(TAG, "saveLocalHit catch: ${e.message}")
            }.collect { list ->
                Log.d(TAG, "saveLocalHit collect")
            }
        }
    }

    private fun deleteLocalHit(hitEntity: HitEntity) {
        viewModelScope.launch {
            hitUsesCase.invoke(listOf(hitEntity), Action.DELETE).flowOn(Dispatchers.IO).onStart {
                Log.d(TAG, "saveLocalHit onStart: ")
            }.catch { e ->
                Log.d(TAG, "saveLocalHit catch: ${e.message}")
            }.collect {
                Log.d(TAG, "saveLocalHit collect")
                blackList.add(hitEntity)
                _dataList.update { list ->
                    list.filterNot { it.storyId == hitEntity.storyId }
                }
            }
        }
    }
}