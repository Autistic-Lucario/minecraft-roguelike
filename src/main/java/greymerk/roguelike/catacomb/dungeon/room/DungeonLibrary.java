package greymerk.roguelike.catacomb.dungeon.room;

import greymerk.roguelike.catacomb.dungeon.DungeonBase;
import greymerk.roguelike.catacomb.settings.CatacombLevelSettings;
import greymerk.roguelike.catacomb.theme.ITheme;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.Door;
import greymerk.roguelike.worldgen.IBlockFactory;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

public class DungeonLibrary extends DungeonBase{

	@Override
	public boolean generate(World world, Random rand, CatacombLevelSettings settings, Cardinal[] entrances, int x, int y, int z) {
		
		IBlockFactory walls = settings.getTheme().getPrimaryWall();
		MetaBlock air = new MetaBlock(Blocks.air);
		Coord origin = new Coord(x, y, z);
		Coord cursor;
		Coord start;
		Coord end;
		
		
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y, z - 4, x + 4, y + 3, z + 4, air, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 3, y + 4, z - 3, x + 3, y + 6, z + 3, air, true, true);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 2, y + 7, z - 2, x + 2, y + 7, z + 2, air, true, true);
		
		WorldGenPrimitive.fillRectHollow(world, rand, x - 5, y, z - 5, x + 5, y + 4, z + 5, walls, false, true);
		WorldGenPrimitive.fillRectHollow(world, rand, x - 4, y + 3, z - 4, x + 4, y + 7, z + 4, walls, false, true);
		WorldGenPrimitive.fillRectHollow(world, rand, x - 3, y + 6, z - 3, x + 3, y + 8, z + 3, walls, false, true);
		
		WorldGenPrimitive.fillRectSolid(world, rand, x - 5, y - 1, z - 5, x + 5, y - 1, z + 5, settings.getTheme().getPrimaryFloor(), true, true);
		
		for(Cardinal dir : Cardinal.directions){
			
			Cardinal[] orth = Cardinal.getOrthogonal(dir);
			
			if(Arrays.asList(entrances).contains(dir)){
				door(world, rand, settings.getTheme(), dir, origin);
			} else {
				desk(world, rand, settings.getTheme(), dir, origin);
			}
			
			start = new Coord(origin);
			start.add(dir, 4);
			start.add(orth[0], 4);
			end = new Coord(start);
			end.add(Cardinal.UP, 4);
			WorldGenPrimitive.fillRectSolid(world, rand, start, end, settings.getTheme().getPrimaryPillar(), true, true);
			
			start = new Coord(origin);
			start.add(dir, 3);
			start.add(orth[0], 3);
			start.add(Cardinal.UP, 3);
			end = new Coord(start);
			end.add(Cardinal.UP, 3);
			WorldGenPrimitive.fillRectSolid(world, rand, start, end, settings.getTheme().getPrimaryPillar(), true, true);
			
			cursor = new Coord(end);
			cursor.add(Cardinal.reverse(dir));
			cursor.add(orth[1]);
			cursor.add(Cardinal.UP);
			WorldGenPrimitive.setBlock(world, rand, cursor, walls, true, true);
			
			for(Cardinal o : orth){
				cursor = new Coord(origin);
				cursor.add(dir, 4);
				cursor.add(o, 3);
				cursor.add(Cardinal.UP, 2);
				MetaBlock stair = settings.getTheme().getPrimaryStair();
				WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(o), true).setBlock(world, cursor);
				cursor.add(Cardinal.UP);
				WorldGenPrimitive.setBlock(world, rand, cursor, walls, true, true);
				cursor.add(Cardinal.reverse(o));
				WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(o), true).setBlock(world, cursor);
				cursor.add(Cardinal.UP, 3);
				cursor.add(Cardinal.reverse(dir));
				WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(o), true).setBlock(world, cursor);
				
			}
		}
		
		
		
		
		return false;
	}

	private void door(World world, Random rand, ITheme theme, Cardinal dir, Coord pos){
		Coord start;
		Coord end;
		Cardinal[] orth = Cardinal.getOrthogonal(dir);
		
		start = new Coord(pos);
		start.add(dir, 7);
		end = new Coord(start);
		start.add(orth[0]);
		end.add(orth[1]);
		end.add(Cardinal.UP, 2);
		
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, theme.getPrimaryWall(), true, true);
		
		Coord cursor = new Coord(pos);
		cursor.add(dir, 7);
		Door.generate(world, cursor, dir, Door.WOOD);
		
		for(Cardinal o : orth){
			
			cursor = new Coord(pos);
			cursor.add(dir, 5);
			cursor.add(o);
			cursor.add(Cardinal.UP, 2);
			
			MetaBlock stair = theme.getPrimaryStair();
			WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(dir), true).setBlock(world, cursor);
			cursor.add(dir);
			WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(o), true).setBlock(world, cursor);
		}
	}
	
	private void desk(World world, Random rand, ITheme theme, Cardinal dir, Coord pos){
		Coord start;
		Coord end;
		Coord cursor;
		
		Cardinal[] orth = Cardinal.getOrthogonal(dir);
		MetaBlock shelf = new MetaBlock(Blocks.bookshelf);

		for(Cardinal o : orth){
			start = new Coord(pos);
			start.add(dir, 5);
			start.add(o, 2);
			end = new Coord(start);
			end.add(Cardinal.UP, 2);
			WorldGenPrimitive.fillRectSolid(world, rand, start, end, shelf, true, true);
		}
		
		start = new Coord(pos);
		start.add(dir, 6);
		end = new Coord(start);
		start.add(orth[0], 2);
		end.add(orth[1], 2);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, theme.getSecondaryWall(), true, true);
		
		start.add(Cardinal.UP);
		end.add(Cardinal.UP, 2);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, shelf, true, true);
		
		start.add(Cardinal.reverse(dir));
		end.add(Cardinal.reverse(dir));
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, new MetaBlock(Blocks.air), true, true);
		
		cursor = new Coord(pos);
		cursor.add(dir, 4);
		
		MetaBlock stair = new MetaBlock(Blocks.oak_stairs);
		WorldGenPrimitive.blockOrientation(stair, dir, false).setBlock(world, cursor);
		
		cursor.add(dir);
		WorldGenPrimitive.blockOrientation(stair, Cardinal.reverse(dir), true).setBlock(world, cursor);
		
		cursor.add(orth[0]);
		WorldGenPrimitive.blockOrientation(stair, orth[1], true).setBlock(world, cursor);
		
		cursor.add(orth[1], 2);
		WorldGenPrimitive.blockOrientation(stair, orth[0], true).setBlock(world, cursor);
		
		
	}
	
	@Override
	public int getSize() {
		return 8;
	}

}
