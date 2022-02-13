package com.example.lostandfound

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class NotificationViewModel(private val dao: NotificationDao) : ViewModel() {
    val allNotifications: Flow<PagingData<NotificationListItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            maxSize = 30
        )
    ) {
        dao.allNotificationsByName()
    }.flow
        .map { pagingData ->
            pagingData
                .map { notification -> NotificationListItem.Item(notification) }
                .insertSeparators { before: NotificationListItem?, after: NotificationListItem? ->
                    if (before == null && after == null) {
                        null
                    } else if (after == null) {
                        null
                    } else if (before == null) {
                        NotificationListItem.Separator(after.title.first())
                    } else if (!before.title.first().equals(after.title.first(), ignoreCase = true)) {
                        NotificationListItem.Separator(after.title.first())
                    } else {
                        null
                    }
                }
        }
        .cachedIn(viewModelScope)

    fun insert(text: CharSequence) = ioThread {
        dao.insert(
            Notification(
                title = text.toString(), adress = "",
                date = 0,
//                date = Date(), TODO
                description = "",
                user = "this is user and other is title"
//                user = User(email = "", lastName = "", name = "", password = "") TODO
            )
        )
    }

    fun remove(notification: Notification) = ioThread {
        dao.delete(notification)
    }
}