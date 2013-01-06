package Walking;

import java.util.EnumSet;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path.TraversalOption;

import Main.EliteGanos;

public class toCaveLS extends Node {
	//DONE
	private final static Tile[] loadToPath = new Tile[] { new Tile(3293, 3191, 0), new Tile(3295, 3197, 0), new Tile(3299, 3202, 0), 
			new Tile(3301, 3208, 0), new Tile(3304, 3213, 0), new Tile(3306, 3218, 0), 
			new Tile(3306, 3225, 0), new Tile(3308, 3230, 0), new Tile(3309, 3234, 0), 
			new Tile(3314, 3236, 0) };
	
	private final static Area Kharid2 = new Area(new Tile[] { new Tile(3417, 3335, 0), new Tile(3297, 3335, 0), new Tile(3282, 3175, 0), 
			new Tile(3411, 3181, 0) });

	@Override
	public boolean activate() {
		
		return false;// Kharid2.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		EliteGanos.status = "Going to caves..";
		Walking.newTilePath(loadToPath).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));

	}

}
