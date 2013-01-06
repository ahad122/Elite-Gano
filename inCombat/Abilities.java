package inCombat;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

import sk.action.ActionBar;
import sk.action.book.BookAbility;


public class Abilities extends Node {
	
	
	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return Players.getLocal().isInCombat()  && Players.getLocal().getAnimation()!= -1 &&(BookAbility.DRAGON_BREATH.available() || BookAbility.IMPACT.available() || BookAbility.COMBUST.available() || BookAbility.CHAIN.available() || BookAbility.MOMENTUM.available() || BookAbility.WILD_MAGIC.available() || BookAbility.TSUNAMI.available());
	}

	@Override
	public void execute() {
		if(BookAbility.TSUNAMI.available()){
			ActionBar.useAbility(BookAbility.TSUNAMI);
		}
		
		if(BookAbility.WILD_MAGIC.available()){
			ActionBar.useAbility(BookAbility.WILD_MAGIC);
		}
		if(BookAbility.DRAGON_BREATH.available()){
			ActionBar.useAbility(BookAbility.DRAGON_BREATH);
		}
		
		if(BookAbility.IMPACT.available()){
			ActionBar.useAbility(BookAbility.IMPACT);
		}
		if(BookAbility.COMBUST.available()){
			ActionBar.useAbility(BookAbility.COMBUST);
		}
		
		if(BookAbility.CHAIN.available()){
		ActionBar.useAbility(BookAbility.CHAIN);
		}

	}

}
