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

public class BankVarrock extends Node {
	//Done
	private final static Area VarBankPathArea = new Area(new Tile[] { new Tile(3220, 3437, 0), new Tile(3197, 3436, 0), new Tile(3197, 3431, 0), 
			new Tile(3177, 3431, 0), new Tile(3177, 3427, 0), new Tile(3197, 3427, 0), 
			new Tile(3197, 3421, 0), new Tile(3221, 3421, 0), new Tile(3223, 3421, 0), 
			new Tile(3223, 3437, 0) });
	
	private final static Area GEBankPathArea = new Area(new Tile[] { new Tile(3183, 3473, 0), new Tile(3150, 3473, 0), new Tile(3150, 3446, 0), 
			new Tile(3176, 3446, 0), new Tile(3176, 3447, 0), new Tile(3196, 3447, 0) });
	
	private final static Tile[] toVarBank = new Tile[] { new Tile(3211, 3428, 0), new Tile(3205, 3428, 0), new Tile(3201, 3428, 0), 
			new Tile(3196, 3429, 0), new Tile(3190, 3429, 0), new Tile(3186, 3430, 0), 
			new Tile(3184, 3434, 0) };

	private final static Tile[] toVarBankGE = new Tile[] { new Tile(3167, 3461, 0), new Tile(3172, 3457, 0), new Tile(3176, 3453, 0), 
		new Tile(3182, 3450, 0), new Tile(3185, 3448, 0), new Tile(3185, 3443, 0) };
	
	//public static String status ;
	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return (VarBankPathArea.contains(Players.getLocal().getLocation())|| GEBankPathArea.contains(Players.getLocal().getLocation()));
	}

	@Override
	public void execute() {
		EliteGanos.status = "Going to Varrock Bank";
		
		if(Widgets.get(749, 0).getTextureId() != 1209){
			Prayer.toggleQuick(true);
		}
		
		Walking.newTilePath(toVarBank).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));
		Walking.newTilePath(toVarBankGE).traverse(EnumSet.of(TraversalOption.SPACE_ACTIONS));

	}

}
