package inCombat;


import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;

import sk.action.Ability;
import sk.action.ActionBar;
import sk.action.book.BookAbility;
import sk.action.book.magic.Spell;

import Main.EliteGanos;

public class Fight extends Node {
	
	private static final int BEAST = 14696;
	private static final int VIAL_ID = 229;
	
	private final static Area GANOAREA = new Area(new Tile[] {
			new Tile(4200, 3000, 0), new Tile(4200, 6000,0),
			new Tile(4900, 6000, 0), new Tile(4900, 3000, 0)

	});
	public static boolean useRenewals = true;
	
	private static final int [] RENEWALS = {21630,21632,21634,21636,23609,23611,23613,23615,23617,23619};


	@Override
	public boolean activate() {
		NPC beast = NPCs.getNearest(BEAST);
		
		return beast!=null && beast.isOnScreen()  && !Players.getLocal().isInCombat()  && GANOAREA.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {

		NPC beast = NPCs.getNearest(BEAST);
		Item vial = Inventory.getItem(VIAL_ID);
		Item renewal = Inventory.getItem(RENEWALS);
		if(Inventory.getCount(VIAL_ID) != 0){
			vial.getWidgetChild().interact("Drop");
		}
		
		if(EliteGanos.SPELLUSING == 1){
		ActionBar.useAbility(Spell.FIRE_SURGE);
		} 
		if(EliteGanos.SPELLUSING == 2){
			ActionBar.useAbility(Spell.FIRE_WAVE);
		
		}
		
		
		
		
		if(Widgets.get(749, 0).getTextureId() == 1209){
			Prayer.toggleQuick(true);
		}
		
		if(Inventory.contains(RENEWALS) && useRenewals == true){
			renewal.getWidgetChild().interact("Drink");
			Task.sleep(500);
		}
		
		Camera.turnTo(beast);
		beast.interact("Attack");
		Task.sleep(1000);
		
		

	}
	public int getPrayPoints(){
		return Integer.parseInt(Widgets.get(749,6).getText());
	}
	

}