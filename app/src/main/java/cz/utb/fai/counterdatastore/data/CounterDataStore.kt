package cz.utb.fai.counterdatastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val PREFERENCES_STORE_NAME = "my_store"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_STORE_NAME
)
class CounterDataStore(context: Context) {

}