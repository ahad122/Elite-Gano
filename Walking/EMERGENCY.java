package Walking;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class EMERGENCY extends Node {
	private final static int VARROCK_TAB = 8007;
	private final static int FALA_TAB = 8009;

	@Override
	public boolean activate() {
		
		return getHp()<2500 && (Inventory.getCount(VARROCK_TAB)!= 0 || Inventory.getCount(FALA_TAB)!= 0);
	}

	@Override
	public void execute() {
		System.out.print("Emergency");
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
	
	private int getHp(){
		return Integer.parseInt(Widgets.get(748, 8).getText());
	}

}
