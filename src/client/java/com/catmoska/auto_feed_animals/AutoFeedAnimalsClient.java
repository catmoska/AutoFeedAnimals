package com.catmoska.auto_feed_animals;

import com.catmoska.auto_feed_animals.ClientMinecraft.Button.ClientButton;
import com.catmoska.auto_feed_animals.FeedAnimals.FeedAnimals;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.*;
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
	public static final FeedAnimals Pig = new FeedAnimals();
	public static final FeedAnimals Horse = new FeedAnimals();
	public static final FeedAnimals Chicken = new FeedAnimals();
	public static final FeedAnimals Cat = new FeedAnimals();
	public static final FeedAnimals Rabbit = new FeedAnimals();
	public static final FeedAnimals Llama = new FeedAnimals();
	public static final FeedAnimals Turtle = new FeedAnimals();
	public static final FeedAnimals Fox = new FeedAnimals();
	public static final FeedAnimals Panda = new FeedAnimals();
	public static final FeedAnimals Bee = new FeedAnimals();
	public static final FeedAnimals Strider = new FeedAnimals();
	public static final FeedAnimals Hoglin = new FeedAnimals();
	public static final FeedAnimals Axolotl = new FeedAnimals();
	public static final FeedAnimals Frog = new FeedAnimals();
	public static final FeedAnimals Camel = new FeedAnimals();

	@Override
	public void onInitializeClient() {
		ClientButton.onInitializeClient();
		Setings.onInitializeClient();

		Wheat.AddTypeFood(Items.WHEAT);
		Wheat.AddTypeAnimal(CowEntity.class);
		Wheat.AddTypeAnimal(SheepEntity.class);
		Wheat.AddTypeAnimal(MooshroomEntity.class);
		Wheat.AddTypeAnimal(GoatEntity.class);


		Pig.AddTypeFood(Items.CARROT);
		Pig.AddTypeFood(Items.POTATO);
		Pig.AddTypeFood(Items.BEETROOT);
		Pig.AddTypeAnimal(PigEntity.class);


		Horse.AddTypeFood(Items.GOLDEN_CARROT);
		Horse.AddTypeFood(Items.GOLDEN_APPLE);
		Horse.AddTypeAnimal(HorseEntity.class);
		Horse.AddTypeAnimal(DonkeyEntity.class);


		Chicken.AddTypeFood(Items.WHEAT_SEEDS);
		Chicken.AddTypeFood(Items.MELON_SEEDS);
		Chicken.AddTypeFood(Items.PUMPKIN_SEEDS);
		Chicken.AddTypeFood(Items.BEETROOT_SEEDS);
		Chicken.AddTypeAnimal(ChickenEntity.class);


		Chicken.AddTypeFood(Items.ROTTEN_FLESH);
		Chicken.AddTypeFood(Items.CHICKEN);
		Chicken.AddTypeFood(Items.COOKED_CHICKEN);
		Chicken.AddTypeFood(Items.PORKCHOP);
		Chicken.AddTypeFood(Items.COOKED_PORKCHOP);
		Chicken.AddTypeFood(Items.BEEF);
		Chicken.AddTypeFood(Items.COOKED_BEEF);
		Chicken.AddTypeFood(Items.RABBIT);
		Chicken.AddTypeFood(Items.COOKED_RABBIT);
		Chicken.AddTypeFood(Items.MUTTON);
		Chicken.AddTypeFood(Items.COOKED_MUTTON);
		Chicken.AddTypeAnimal(WolfEntity.class);


		Cat.AddTypeFood(Items.COD);
		Cat.AddTypeFood(Items.SALMON);
		Cat.AddTypeAnimal(CatEntity.class);
		Cat.AddTypeAnimal(OcelotEntity.class);


		Rabbit.AddTypeFood(Items.CARROT);
		Rabbit.AddTypeFood(Items.GOLDEN_CARROT);
		Rabbit.AddTypeAnimal(RabbitEntity.class);


		Llama.AddTypeFood(Items.HAY_BLOCK);
		Llama.AddTypeAnimal(LlamaEntity.class);


		Turtle.AddTypeFood(Items.SEAGRASS);
		Turtle.AddTypeAnimal(TurtleEntity.class);


		Fox.AddTypeFood(Items.SWEET_BERRIES);
		Fox.AddTypeFood(Items.GLOW_BERRIES);
		Fox.AddTypeAnimal(FoxEntity.class);


		Panda.AddTypeFood(Items.BAMBOO);
		Panda.AddTypeAnimal(PandaEntity.class);


		Bee.AddTypeFood(Items.DANDELION);
		Bee.AddTypeFood(Items.POPPY);
		Bee.AddTypeFood(Items.BLUE_ORCHID);
		Bee.AddTypeFood(Items.ALLIUM);
		Bee.AddTypeFood(Items.AZURE_BLUET);
		Bee.AddTypeFood(Items.RED_TULIP);
		Bee.AddTypeFood(Items.ORANGE_TULIP);
		Bee.AddTypeFood(Items.WHITE_TULIP);
		Bee.AddTypeFood(Items.PINK_TULIP);
		Bee.AddTypeFood(Items.OXEYE_DAISY);
		Bee.AddTypeFood(Items.CORNFLOWER);
		Bee.AddTypeFood(Items.LILY_OF_THE_VALLEY);
		Bee.AddTypeFood(Items.WITHER_ROSE);
		Bee.AddTypeFood(Items.SUNFLOWER);
		Bee.AddTypeFood(Items.LILAC);
		Bee.AddTypeFood(Items.ROSE_BUSH);
		Bee.AddTypeFood(Items.PEONY);
		Bee.AddTypeAnimal(BeeEntity.class);


		Strider.AddTypeFood(Items.WARPED_FUNGUS);
		Strider.AddTypeAnimal(StriderEntity.class);


		Hoglin.AddTypeFood(Items.CRIMSON_FUNGUS);
		Hoglin.AddTypeAnimal(HorseEntity.class);


		Axolotl.AddTypeFood(Items.TROPICAL_FISH_BUCKET);
		Axolotl.AddTypeAnimal(AxolotlEntity.class);


		Frog.AddTypeFood(Items.SLIME_BALL);
		Frog.AddTypeAnimal(FrogEntity.class);


		Camel.AddTypeFood(Items.CACTUS);
		Camel.AddTypeAnimal(CamelEntity.class);

		ClientTickEvents.START_CLIENT_TICK.register(this::onClientTick);
	}

	private void onClientTick(MinecraftClient client) {
		if (!(client.player != null && client.world != null)) return;
		if (!Setings.OnOff) return;

		Wheat.Updeit(client);
		Pig.Updeit(client);
		Horse.Updeit(client);
		Chicken.Updeit(client);
		Cat.Updeit(client);
		Rabbit.Updeit(client);
		Llama.Updeit(client);
		Turtle.Updeit(client);
		Fox.Updeit(client);
		Panda.Updeit(client);
		Bee.Updeit(client);
		Strider.Updeit(client);
		Hoglin.Updeit(client);
		Axolotl.Updeit(client);
		Frog.Updeit(client);
		Camel.Updeit(client);
	}

}
