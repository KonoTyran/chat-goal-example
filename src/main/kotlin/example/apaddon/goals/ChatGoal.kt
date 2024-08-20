package example.apaddon.goals

import com.google.gson.annotations.SerializedName
import dev.koifysh.randomizer.ArchipelagoRandomizer
import dev.koifysh.randomizer.registries.APGoal
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents
import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.PlayerChatMessage
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.BossEvent

class ChatGoal : APGoal() {

    @SerializedName("required_message")
    val requiredMessage: String = "Hello, World!" // will get overwritten by the data in the apmc file

    override val hasBossBar: Boolean = true
    override var isComplete: Boolean = false
    override val bossBarColor: BossEvent.BossBarColor = BossEvent.BossBarColor.BLUE
    override val bossBarCurrentValue: Int = 1
    override val bossBarMaxValue: Int = 1
    override val bossBarOverlay: BossEvent.BossBarOverlay = BossEvent.BossBarOverlay.PROGRESS

    override val bossBarName: Component
        get() = Component.literal("Say \"$requiredMessage\"")

    override fun start() {
        ServerMessageEvents.CHAT_MESSAGE.register(this::chatMessage)
        ArchipelagoRandomizer.server.playerList.players.forEach {
            it.sendSystemMessage(Component.literal("Say \"$requiredMessage\" to complete this goal."))
        }
    }

    private fun chatMessage(chatMessage: PlayerChatMessage, serverPlayer: ServerPlayer, bound: ChatType.Bound) {
        if (isComplete) return

        if (chatMessage.decoratedContent().string == requiredMessage) {
            isComplete = true
            checkCompletion()
        }
    }
}