package example.apaddon

import dev.koifysh.randomizer.ArchipelagoRandomizer
import dev.koifysh.randomizer.registries.APGoal
import example.apaddon.goals.ChatGoal
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents
import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.PlayerChatMessage
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import org.slf4j.LoggerFactory

object ExampleAPAddon : ModInitializer {
	private const val MOD_ID = "chat-goal-addon"

	private val logger = LoggerFactory.getLogger(this::class.java)

	override fun onInitialize() {
		logger.info("Registering Chat Goal with Archipelago Randomizer")
		ArchipelagoRandomizer.goalRegister.register(ResourceLocation.fromNamespaceAndPath(MOD_ID,"chat"), ChatGoal::class.java)
	}

}