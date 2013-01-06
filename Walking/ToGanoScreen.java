package Walking;

import java.util.EnumSet;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.Path.TraversalOption;

public class ToGanoScreen extends Node {
	
private static final int BEAST = 14696;
	
	private final static Area GANOAREA = new Area(new Tile[] {
			new Tile(4200, 3000, 0), new Tile(4200, 6000,0),
			new Tile(4900, 6000, 0), new Tile(4900, 3000, 0)

	});
	private final static Tile[] lookingforgano = new Tile[]{
		new Tile(4652,5392,0), new Tile(4649,5395,0), new Tile(4649,5400,0), new Tile(4648,5403,0), new Tile(4648,5407,0), new Tile(4648,5411,0), new Tile(4647,5416,0), new Tile(4646,5420,0), new Tile(4650,5424,0), new Tile(4650,5431,0), new Tile(4648,5435,0), new Tile(4648,5439,0), new Tile(4648,5443,0), new Tile(4648,5447,0), new Tile(4647,5450,0), new Tile(4647,5455,0), new Tile(4651,5458,0), new Tile(4652,5462,0), new Tile(4654,5465,0), new Tile(4655,5469,0), new Tile(4654,5473,0)
	};
	

	@Override
	public boolean activate() {
		NPC beast = NPCs.getNearest(BEAST);
		return !beast.isOnScreen() && GANOAREA.contains(Players.getLocal().getLocation())   ;
	}

	@Override
	public void execute() {
		if(Widgets.get(749, 0).getTextureId() != 1209){
			Prayer.toggleQuick(true);
		}
		
		Walking.newTilePath(lookingforgano).traverse(
				EnumSet.of(TraversalOption.SPACE_ACTIONS));
	}

}
