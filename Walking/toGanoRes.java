package Walking;

import java.util.EnumSet;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path.TraversalOption;
import org.powerbot.game.api.wrappers.node.SceneObject;

import Main.EliteGanos;

public class toGanoRes extends Node {
	private final static Area firstFloorGano = new Area(new Tile[] {
			new Tile(4200, 3000, 3), new Tile(4200, 6000, 3),
			new Tile(4900, 6000, 3), new Tile(4900, 3000, 3)

	});
	
	
	private final static Area secondFloorGano = new Area(new Tile[] {
			new Tile(4200, 3000, 2), new Tile(4200, 6000, 2),
			new Tile(4900, 6000, 2), new Tile(4900, 3000, 2)

	});
	
	private final static Area thirdFloorGano = new Area(new Tile[] {
			new Tile(4200, 3000, 1), new Tile(4200, 6000, 1),
			new Tile(4900, 6000, 1), new Tile(4900, 3000, 1)

	});
	
	private final static Tile[] toResourceDung = new Tile[]{new Tile(4623,5460,3), new Tile(4623,5465,3), new Tile(4625,5471,3), new Tile(4626,5478,3), new Tile(4629,5483,3), new Tile(4634,5486,3), new Tile(4641,5488,3), new Tile(4646,5489,3), new Tile(4653,5489,3), new Tile(4658,5489,3), new Tile(4661,5490,3)};
	private final static int RESDUNGID = 64291;
	private final static Tile[] tofirstVine = new Tile[]{new Tile(4696,5623,3), new Tile(4696,5619,3)};
	private final static int FIRSTVINE = 64360;
	private final static int SECONDVINE = 64359;
	private final static Tile badTile = new Tile(4696,5625,3);
	private final static Tile GoodTile = new Tile(4696,5620,3);
	
	@Override
	public boolean activate() {
		
		return (firstFloorGano.contains(Players.getLocal().getLocation()) || secondFloorGano.contains(Players.getLocal().getLocation())|| thirdFloorGano.contains(Players.getLocal().getLocation())) && EliteGanos.WHICHDUNGEON==2;
	}

	@Override
	public void execute() {
		SceneObject resdung = SceneEntities.getNearest(RESDUNGID);
		SceneObject firstvine = SceneEntities.getNearest(FIRSTVINE);
		SceneObject secondvine = SceneEntities.getNearest(SECONDVINE);
		
		
		Walking.newTilePath(toResourceDung).traverse(
				
				EnumSet.of(TraversalOption.SPACE_ACTIONS));
	
		if (secondvine.isOnScreen() && secondvine!=null){
			Camera.turnTo(secondvine);
			secondvine.interact("Climb-down");
			Task.sleep(2000);
		}
		
		
		
		if(resdung.isOnScreen() && resdung!=null && !badTile.isOnScreen()){
			EliteGanos.status ="Entering Resource Dungeon";
			Camera.turnTo(resdung);
			resdung.interact("Enter");
			Task.sleep(2000);
			
		}
		
		
		Walking.newTilePath(tofirstVine).traverse(
				EnumSet.of(TraversalOption.SPACE_ACTIONS));
		if(firstvine.isOnScreen() && firstvine!=null && GoodTile.isOnScreen()){
			Camera.turnTo(firstvine);
			firstvine.interact("Climb-down");
			Task.sleep(2000);
		}
		
		

	}

}
