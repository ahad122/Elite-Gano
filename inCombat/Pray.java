package inCombat;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.wrappers.node.Item;

import Main.EliteGanos;

public class Pray extends Node {

	private static final int[]PRAYER_POTS =  {2434,139,141,143};
	private static final int[]PRAY_FLASKS = {23243,23245,23247,23249,23251,23253};
	
	
	@Override
	public boolean activate() {
		Item praypots = Inventory.getItem(PRAYER_POTS);
		
		return (((Inventory.getCount(PRAYER_POTS) !=0)|| Inventory.getCount(PRAY_FLASKS) != 0) && (getPrayPoints()<= 500));
	}

	@Override
	public void execute() {
		
		Item praypots = Inventory.getItem(PRAYER_POTS);
		Item prayflasks = Inventory.getItem(PRAY_FLASKS);
		
		if(Inventory.getCount(PRAYER_POTS)>0){
		praypots.getWidgetChild().interact("Drink");
		Task.sleep(900);
		} else {
			prayflasks.getWidgetChild().interact("Drink");
			Task.sleep(900);
		}

	}
	
	
	public int getPrayPoints(){
		return Integer.parseInt(Widgets.get(749,6).getText());
	}

}
