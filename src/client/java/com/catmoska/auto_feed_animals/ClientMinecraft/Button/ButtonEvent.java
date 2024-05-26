package com.catmoska.auto_feed_animals.ClientMinecraft.Button;

import com.catmoska.auto_feed_animals.AutoFeedAnimalsClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class ButtonEvent {
	private KeyBinding Key;
	private List<Click> listFuncsions = new ArrayList<>();
	private List<LastTick> listFuncsionsOne = new ArrayList<>();

	private int isCallLastTick = 0;

	public ButtonEvent(String name, int glfw) {
		Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key." + AutoFeedAnimalsClient.MODID + "." + name,
				InputUtil.Type.KEYSYM,
				glfw,
				AutoFeedAnimalsClient.MODID
		));
		ClientTickEvents.END_CLIENT_TICK.register(this::ClientTickEvents_END_CLIENT_TICK);
	}

	public void register(Click ev) {
		listFuncsions.add(ev);
	}

	public void registerOne(Click ev, int lastTick) {
		listFuncsionsOne.add(new LastTick(ev,lastTick));
	}

	private void ClientTickEvents_END_CLIENT_TICK(MinecraftClient client) {
		boolean isCall = false;
		while (Key.wasPressed()) {
			isCall = true;
			for (var ev : listFuncsions)
				ev.onClick();

			for (var lt : listFuncsionsOne)
				if (isCallLastTick > lt.lastTick)
					lt.click.onClick();
		}
		if (isCall)
			isCallLastTick = 0;
		else
			isCallLastTick++;
	}

	@FunctionalInterface
	public interface Click {
		void onClick();
	}

	public class LastTick {
		public final Click click;
		public final int lastTick;

		public LastTick(Click click, int lastTick) {

			this.click = click;
			this.lastTick = lastTick;
		}
	}
}
