package Walking;

import java.util.EnumSet;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path.TraversalOption;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ToFairyRing extends Node {
	
	private final static Area toFairyRingArea = new Area(new Tile[] { new Tile(2544, 3136, 0), new Tile(2542, 3081, 0), new Tile(2512, 3082, 0), 
			new Tile(2516, 3139, 0) });
	
	
	private final static Tile[] toFairyRing = new Tile[] { new Tile(2526, 3100, 0), new Tile(2526, 3106, 0), new Tile(2526, 3110, 0), 
			new Tile(2526, 3114, 0), new Tile(2526, 3118, 0), new Tile(2526, 3122, 0), 
			new Tile(2527, 3125, 0) };
	
	private final static int FAIRY_RING = 14112;
	private final static int sFAIRY_RING = 12128;

	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return toFairyRingArea.contains(Players.getLocal().getLocation()) ;
	}

	@Override
	public void execute() {
		SceneObject fairyring = SceneEntities.getNearest(FAIRY_RING);
	
		
		Walking.newTilePath(toFairyRing).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));
		
		
		 
		if((fairyring.isOnScreen()) && (fairyring!=null) && (toFairyRingArea.contains(Players.getLocal().getLocation()))){
			fairyring.interact("Use");
			Task.sleep(6000);
		}
		Task.sleep(2000);
		
		
		
		

	}

}
