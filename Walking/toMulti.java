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

public class toMulti extends Node {
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

	private final static Tile[] toJump = new Tile[] { new Tile(4623, 5460, 3),
			new Tile(4623, 5465, 3), new Tile(4625, 5471, 3),
			new Tile(4626, 5478, 3), new Tile(4629, 5483, 3),
			new Tile(4634, 5486, 3), new Tile(4641, 5488, 3),
			new Tile(4646, 5489, 3), new Tile(4653, 5489, 3),
			new Tile(4656, 5484, 3), new Tile(4656, 5479, 3),
			new Tile(4658, 5476, 3), new Tile(4667, 5477, 3),
			new Tile(4672, 5477, 3), new Tile(4676, 5477, 3),
			new Tile(4680, 5476, 3), new Tile(4681, 5476, 3), new Tile(4688,5475,3), new Tile(4690,5472,3), new Tile(4691,5470,3) };
	private final static int FIRST_JUMP = 64294;
	private final static int SECOND_JUMP = 64295;
	private final static int FirstVine = 64359;
	private final static int SecondVine = 64359;
	private final static Area wheretojump = new Area(new Tile[] {
			new Tile(4654, 5481, 3), new Tile(4654, 5474, 3),
			new Tile(4658, 5474, 3), new Tile(4658, 5481, 3) });

	private final static Tile wheretwojump =
			new Tile(4681, 5476, 3);
	private final static Tile wherethreevine = new Tile(4718,5468,1);

	/*private final static Tile[] tofirstvine = new Tile[] {
			new Tile(4688, 5474, 3), new Tile(4690, 5474, 3),
			new Tile(4691, 5470, 3) };
			*/
	private final static Tile[] tosecondvine = new Tile[] {
			new Tile(4690, 5473, 2), new Tile(4689, 5478, 2) };

	private final static Tile[] tothirdvine = new Tile[] {
		new Tile(4692,5481,1), new Tile(4694,5484,1), new Tile(4698,5486,1), new Tile(4704,5486,1), new Tile(4701,5486,1), new Tile(4715,5486,1), new Tile(4717,5482,1), new Tile(4717,5477,1), new Tile(4717,5472,1), new Tile(4718,5468,1)
	};
	private final static Area whentogodown = new Area(new Tile[] { new Tile(4693,5473,3), new Tile(4689,5473,3), new Tile(4689,5470,3), new Tile(4693,5470,3) });
	private final static Tile whentwogodown = 
			new Tile(4689, 5478, 2);

	@Override
	public boolean activate() {

		return (firstFloorGano.contains(Players.getLocal().getLocation())
				|| secondFloorGano.contains(Players.getLocal().getLocation())
				|| thirdFloorGano.contains(Players.getLocal().getLocation())) && EliteGanos.WHICHDUNGEON==3;
	}

	@Override
	public void execute() {
		Camera.setAngle(242);
		Camera.setPitch(93);
		SceneObject firstjump = SceneEntities.getNearest(FIRST_JUMP);
		SceneObject secondjump = SceneEntities.getNearest(SECOND_JUMP);
		SceneObject firstvine = SceneEntities.getNearest(FirstVine);
		SceneObject secondvine = SceneEntities.getNearest(SecondVine);
		Walking.newTilePath(tosecondvine).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));
		Walking.newTilePath(tothirdvine).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));
		if (secondvine.isOnScreen() && secondvine != null
				&& (whentwogodown.isOnMap()||wherethreevine.isOnMap())) {
			Camera.turnTo(secondvine);
			secondvine.interact("Climb-down");
			Task.sleep(3000);
		}
		if(secondjump.isOnScreen() && secondjump!=null && wheretwojump.isOnScreen()){
			Camera.turnTo(secondjump);
			secondjump.interact("Jump");
			Task.sleep(4000);
		}
		
		if (firstjump.isOnScreen() && firstjump != null
				&& wheretojump.contains(Players.getLocal().getLocation())) {
			Camera.turnTo(firstjump);
			firstjump.interact("Jump");
			Task.sleep(8000);
		} 
		
		Walking.newTilePath(tosecondvine).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));
		
		Task.sleep(3000);
		if (secondvine.isOnScreen() && secondvine != null
				&& whentwogodown.isOnMap()) {
			Camera.turnTo(secondvine);
			secondvine.interact("Climb-down");
			Task.sleep(3000);
		}
		else
		if (firstvine.isOnScreen() && firstvine != null
				&& whentogodown.contains(Players.getLocal().getLocation())) {
			Camera.turnTo(firstvine);
			firstvine.interact("Climb-down");
			Task.sleep(3000);
		}
		

		if (firstjump.isOnScreen() && firstjump != null
				&& wheretojump.contains(Players.getLocal().getLocation())) {
			Camera.turnTo(firstjump);
			firstjump.interact("Jump");
			Task.sleep(5000);
		} 
		

		Walking.newTilePath(toJump).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));
		
		
		if (firstjump.isOnScreen() && firstjump != null
				&& wheretojump.contains(Players.getLocal().getLocation())) {
			Camera.turnTo(firstjump);
			firstjump.interact("Jump");
			Task.sleep(5000);
		} else	if (firstjump.isOnScreen() && firstjump != null
				&& wheretojump.contains(Players.getLocal().getLocation())) {
			Camera.turnTo(firstjump);
			firstjump.interact("Jump");
			Task.sleep(8000);
		} 
	

		
		//Walking.newTilePath(tofirstvine).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));
		
		

		

		/*
		 * if (firstvine.isOnScreen() && firstvine != null &&
		 * whentogodown.isOnScreen()) { Camera.turnTo(firstvine);
		 * 
		 * firstvine.interact("Climb-down"); Task.sleep(5000);
		 * 
		 * }
		 */

	}

}
