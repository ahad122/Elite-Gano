package Walking;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

import Main.EliteGanos;

public class EnterCave extends Node {
	
	
	private final static Area CAVEAREA = new Area(new Tile[] { new Tile(3401, 3344, 0), new Tile(3401, 3320, 0), new Tile(3417, 3320, 0), 
			new Tile(3421, 3343, 0) });
	
	private final static int CAVE = 63093;
	

	@Override
	public boolean activate() {
		
		return CAVEAREA.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
	Camera.setPitch(27);
	Camera.setAngle(336);
		SceneObject cave = SceneEntities.getNearest(CAVE);
		cave.interact("Enter");
		EliteGanos.status = "Entered the Dungeon";
		Task.sleep(3000);
		
	}

}
