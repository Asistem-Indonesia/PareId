package id.asistem.pareid

import android.app.Application
import id.asistem.pareid.data.db.AppDatabase
import id.asistem.pareid.data.preferences.PrefManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class PareId : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@PareId))

//        bind() from singleton { NetworkConnectionInterceptor(instance()) }
//        bind() from singleton { MyApi(instance()) }
//        bind() from singleton { AppDatabase(instance()) }
//
//        //Repository
//        bind() from singleton { ParticipantRepository(instance(), instance()) }
//        bind() from singleton { ProfileRepository(instance(), instance()) }
//        bind() from singleton { ExamRepository(instance()) }
//        bind() from singleton { AudioRepository(instance()) }
//        bind() from singleton { GyroscopeRepository(instance()) }
//        bind() from singleton { FaceDetectionRepository(instance()) }
//        bind() from singleton { StatusExamRepository(instance(),instance()) }
//        bind() from singleton { ActivityExamRepository(instance()) }
//        bind() from singleton { ImageRepository(instance()) }
//
//        //factory
//        bind() from singleton { AuthViewModelFactory(instance()) }
//        bind() from singleton { AuthFaceViewModelFactory(instance()) }
//        bind() from provider { ProfileViewModelFactory(instance()) }
//        bind() from provider { ExamViewModelFactory(instance(),instance()) }
//        bind() from provider { GyroViewModelFactory(instance()) }
//        bind() from provider { AudioViewModelFactory(instance()) }
//        bind() from provider { StatusExamViewModelFactory(instance()) }
//        bind() from provider { ActivityExamViewModelFactory(instance()) }
//        bind() from provider { HomeViewModelFactory(instance(),instance(),instance()) }
//        bind() from provider { CameraViewModelFactory(instance()) }
    }

    companion object {
        @get:Synchronized
        lateinit var instance: PareId
        lateinit var prefManager: PrefManager
        lateinit var db: AppDatabase

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefManager = PrefManager(this)
        db = AppDatabase(this)
    }
}