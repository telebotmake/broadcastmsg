package yourpackage;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class BroadcastMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCommandManager.DISPATCHER.register(
            literal("broadcast-ip").executes(context -> {
                MinecraftClient client = MinecraftClient.getInstance();

                if (client.getNetworkHandler() == null || client.player == null) {
                    return 0;
                }

                for (PlayerListEntry entry : client.getNetworkHandler().getPlayerList()) {
                    String playerName = entry.getProfile().getName();
                    client.player.sendChatMessage("/msg " + playerName + " بیا 5.10.248.159");
                }

                client.inGameHud.getChatHud().addMessage(Text.literal("پیام برای همه پلیرها فرستاده شد."));
                return 1;
            })
        );
    }
}
