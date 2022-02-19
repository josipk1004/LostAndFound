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

package com.example.lostandfound

import com.example.lostandfound.entity.ShortNotification
import java.util.concurrent.atomic.AtomicInteger

class NotificationViewModelFactory {
    private val counter = AtomicInteger(0)
    fun createRedditPost(subredditName : String) : ShortNotification {
        val id = counter.incrementAndGet()
        val notif = ShortNotification(
                subject = "subject_$id",
                title = "title $id",
                id = id.toLong()
        )
        return notif
    }
}