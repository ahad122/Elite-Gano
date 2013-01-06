package inCombat;

import java.util.ArrayList;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.GroundItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Loot extends Node {
	public final static int BLUE_CHARM = 12163;
	public static boolean pickBlue = false;
	public final static int GOLD_CHARM = 12158;
	public static boolean pickGold = false;
	public final static int CRIMSON_CHARM = 12160;
	public static boolean pickCrim = false;
	public final static int GREEN_CHARM = 12159;
	public static boolean pickGreen = false;
	public final static int EFFIGY = 18778;
	public static boolean pickEff = false;
	public final static int GANO_FLAKE = 22451;
	public static boolean pickFlake = false;
	public final static int YEW_SEED = 5315;
	public static boolean pickYewSeed = false;
	public final static int TORSTOL_SEED = 5304;
	public static boolean pickTorstolSeed = false;
	public final static int CLEAN_TORSTOL = 270;
	public static boolean pickCleanTorstol = false;
	public final static int POLYPORE_SPORE = 22448;
	public static boolean pickPolypore = false;
	public final static int YEW_LOG = 1516;
	public static boolean pickYewLog = false;
	public final static int ADAMANT_BAR = 2362;
	public static boolean pickAdBar = false;
	public final static int RUNE_BAR = 2364;
	public static boolean pickRuneBar = false;
	public final static int PAP_SEED = 5288;
	public static boolean pickPapSeed = false;
	public final static int PALM_SEED = 5289;
	public static boolean pickPalm = false;
	public final static int MAGIC_SEED = 5316;
	public static boolean pickMagic = false;

	public static ArrayList<Integer> whattoloot = new ArrayList<Integer>();
	private int [] actualloot = toInt(  whattoloot) ;
	GroundItem loot = GroundItems.getNearest(actualloot );
	
	

	@Override
	public boolean activate() {
		GroundItem loot = GroundItems.getNearest(actualloot );

		return loot!=null &&loot.isOnScreen();
	}

	@Override
	public void execute() {
		GroundItem loot = GroundItems.getNearest(actualloot);
		System.out.print("Derp");
		Mouse.setSpeed(Speed.FAST);

		 Camera.turnTo(loot);
		 loot.interact("Take");
		 Task.sleep(500);

	}

	public int[] toInt(final ArrayList<Integer> whattoloot) {
	    int[] actualloot = new int[whattoloot.size()];
	    int k = 0;
	    for (final Integer i : whattoloot) {
	        actualloot[k] = i.intValue();
	        k++;
	    }
	    return actualloot;
	}

}
