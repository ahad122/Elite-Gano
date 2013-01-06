package Walking;

import java.util.EnumSet;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path.TraversalOption;
import org.powerbot.game.api.wrappers.node.SceneObject;

import Main.EliteGanos;

public class toCaveDA extends Node {
	// DONE

	//private final static Area DADropOff = new Area(new Tile[] { new Tile(3311, 3223, 0), new Tile(3314, 3340, 0), new Tile(3401, 3341, 0), 
		//new Tile(3401, 3233, 0) });

	
	private final static Area DADropOff = new Area(new Tile[] { new Tile(3400, 3344, 0), new Tile(3401, 3281, 0), new Tile(3380, 3143, 0), 
			new Tile(3221, 3140, 0), new Tile(3247, 3343, 0) });
	/*private final static Tile[] toDoor = new Tile[] { new Tile(3318, 3238, 0),
			new Tile(3320, 3241, 0), new Tile(3322, 3244, 0),
			new Tile(3324, 3246, 0), new Tile(3324, 3250, 0),
			new Tile(3324, 3255, 0), new Tile(3324, 3260, 0),
			new Tile(3324, 3264, 0), new Tile(3327, 3266, 0),
			new Tile(3331, 3267, 0), new Tile(3334, 3269, 0),
			new Tile(3338, 3271, 0), new Tile(3342, 3272, 0),
			new Tile(3343, 3275, 0)

			, new Tile(3349, 3282, 0), new Tile(3353, 3285, 0),
			new Tile(3357, 3287, 0), new Tile(3361, 3289, 0),
			new Tile(3365, 3290, 0), new Tile(3369, 3291, 0),
			new Tile(3373, 3293, 0), new Tile(3375, 3296, 0),
			new Tile(3375, 3302, 0), new Tile(3375, 3307, 0),
			new Tile(3375, 3313, 0), new Tile(3375, 3318, 0),
			new Tile(3376, 3322, 0), new Tile(3379, 3326, 0),
			new Tile(3383, 3328, 0), new Tile(3388, 3328, 0),
			new Tile(3391, 3327, 0), new Tile(3397, 3326, 0),
			new Tile(3402, 3326, 0), new Tile(3407, 3326, 0) };
			*/
	
	
	
	private final static Tile[] toDoor = new Tile[] { new Tile(3294, 3190, 0), new Tile(3294, 3195, 0), new Tile(3296, 3198, 0), 
			new Tile(3298, 3202, 0), new Tile(3300, 3206, 0), new Tile(3302, 3209, 0), 
			new Tile(3304, 3212, 0), new Tile(3305, 3215, 0), new Tile(3305, 3220, 0), 
			new Tile(3305, 3226, 0), new Tile(3307, 3229, 0), new Tile(3308, 3233, 0), 
			new Tile(3311, 3235, 0), new Tile(3314, 3236, 0), new Tile(3317, 3237, 0), 
			new Tile(3319, 3239, 0), new Tile(3320, 3242, 0), new Tile(3322, 3244, 0), 
			new Tile(3323, 3247, 0), new Tile(3324, 3250, 0), new Tile(3324, 3255, 0), 
			new Tile(3324, 3261, 0), new Tile(3325, 3265, 0), new Tile(3329, 3267, 0), 
			new Tile(3333, 3268, 0), new Tile(3337, 3269, 0), new Tile(3341, 3270, 0), 
			new Tile(3344, 3273, 0), new Tile(3346, 3276, 0), new Tile(3348, 3280, 0), 
			new Tile(3351, 3282, 0), new Tile(3355, 3284, 0), new Tile(3359, 3285, 0), 
			new Tile(3363, 3286, 0), new Tile(3366, 3287, 0), new Tile(3370, 3288, 0), 
			new Tile(3373, 3289, 0), new Tile(3374, 3293, 0), new Tile(3374, 3298, 0), 
			new Tile(3374, 3304, 0), new Tile(3374, 3310, 0), new Tile(3374, 3315, 0), 
			new Tile(3374, 3320, 0), new Tile(3376, 3324, 0), new Tile(3379, 3327, 0), 
			new Tile(3383, 3328, 0), new Tile(3387, 3328, 0), new Tile(3390, 3327, 0), 
			new Tile(3393, 3327, 0), new Tile(3398, 3326, 0), new Tile(3404, 3326, 0) };

	private final static int DOOR = 27852;
	

	@Override
	public boolean activate() {

		return  DADropOff.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		EliteGanos.status = "Going to caves..";
		

		SceneObject door = SceneEntities.getNearest(DOOR);
		Walking.newTilePath(toDoor).traverse(
				EnumSet.of(TraversalOption.SPACE_ACTIONS));
		
		
		
		if (door.isOnScreen() && door != null) {
			Task.sleep(1000);
			Camera.turnTo(door);
			door.interact("Open");
			Task.sleep(1500);
		} 
		
	
		
		

		

	}

}
