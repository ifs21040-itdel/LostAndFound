package com.ifs21040.lostandfound.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ifs21040.lostandfound.ViewModelFactory
import com.ifs21040.lostandfound.data.pref.UserModel
import com.ifs21040.lostandfound.data.remote.MyResult
import com.ifs21040.lostandfound.data.remote.response.DataLoginResponse
import com.ifs21040.lostandfound.data.repository.AuthRepository


class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun login(email: String, password: String):
            LiveData<MyResult<DataLoginResponse>> {
        return authRepository.login(email, password).asLiveData()
    }

    suspend fun saveSession(user: UserModel): LiveData<UserModel> {
        return authRepository.saveSession(user).asLiveData()
    }
    companion object {
        @Volatile
        private var INSTANCE: LoginViewModel? = null
        fun getInstance(
            authRepository: AuthRepository
        ): LoginViewModel {
            synchronized(ViewModelFactory::class.java) {
                INSTANCE = LoginViewModel(
                    authRepository
                )
            }
            return INSTANCE as LoginViewModel
        }
    }
}