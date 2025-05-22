package com.gidm.brushnbid.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        private val FIRST_LAUNCH = booleanPreferencesKey("first_launch")
        private val USER_ID = intPreferencesKey("user_id")
        private val TOKEN = stringPreferencesKey("token")
        private val USERNAME = stringPreferencesKey("username")
        private val EMAIL = stringPreferencesKey("email")
        private val FULLNAME = stringPreferencesKey("fullname")
    }

    suspend fun isFirstLaunch(): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[FIRST_LAUNCH] ?: false
    }

    suspend fun setFirstLaunchDone() {
        context.dataStore.edit { prefs ->
            prefs[FIRST_LAUNCH] = false
        }
    }

    suspend fun saveUserSession(userId: Int, token: String, username: String, email: String, fullname: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[TOKEN] = token
            prefs[USERNAME] = username
            prefs[EMAIL] = email
            prefs[FULLNAME] = fullname
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }

    suspend fun getUserId(): Int? {
        val prefs = context.dataStore.data.first()
        return prefs[USER_ID]
    }

    suspend fun getToken(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[TOKEN]
    }

    suspend fun getUsername(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[USERNAME]
    }

    suspend fun getEmail(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[EMAIL]
    }

    suspend fun getFullname(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[FULLNAME]
    }

}