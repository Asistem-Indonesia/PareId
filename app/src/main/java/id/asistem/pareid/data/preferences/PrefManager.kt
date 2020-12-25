package id.asistem.pareid.data.preferences

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "pareId"
private const val KEY_USER_ID = "key_user_id"

class PrefManager(context: Context) {

    private val sp: SharedPreferences by lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    val spe: SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clearId() {
        sp.edit().remove(KEY_USER_ID).apply()
    }


//                            PARENT
//=================================================================================

    var spUsername: String?
        get() = sp.getString("KEY_USERNAME", "")
        set(value) {
            spe.putString("KEY_USERNAME", value)
            spe.commit()
        }

    var spPassword: String?
        get() = sp.getString("KEY_PASSWORD", "")
        set(value) {
            spe.putString("KEY_PASSWORD", value)
            spe.commit()
        }

    var spIsLogin: Boolean
        get() = sp.getBoolean("KEY_IS_LOGIN", false)
        set(value) {
            spe.putBoolean("KEY_IS_LOGIN", value)
            spe.commit()
        }

    var spId: Int?
        get() = sp.getInt("KEY_ID_CLICK", 0)
        set(value) {
            spe.putInt("KEY_ID_CLICK", value!!)
            spe.commit()
        }

    var spApp: String?
        get() = sp.getString("KEY_APP", "")
        set(value) {
            spe.putString("KEY_APP", value)
            spe.commit()
        }


//                            CHILD
//=================================================================================


    var spUsernameChild: String?
        get() = sp.getString("KEY_USERNAME_CHILD", "")
        set(value) {
            spe.putString("KEY_USERNAME_CHILD", value)
            spe.commit()
        }

    var spIdChild: Int
        get() = sp.getInt("KEY_ID_CHILD", 0)
        set(value) {
            spe.putInt("KEY_ID_CHILD", value)
            spe.commit()
        }

    var spIsLoginChild: Boolean
        get() = sp.getBoolean("KEY_IS_LOGIN_CHILD", false)
        set(value) {
            spe.putBoolean("KEY_IS_LOGIN_CHILD", value)
            spe.commit()
        }

}
