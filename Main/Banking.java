package Main;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Magic;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

import sk.action.ActionBar;
import sk.action.book.magic.Spell;

public class Banking extends Node {
	private static final int [] BANKERS = {11758,782};
	
	private final static Area VarrockBankArea = new Area(new Tile[] { new Tile(3177, 3446, 0), new Tile(3194, 3446, 0), new Tile(3194, 3432, 0), 
			new Tile(3177, 3432, 0) });
	
	private final static Area FallyBankArea= new Area(new Tile[] { new Tile(2946, 3373, 0), new Tile(2942, 3373, 0), new Tile(2942, 3367, 0), 
			new Tile(2947, 3367, 0), new Tile(2947, 3373, 0) });
	
	

	private static final int[]PRAYER_POTS =  {2434,139,141,143};
	private static final int[]PRAY_FLASKS = {23243,23245,23247,23249,23251,23253};
	private static final int [] RENEWALS = {21630,21632,21634,21636};
	private static final int[] RENEW_FLASKS = {23609,23611,23613,23615,23617,23619};
	public static int SHARK = 385;
	public static int MONKFISH = 7946;
	public static int ROCKTAIL = 15272;
	public static int MANTARAY = 391;
			
	
	public final static int VARROCK_TAB = 8007;
	public final static int FALA_TAB = 8009;
	public static boolean PrayIsEnab = false;
	public static int WHICHPRAYTYPE = 2434;
	public static int FirstRuneType;
	public static int SecondRuneType;
	public static int ThirdRuneType;
	public static int amountfirstrune = 0;
	public static int amountsecondrune = 0;
	public static int amountthirdrune = 0;
	public static final int AIR_RUNE = 556;
	public static final int DEATH_RUNE = 560;
	public static final int LAW_RUNE = 563;
	public static final int CHAOS_RUNE = 562;
	public static final int MIND_RUNE = 558;
	public static final int COSMIC_RUNE = 564;
	public static final int EARTH_RUNE = 557;
	public static final int SOUL_RUNE = 566;
	public static final int WATER_RUNE = 555;
	public static final int FIRE_RUNE = 554;
	public static final int NATURE_RUNE = 561;
	public static final int ASTRAL_RUNE = 9075;
	public static final int BLOOD_RUNE = 565;
	public static final int ARMADYL_RUNE = 21773;
	
	
	
	
	
	
	@Override
	public boolean activate() {
		
		return (VarrockBankArea.contains(Players.getLocal().getLocation()) || FallyBankArea.contains(Players.getLocal().getLocation()));
	}

	@Override
	public void execute() {
		Mouse.setSpeed(Speed.FAST);
		Task.sleep(3000);
		
		ActionBar.setExpanded(false);
	
		SceneObject banker = SceneEntities.getNearest(BANKERS);
		Item praypot = Inventory.getItem(PRAYER_POTS);
		Item varTele = Inventory.getItem(VARROCK_TAB);
		Item falTele = Inventory.getItem(FALA_TAB);
		
		
				
		if(banker.isOnScreen() && banker!=null){
			EliteGanos.status = "Banking..";
			banker.interact("Bank");
			Task.sleep(3000);
			if(Players.getLocal().isMoving()){
				Task.sleep(4000);
			} else if(!Bank.open()){
				banker.interact("Bank");
			}
			else if(Bank.isOpen()){
			Bank.depositInventory();
			
			EliteGanos.status = "Withdrawing items..";
			Task.sleep(500);
			Bank.withdraw(EliteGanos.TELETAB, 1);
			Task.sleep(500);
			if(EliteGanos.ENABLEFIRSTRUNE == true){
				Bank.withdraw(FirstRuneType, amountfirstrune);
			}
			if(EliteGanos.ENABLESECONDRUNE == true){
				Bank.withdraw(SecondRuneType, amountsecondrune);
			}
			
			if(EliteGanos.ENABLETHIRDRUNE==true){
				Bank.withdraw(ThirdRuneType, amountthirdrune);
			}
			if(EliteGanos.RenewIsEnab){
				Bank.withdraw(EliteGanos.TypeofRenew, EliteGanos.numberofrenew);
			}
			
			if(EliteGanos.NumberPrayPots!=0){
			Bank.withdraw(WHICHPRAYTYPE,EliteGanos.NumberPrayPots);
			Task.sleep(200);
			}
			
			if(EliteGanos.ENABLEFOOD == true){
				Bank.withdraw(EliteGanos.WHICHFOODTOUSE, EliteGanos.howmuchfood);
			}
			
			Bank.close();
			}
			
			
			Task.sleep(1500);
			
			if(EliteGanos.SPELLUSING == 1){
				ActionBar.useAbility(Spell.FIRE_SURGE);
				} 
				if(EliteGanos.SPELLUSING == 2){
					ActionBar.useAbility(Spell.FIRE_WAVE);
				}
				
				Task.sleep(1000);
			EliteGanos.status = "Going to caves..";
				
				Widgets.get(548, 131).interact("Ability Book");
				Task.sleep(500);
				Widgets.get(275,41).interact("Magic");
				Task.sleep(300);
				Widgets.get(275, 47).interact("Teleport-spells");
				Task.sleep(200);
				ActionBar.setExpanded(true);
				
				Mouse.click(575, 355, true);
				if(EliteGanos.MethodToCave == "Al-Kharid Lodestone"){
				Task.sleep(1500);
				if(Widgets.get(1092, 40).isOnScreen()){
					Widgets.get(1092, 40).interact("Teleport");
				}
		
				Task.sleep(12000);
				} else if (EliteGanos.MethodToCave == "Fairy Ring(BIP)"){
					Task.sleep(1500);
					if(Widgets.get(1092, 52).isOnScreen()){
						Widgets.get(1092, 52).interact("Teleport");
						Task.sleep(12000);
					}
				}
				
			
			
			
			
		}
	}

}
