package inCombat;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

import Main.EliteGanos;

public class Heal extends Node {

	@Override
	public boolean activate() {
		
		return Inventory.getCount(EliteGanos.WHICHFOODTOUSE) != 0 && getHp()<=4000;
	}

	@Override
	public void execute() {
		Item foodeating = Inventory.getItem(EliteGanos.WHICHFOODTOUSE);
		EliteGanos.status = "Eating..";
foodeating.getWidgetChild().interact("Eat");
Task.sleep(1000);
	}
	
	private int getHp(){
		return Integer.parseInt(Widgets.get(748, 8).getText());
	}

}
