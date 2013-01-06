package Walking;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ToSecondFairyRing extends Node {

	
	private final static Area sndFairyRingArea = new Area(new Tile[]{new Tile(2417,4439,0), new Tile(2416,4430,0), new Tile(2407,4430,0), new Tile(2409,4440,0)});
	private final static int sFAIRY_RING = 12128;
	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return sndFairyRingArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		SceneObject sfairyring = SceneEntities.getNearest(sFAIRY_RING);
		
		if(Widgets.get(734, 3).isOnScreen() && sndFairyRingArea.contains(Players.getLocal().getLocation())){
			Widgets.get(734,3).interact("Rotate anticlockwise");
			Task.sleep(2000);
			Widgets.get(734,21).interact("Teleport");
			Task.sleep(4000);
		}
		
		if(sfairyring.isOnScreen() && sfairyring!=null && sndFairyRingArea.contains(Players.getLocal().getLocation())){
			sfairyring.interact("Use");
			Task.sleep(3000);
			
			
		}

	}

}
