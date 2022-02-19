/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.example.lostandfound.net.retrofit.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.lostandfound.NotificationList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest

class NotificationViewModel(
    private val repository: NotificationList,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        const val KEY_USER = "user"
        const val DEFAULT_USER = "pdjetlic2"
    }

    init {
        if (!savedStateHandle.contains(KEY_USER)) {
            savedStateHandle.set(KEY_USER, DEFAULT_USER)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val posts = savedStateHandle.getLiveData<String>(KEY_USER)
        .asFlow()
        .flatMapLatest { repository.notificationsOfList(it, 30) }
        .cachedIn(viewModelScope)

    fun showUser(user: String) {
        if (!shouldShowUser(user)) return
        savedStateHandle.set(KEY_USER, user)
    }

    private fun shouldShowUser(user: String): Boolean {
        return savedStateHandle.get<String>(KEY_USER) != user
    }
}
