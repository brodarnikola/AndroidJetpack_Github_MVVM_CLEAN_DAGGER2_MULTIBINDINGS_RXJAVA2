/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vjezba.androidjetpackgithub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vjezba.androidjetpackgithub.ui.fragments.EnterDetailsViewState
import com.vjezba.domain.repository.UserManager
import javax.inject.Inject


/**
 * RegistrationViewModel is the ViewModel that the Registration flow ([RegistrationActivity]
 * and fragments) uses to keep user's input data.
 */
// @Inject tells Dagger how to provide instances of this type
// Dagger also knows that UserManager is a dependency

// Scopes this ViewModel to components that use @ActivityScope

class RegistrationViewModel @Inject internal constructor(val userManager: UserManager) : ViewModel() {

    private var username: String? = null
    private var password: String? = null
    private var acceptedTCs: Boolean? = null

   /* private val _username = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val password: LiveData<String> = _password

    private val _acceptedTCs = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val acceptedTCs: LiveData<String> = _acceptedTCs*/

    fun updateUserData(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun acceptTCs() {
        acceptedTCs = true
    }

    fun registerUser() {
        assert(username != null)
        assert(password != null)
        assert(acceptedTCs == true)

        userManager.registerUser(username!!, password!!)
    }
}
