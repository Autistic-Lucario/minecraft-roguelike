package greymerk.roguelike.catacomb.dungeon.room;

import greymerk.roguelike.catacomb.Catacomb;
import greymerk.roguelike.catacomb.dungeon.IDungeon;
import greymerk.roguelike.catacomb.theme.ITheme;
import greymerk.roguelike.config.RogueConfig;
import greymerk.roguelike.treasure.TreasureChest;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.Log;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;

public class DungeonsMusic implements IDungeon {
	World world;
	Random rand;
	int originX;
	int originY;
	int originZ;
	
	public DungeonsMusic() {
	}

	public boolean generate(World inWorld, Random inRandom, ITheme theme, int inOriginX, int inOriginY, int inOriginZ) {
		world = inWorld;
		rand = inRandom;
		originX = inOriginX;
		originY = inOriginY;
		originZ = inOriginZ;

		MetaBlock air = new MetaBlock(0);
		
		// fill air
		WorldGenPrimitive.fillRectSolid(world, inRandom, originX - 5, originY, originZ - 5, originX + 5, originY + 3, originZ + 5, air);
		
		// shell
		WorldGenPrimitive.fillRectHollow(world, rand, originX - 6, originY - 2, originZ - 6, originX + 6, originY + 5, originZ + 6, new MetaBlock(Block.stoneBrick.blockID, 0, 2), false, true);
		
		// floor
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY - 1, originZ - 5, originX + 5, originY - 1, originZ + 5, Block.planks.blockID, 1, 2, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 3, originY, originZ - 3, originX + 3, originY, originZ + 3, 171, rand.nextInt(16), 2, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 2, originY, originZ - 2, originX + 2, originY, originZ + 2, 171, rand.nextInt(16), 2, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 1, originY, originZ - 1, originX + 1, originY, originZ + 1, 171, rand.nextInt(16), 2, true, true);
		
		//WALLS
		MetaBlock log = Log.getLog(Log.OAK);
		
		// vertical beams
		WorldGenPrimitive.fillRectSolid(world, inRandom, originX - 2, originY, originZ - 5, originX - 2, originY + 2, originZ - 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, inRandom, originX + 2, originY, originZ - 5, originX + 2, originY + 2, originZ - 5, log, true, true);

		WorldGenPrimitive.fillRectSolid(world, inRandom, originX - 2, originY, originZ + 5, originX - 2, originY + 2, originZ + 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, inRandom, originX + 2, originY, originZ + 5, originX + 2, originY + 2, originZ + 5, log, true, true);

		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY, originZ - 2, originX - 5, originY + 2, originZ - 2, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY, originZ + 2, originX - 5, originY + 2, originZ + 2, log, true, true);

		WorldGenPrimitive.fillRectSolid(world, rand, originX + 5, originY, originZ - 2, originX + 5, originY + 2, originZ - 2, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 5, originY, originZ + 2, originX + 5, originY + 2, originZ + 2, log, true, true);

		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY, originZ - 5, originX - 5, originY + 2, originZ - 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY, originZ + 5, originX - 5, originY + 2, originZ + 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 5, originY, originZ - 5, originX + 5, originY + 2, originZ - 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 5, originY, originZ + 5, originX + 5, originY + 2, originZ + 5, log, true, true);

		// shelves
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 4, originY, originZ - 5, originX - 3, originY, originZ - 5, 159, 15, 2, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 3, originY, originZ - 5, originX + 4, originY, originZ - 5, 159, 15, 2, true, true);
		
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 4, originY, originZ + 5, originX - 3, originY, originZ + 5, 159, 15, 2, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 3, originY, originZ + 5, originX + 4, originY, originZ + 5, 159, 15, 2, true, true);
		
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY, originZ - 4, originX - 5, originY, originZ - 3, 159, 15, 2, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY, originZ + 3, originX - 5, originY, originZ + 4, 159, 15, 2, true, true);
		
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 5, originY, originZ - 4, originX + 5, originY, originZ - 3, 159, 15, 2, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 5, originY, originZ + 3, originX + 5, originY, originZ + 4, 159, 15, 2, true, true);
		
		HashSet<Coord> chestSpace = new HashSet<Coord>();
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX - 4, originY + 1, originZ - 5, originX - 3, originY + 1, originZ - 5));
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX + 3, originY + 1, originZ - 5, originX + 4, originY + 1, originZ - 5));
		
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX - 4, originY + 1, originZ + 5, originX - 3, originY + 1, originZ + 5));
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX + 3, originY + 1, originZ + 5, originX + 4, originY + 1, originZ + 5));
		
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX - 5, originY + 1, originZ - 4, originX - 5, originY + 1, originZ - 3));
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX - 5, originY + 1, originZ + 3, originX - 5, originY + 1, originZ + 4));
		
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX + 5, originY + 1, originZ - 4, originX + 5, originY + 1, originZ - 3));
		chestSpace.addAll(WorldGenPrimitive.getRectSolid(originX + 5, originY + 1, originZ + 3, originX + 5, originY + 1, originZ + 4));

		TreasureChest.generate(world, rand, new ArrayList<Coord>(chestSpace), TreasureChest.MUSIC);
		
		// horizontal beams
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY + 3, originZ - 5, originX - 5, originY + 3, originZ + 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY + 3, originZ - 5, originX + 5, originY + 3, originZ - 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY + 3, originZ + 5, originX + 5, originY + 3, originZ + 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 5, originY + 3, originZ - 5, originX + 5, originY + 3, originZ + 5, log, true, true);
		
		// ceiling cross beams
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 2, originY + 4, originZ - 5, originX - 2, originY + 4, originZ + 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX + 2, originY + 4, originZ - 5, originX + 2, originY + 4, originZ + 5, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY + 4, originZ - 2, originX + 5, originY + 4, originZ - 2, log, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY + 4, originZ + 2, originX + 5, originY + 4, originZ + 2, log, true, true);
		
		// ceiling lamp
		WorldGenPrimitive.setBlock(world, originX, originY + 4, originZ, Block.blockRedstone.blockID);
		WorldGenPrimitive.setBlock(world, originX - 1, originY + 4, originZ, Block.redstoneLampActive.blockID);
		WorldGenPrimitive.setBlock(world, originX + 1, originY + 4, originZ, Block.redstoneLampActive.blockID);
		WorldGenPrimitive.setBlock(world, originX, originY + 4, originZ - 1, Block.redstoneLampActive.blockID);
		WorldGenPrimitive.setBlock(world, originX, originY + 4, originZ + 1, Block.redstoneLampActive.blockID);
		
		// ceiling fill
		WorldGenPrimitive.fillRectSolid(world, rand, originX - 5, originY + 4, originZ - 5, originX + 5, originY + 4, originZ + 5, 159, 15, 2, true, false);
		
		// music box
		WorldGenPrimitive.setBlock(world, originX, originY, originZ, Block.jukebox.blockID);
		
		return true;
	}
	
	public boolean isValidDungeonLocation(World world, int originX, int originY, int originZ) {
		return false;
	}

	public int getSize(){
		return 7;
	}
	
}
