package cz.utb.fai.counterdatastore.data

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFERENCES_STORE_NAME = "my_store"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_STORE_NAME
)
class CounterDataStore(context: Context) {

    private val COUNTER_VALUE = intPreferencesKey("counter_value")
    private val USER_EMAIL = stringPreferencesKey("user_email")
    private val USER_NAME = stringPreferencesKey("user_name")

    suspend fun saveCounterValue(value: Int, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[COUNTER_VALUE] = value
        }
    }

    suspend fun saveUserEmailAndName(email: String, name: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
            preferences[USER_NAME] = name
        }
    }

    val counterFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            // on first run the value is 0
            preferences[COUNTER_VALUE] ?: 0
        }

    val emailFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            // on first run the value is 0
            preferences[USER_EMAIL] ?: ""
        }

    val nameFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            // on first run the value is 0
            preferences[USER_NAME] ?: ""
        }
}