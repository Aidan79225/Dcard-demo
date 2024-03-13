package com.aidan.dcard

import com.aidan.dcard.entity.RepoInfo
import com.aidan.dcard.entity.UserInfo
import com.aidan.dcard.ui.RepoInfoItemCallback
import com.aidan.dcard.util.DateSerializer
import org.junit.Assert
import org.junit.Test

class RepoInfoItemCallbackTest {
    private val repoInfoItemCallback =  RepoInfoItemCallback()

    private fun createDummyUser(): UserInfo {
        return UserInfo(
            id = 1,
            name = "",
            avatarUrl = ""
        )
    }

    private fun createDummyRepo(): RepoInfo {
        return RepoInfo(
            id = 1,
            fullName = "",
            description = "",
            owner = createDummyUser(),
            topics = emptyList(),
            star = 0,
            language = "",
            languageColor = "#2F81F7",
            updatedAt = null
        )
    }

    @Test
    fun `verify it should be same item but their contents are different`() {
        val a = createDummyRepo().copy(fullName = "a", description = "a")
        val b = createDummyRepo().copy(fullName = "b", description = "b")
        Assert.assertTrue(repoInfoItemCallback.areItemsTheSame(a, b))
        Assert.assertFalse(repoInfoItemCallback.areContentsTheSame(a, b))
    }
}