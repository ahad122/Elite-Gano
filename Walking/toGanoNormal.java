package Walking;

import java.util.EnumSet;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.Path.TraversalOption;
import org.powerbot.game.api.wrappers.node.SceneObject;

import Main.EliteGanos;

public class toGanoNormal extends Node {
	// DONE

	private final static Tile[] firstLadder = new Tile[] {
			new Tile(4623, 5457, 3), new Tile(4629, 5454, 3) };
	private final static int FirstVine = 64360;
	private final static int SecondVine = 64359;
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

	private final static Tile[] thirdLadder = new Tile[] {
			new Tile(4628, 5440, 1), new Tile(4629, 5434, 1),
			new Tile(4629, 5427, 1), new Tile(4630, 5421, 1),
			new Tile(4630, 5414, 1), new Tile(4629, 5407, 1),
			new Tile(4628, 5399, 1), new Tile(4633, 5392, 1),
			new Tile(4640, 5391, 1), new Tile(4648, 5391, 1) };
	
	
	public static String status;
	private static final int BEAST = 14696;
	NPC beast = NPCs.getNearest(BEAST);

	@Override
	public boolean activate() {

		return (firstFloorGano.contains(Players.getLocal().getLocation()) || secondFloorGano.contains(Players.getLocal().getLocation())|| thirdFloorGano.contains(Players.getLocal().getLocation())) && EliteGanos.WHICHDUNGEON==1;
	}

	@Override
	public void execute() {
		
Camera.setAngle(217);
Camera.setPitch(57);
		// EliteGanos.status = "Going to beast :o";

		SceneObject firstvine = SceneEntities.getNearest(FirstVine);
		SceneObject secondvine = SceneEntities.getNearest(SecondVine);
		Walking.newTilePath(firstLadder).traverse(
				EnumSet.of(TraversalOption.SPACE_ACTIONS));
		
		
		Walking.newTilePath(thirdLadder).traverse(
				EnumSet.of(TraversalOption.SPACE_ACTIONS));
		
		if (secondvine.isOnScreen() && secondvine != null) {
			Camera.turnTo(secondvine);

			secondvine.interact("Climb-down");
			Task.sleep(5000);
			
		}
		if (firstvine.isOnScreen() && firstvine != null) {
			Camera.turnTo(firstvine);
			firstvine.interact("Climb-down");
			Task.sleep(1500);
		}else

		
		Walking.newTilePath(firstLadder).traverse(
				EnumSet.of(TraversalOption.SPACE_ACTIONS));

	}

}
