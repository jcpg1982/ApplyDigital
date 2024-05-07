package pe.master.machines.applyDigital.helpers

import pe.master.machines.applyDigital.BuildConfig

object Constants {

    const val PATH_NAME_APP = BuildConfig.APP_NAME
    const val PACKAGE_NAME = BuildConfig.APPLICATION_ID
    const val NAME_DATA_BASE = "$PATH_NAME_APP.db"

    object Routes {
        const val HOME = "Home"
        const val HIT_DETAIL = "Hit_Details"
    }
}