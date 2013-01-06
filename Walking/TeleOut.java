package Walking;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

import Main.EliteGanos;

public class TeleOut extends Node {
	
	private static final int[]PRAYER_POTS =  {2434,139,141,143
	,23243,23245,23247,23249,23251,23253};

	private final static int VARROCK_TAB = 8007;
	private final static int FALA_TAB = 8009;
	
	Item praypots = Inventory.getItem(PRAYER_POTS);
	Item varTab = Inventory.getItem(VARROCK_TAB);
	Item falTab = Inventory.getItem(FALA_TAB);
	

	@Override
	public boolean activate() {
		
		
		return Inventory.getCount(PRAYER_POTS) == 0 && (Inventory.getCount(VARROCK_TAB)!= 0 || Inventory.getCount(FALA_TAB)!= 0) && EliteGanos.PrayIsEnab == true ;
	}

	@Override
	public void execute() {
		System.out.print("TeleOut Class");
		Item varTab = Inventory.getItem(VARROCK_TAB);
		Item falTab = Inventory.getItem(FALA_TAB);
		
		if(Inventory.getCount(VARROCK_TAB)!= 0 ){
			varTab.getWidgetChild().interact("Break");
			Task.sleep(1500);
		} else if (Inventory.getCount(FALA_TAB)!= 0){
			falTab.getWidgetChild().interact("Break");
			Task.sleep(1500);
		
		}
		

	}

}
