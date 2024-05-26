package com.catmoska.auto_feed_animals.FeedAnimals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class FeedAnimals {
	private final List<Class<? extends AnimalEntity>> ListAnimals = new ArrayList<>();
	private List<AnimalEntity> ListAnimalsNear = new ArrayList<>();
	private final List<Item> ListFood = new ArrayList<>();
	private int indexFeed = 1;

	public void Updeit(MinecraftClient client) {
		ItemStack heldItem = client.player.getStackInHand(Hand.MAIN_HAND);
		if (!ListFood.contains(heldItem.getItem())) {
			indexFeed = 0;
			ListAnimalsNear.clear();
			return;
		}

		if (ListAnimalsNear.size() <= indexFeed || ListAnimalsNear.isEmpty()) {
			ListAnimalsNear.clear();
			ListAnimalsNear = getScanAndFeed(client);
			indexFeed = 0;
		}
		if (ListAnimalsNear.size() > indexFeed) {
			feed(client, ListAnimalsNear.get(indexFeed));
			indexFeed++;
		}
	}

	public void AddTypeFood(Item i) {
		ListFood.add(i);
	}

	public void AddTypeAnimal(Class<? extends AnimalEntity> e) {
		ListAnimals.add(e);
	}

	private List<AnimalEntity> getScanAndFeed(MinecraftClient client) {
		net.minecraft.util.math.Box scanBox = new Box(client.player.getX() - 5, client.player.getY() - 5, client.player.getZ() - 5, client.player.getX() + 5, client.player.getY() + 5, client.player.getZ() + 5);

		List<AnimalEntity> animalList = new ArrayList<>();

		for (var aType : ListAnimals)
			client.world.getEntitiesByClass(aType, scanBox, en -> true).forEach(animal -> {
				if (!animal.isBaby()) {
					animalList.add(animal);
				}
			});
		return animalList;
	}

	private void feed(MinecraftClient client, AnimalEntity cow) {
		client.interactionManager.interactEntity(client.player, cow, Hand.MAIN_HAND);
	}
}
