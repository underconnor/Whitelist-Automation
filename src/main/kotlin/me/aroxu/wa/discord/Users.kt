package me.aroxu.wa.discord

import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.User
import dev.kord.core.exception.EntityNotFoundException
import dev.kord.rest.request.KtorRequestException
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Users {
    fun getUserById(id: Snowflake): User? {
        var result: User? = null
        runBlocking {
            launch {
                result = try {
                    WADiscordClient.client.getUser(id)
                } catch (e: Exception) {
                    when (e) {
                        is EntityNotFoundException, is KtorRequestException -> {
                            null
                        }
                        else -> throw e
                    }
                }
            }
        }
        return result
    }
}
