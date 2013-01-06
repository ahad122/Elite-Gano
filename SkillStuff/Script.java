package SkillStuff;

import java.awt.Graphics;
import java.awt.Point;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;

@Manifest(authors = { "OneLuckyDuck" }, name = "SkillData Example")
public class Script extends ActiveScript implements PaintListener {

	@Override
	public int loop() {
		System.out.println(SkillData.getTotalExperience());
		return 500;
	}

	@Override
	public void onRepaint(Graphics g) {
		final Point p = Mouse.getLocation();
		g.fillOval(p.x - 3, p.y - 3, 6, 6);

	}
}
