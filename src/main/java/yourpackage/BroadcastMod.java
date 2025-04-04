package yourpackage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import com.mojang.brigadier.context.CommandContext;

public class BroadcastMod implements ModInitializer {

    @Override
    public void onInitialize() {
        System.out.println("[Broadcast Mod] مود فعال شد!");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("broadcast-ip")
                .executes(context -> broadcastIP(context, "بیا 5.10.248.159")));
        });
    }

    private int broadcastIP(CommandContext<ServerCommandSource> context, String message) {
        MinecraftServer server = context.getSource().getServer();

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            String playerName = player.getName().getString();
            String command = String.format("msg %s %s", playerName, message);
            server.getCommandManager().executeWithPrefix(context.getSource(), command);
        }

        // اصلاح شده با Supplier<Text>
        context.getSource().sendFeedback(() -> Text.literal("پیام \"" + message + "\" به همه ارسال شد!"), false);

        return 1;
    }
}
