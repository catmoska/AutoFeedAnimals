package com.catmoska.auto_feed_animals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class AutoFeedAnimalsClient implements ClientModInitializer {

	private final List<CowEntity> fedCows = new ArrayList<>();
	private int indexFedCows = 1;
	private final List<SheepEntity> fedSheep = new ArrayList<>();
	private int indexFedSheep = 1;


	@Override
	public void onInitializeClient() {
		ClientTickEvents.START_CLIENT_TICK.register(this::onClientTick);
	}

	private void onClientTick(MinecraftClient client) {
		if (!(client.player != null && client.world != null))
			return;
		if  (fedCows.size() <= indexFedCows || fedCows.isEmpty()) {
			fedCows.clear();
			scanAndFeedCows(client);
			indexFedCows = 0;
		}
		if (fedSheep.size() <= indexFedSheep || fedSheep.isEmpty()) {
			fedSheep.clear();
			scanAndFeedSheep(client);
			indexFedSheep = 0;
		}
		if (fedCows.size() > indexFedCows) {
			feedCow(client, fedCows.get(indexFedCows));
			indexFedCows++;
		}

		if (fedSheep.size() > indexFedSheep) {
			feedSheep(client, fedSheep.get(indexFedSheep));
			indexFedSheep++;
		}
	}

	private void scanAndFeedCows(MinecraftClient client) {
		// Проверьте, держит ли игрок сено в руке
		ItemStack heldItem = client.player.getStackInHand(Hand.MAIN_HAND);
		if (heldItem.getItem() != Items.WHEAT) {
			return;
		}

		// Определите область вокруг игрока (например, 5 блоков во все стороны)
		Box scanBox = new Box(
				client.player.getX() - 5, client.player.getY() - 5, client.player.getZ() - 5,
				client.player.getX() + 5, client.player.getY() + 5, client.player.getZ() + 5
		);

		// Найдите коров в данной области
		client.world.getEntitiesByClass(CowEntity.class, scanBox, cow -> true).forEach(cow -> {
			// Кормление коровы
			if (!cow.isBaby()) {
				fedCows.add(cow);
			}
		});
	}

	private void scanAndFeedSheep(MinecraftClient client) {
		// Проверьте, держит ли игрок сено в руке
		ItemStack heldItem = client.player.getStackInHand(Hand.MAIN_HAND);
		if (heldItem.getItem() != Items.WHEAT) {
			return;
		}

		// Определите область вокруг игрока (например, 5 блоков во все стороны)
		Box scanBox = new Box(
				client.player.getX() - 5, client.player.getY() - 5, client.player.getZ() - 5,
				client.player.getX() + 5, client.player.getY() + 5, client.player.getZ() + 5
		);

		client.world.getEntitiesByClass(SheepEntity.class, scanBox, sheep -> true).forEach(sheep -> {
			// Кормление коровы
			if (!sheep.isBaby()) {
				fedSheep.add(sheep);
			}
		});
	}

	private void feedCow(MinecraftClient client, CowEntity cow) {
		// Посылаем пакет на сервер для взаимодействия с коровой
		client.interactionManager.interactEntity(client.player, cow, Hand.MAIN_HAND);
	}

	private void feedSheep(MinecraftClient client, SheepEntity cow) {
		// Посылаем пакет на сервер для взаимодействия с коровой
		client.interactionManager.interactEntity(client.player, cow, Hand.MAIN_HAND);
	}
}