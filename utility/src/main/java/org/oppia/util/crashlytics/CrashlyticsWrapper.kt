package org.oppia.util.crashlytics

import java.lang.Exception
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

/** Controller for providing custom crash reporting to Firebase Crashlytics */

class CrashlyticsWrapper @Inject constructor(){

  private val firebaseCrashlytics = FirebaseCrashlytics.getInstance()
  /** Logs a custom non-fatal exception to Firebase Crashlytics */
  fun logException(exception: Exception) {
    firebaseCrashlytics.recordException(exception)
  }

  /** Logs a custom log message which can be put alongside a crash report to Firebase Crashlytics */
  fun logMessage(message: String, firebaseCrashlytics: FirebaseCrashlytics){
    firebaseCrashlytics.log(message)
  }

  /** Sets up a user identifier which is attached in every crash report to Firebase Crashlytics */
  fun setUserIdentifier(identifier: String, firebaseCrashlytics: FirebaseCrashlytics){
    firebaseCrashlytics.setUserId(identifier)
  }
}