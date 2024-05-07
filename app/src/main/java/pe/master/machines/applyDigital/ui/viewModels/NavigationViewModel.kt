package pe.master.machines.applyDigital.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pe.master.machines.applyDigital.data.models.Route
import java.util.Stack
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val _currentRoute: MutableStateFlow<Route> = MutableStateFlow(Route.Home)
    val currentRoute = _currentRoute.asStateFlow()

    private val backStack = Stack<Route>()

    val onBack: Boolean
        get() {
            return if (backStack.isNotEmpty()) {
                _currentRoute.value = backStack.pop()
                false
            } else true
        }

    fun onNavigate(route: Route) {
        viewModelScope.launch {
            backStack.push(currentRoute.value)
            _currentRoute.value = route
        }
    }
}