package example.apaddon

import dev.koifysh.randomizer.ArchipelagoRandomizer
import example.apaddon.goals.ChatGoal
import net.fabricmc.api.ModInitializer
import net.minecraft.resources.ResourceLocation
import org.slf4j.LoggerFactory

object ExampleAPAddon : ModInitializer {
    private const val MOD_ID = "chat-goal-addon"

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun onInitialize() {
        logger.info("Registering Chat Goal with Archipelago Randomizer")
        ArchipelagoRandomizer.goalRegister.register(
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "chat"),
            ChatGoal::class.java
        )
    }

}