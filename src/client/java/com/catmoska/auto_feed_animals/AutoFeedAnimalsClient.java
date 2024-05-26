package com.catmoska.auto_feed_animals;

import com.catmoska.auto_feed_animals.ClientMinecraft.Button.ClientButton;
import com.catmoska.auto_feed_animals.FeedAnimals.FeedAnimals;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AutoFeedAnimalsClient implements ClientModInitializer {
	public static final String MODID = "auto_feed_animals";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final FeedAnimals Wheat = new FeedAnimals();

	@Override
	public void onInitializeClient() {
		ClientButton.onInitializeClient();
		Setings.onInitializeClient();

		Wheat.AddTypeFood(Items.WHEAT);
		Wheat.AddTypeAnimal(CowEntity.class);
		Wheat.AddTypeAnimal(SheepEntity.class);




		ClientTickEvents.START_CLIENT_TICK.register(this::onClientTick);
	}

	private void onClientTick(MinecraftClient client) {
		if (!(client.player != null && client.world != null)) return;
		if (!Setings.OnOff) return;

		Wheat.Updeit(client);

	}

}
