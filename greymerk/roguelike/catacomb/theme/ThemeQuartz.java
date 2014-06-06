package greymerk.roguelike.catacomb.theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import greymerk.roguelike.catacomb.segment.Segment;
import greymerk.roguelike.worldgen.BlockWeightedRandom;
import greymerk.roguelike.worldgen.Log;
import greymerk.roguelike.worldgen.MetaBlock;
import net.minecraft.src.Block;

public class ThemeQuartz extends ThemeBase{

	public ThemeQuartz(){
	
		MetaBlock walls = new MetaBlock(Block.blockNetherQuartz.blockID);
		
		
		BlockWeightedRandom bridge = new BlockWeightedRandom(new MetaBlock(Block.blockNetherQuartz.blockID));
		bridge.addBlock(new MetaBlock(0), 3);
		
		MetaBlock stair = new MetaBlock(Block.stairsNetherQuartz.blockID);
		MetaBlock pillar = new MetaBlock(Block.blockNetherQuartz.blockID, 2);
		
		this.walls = new BlockSet(walls, stair, pillar);
		
		MetaBlock SegmentWall = new MetaBlock(Block.blockNetherQuartz.blockID, 1);
		
		this.decor =  new BlockSet(SegmentWall, stair, pillar);
		
		this.segments = new ArrayList<Segment>();
		segments.addAll(Arrays.asList(Segment.SHELF, Segment.INSET));
		
		this.arch = Segment.ARCH;
	}
}