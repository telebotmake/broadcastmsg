package yourpackage;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

public class BroadcastModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // ثبت دستور کلاینتی با استفاده از Callback جدید
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            LiteralArgumentBuilder<FabricClientCommandSource> command = LiteralArgumentBuilder.literal("broadcast-ip")
                .executes(context -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (client.getNetworkHandler() == null || client.player == null) {
                        return 0;
                    }
                    // برای هر پلیر موجود در لیست شبکه، دستور /msg ارسال می‌کنیم
                    for (PlayerListEntry entry : client.getNetworkHandler().getPlayerList()) {
                        String playerName = entry.getProfile().getName();
                        client.player.networkHandler.sendChatMessage("/msg " + playerName + " بیا 5.10.248.159");
                    }
                    // پیام تأیید در چت کلاینت نمایش داده می‌شود
                    client.inGameHud.getChatHud().addMessage(Text.literal("Broadcast command executed."));
                    return 1;
                });
            dispatcher.register(command);
        });
    }
}
