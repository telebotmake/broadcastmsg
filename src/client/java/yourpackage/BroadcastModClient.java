package yourpackage;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class BroadcastModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // پیام تست در چت نشون بده برای اطمینان از اجرای مود
        MinecraftClient.getInstance().execute(() -> {
            if (MinecraftClient.getInstance().player != null) {
                MinecraftClient.getInstance().inGameHud.getChatHud()
                        .addMessage(Text.literal("✅ مود Broadcast فعال شد!"));
            }
        });

        // ثبت دستور /broadcast-ip
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                literal("broadcast-ip").executes(context -> {
                    MinecraftClient client = MinecraftClient.getInstance();

                    if (client.getNetworkHandler() == null || client.player == null) {
                        return 0;
                    }

                    for (PlayerListEntry entry : client.getNetworkHandler().getPlayerList()) {
                        String playerName = entry.getProfile().getName();
                        client.player.networkHandler.sendChatMessage("/msg " + playerName + " بیا 5.10.248.159");
                    }

                    client.inGameHud.getChatHud().addMessage(Text.literal("📢 پیام برای همه ارسال شد."));
                    return 1;
                })
            );
        });
    }
}
