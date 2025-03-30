package com.gidm.brushnbid.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        private val FIRST_LAUNCH = booleanPreferencesKey("first_launch")
    }

    suspend fun isFirstLaunch(): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[FIRST_LAUNCH] ?: true
    }

    suspend fun setFirstLaunchDone() {
        context.dataStore.edit { prefs ->
            prefs[FIRST_LAUNCH] = false
        }
    }
}