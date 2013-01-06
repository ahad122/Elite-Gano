package Walking;

import java.util.EnumSet;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path.TraversalOption;

import Main.EliteGanos;

public class BankFally extends Node {
	//Done
	private final static Area FalBankPath = new Area(new Tile[] { new Tile(2975, 3409, 0), new Tile(2957, 3409, 0), new Tile(2956, 3393, 0), 
			new Tile(2933, 3393, 0), new Tile(2933, 3374, 0), new Tile(2971, 3375, 0), 
			new Tile(2974, 3375, 0), new Tile(2975, 3375, 0) });
	
	
	/*private final static Area GanoArea = new Area(new Tile []{
		new Tile(5000,300)
		*/	
	
	Tile[] toFallyBank = new Tile[] { new Tile(2965, 3396, 0), new Tile(2964, 3388, 0), new Tile(2960, 3382, 0), 
			new Tile(2954, 3380, 0), new Tile(2949, 3376, 0), new Tile(2945, 3374, 0), 
			new Tile(2945, 3369, 0) };

	@Override
	public boolean activate() {
		
		
		
		
		return FalBankPath.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		
		
		EliteGanos.status = "Going to Falador Bank";
		
		if(Widgets.get(749, 0).getTextureId() != 1209){
			Prayer.toggleQuick(true);
		}
		Walking.newTilePath(toFallyBank).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));

	}

}
