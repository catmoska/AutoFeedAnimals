package com.catmoska.auto_feed_animals;

import com.catmoska.auto_feed_animals.ClientMinecraft.Button.ClientButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class Setings {
	public static boolean OnOff;

	public static void onInitializeClient() {
		ClientButton.OnOff.registerOne(() -> {
			OnOff = !OnOff;
			sendClientMessage(Text.of("Auto Feed Animals: ")
					.copy()
					.append(
							Text.of(OnOff ? "on" : "off").copy().setStyle(Style.EMPTY.withColor(OnOff ? 0x1BE700 : 0xE70004))
					)
			);
		}, 5);
	}

	public static void sendClientMessage(Text message) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player != null) {
			client.player.sendMessage(message, false);
		}
	}
}
