package Main;



import inCombat.Abilities;
import inCombat.Fight;
import inCombat.Heal;
import inCombat.Loot;
import inCombat.Pray;
import inCombat.Renewal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

import sk.action.Ability;
import sk.action.book.magic.Spell;

import ScriptState.State;
import Walking.BankFally;
import Walking.BankVarrock;
import Walking.EMERGENCY;
import Walking.EnterCave;
import Walking.TeleOut;
import Walking.ToFairyRing;
import Walking.ToGanoScreen;
import Walking.ToSecondFairyRing;
import Walking.toCaveDA;
import Walking.toCaveLS;
import Walking.toGanoNormal;
import Walking.toGanoRes;
import Walking.toMulti;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButtonMenuItem;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
//import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButtonMenuItem;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JTextField;

@Manifest(authors = { "Ahad" }, name = "Elite Ganodermics", description = "Kills Ganodermic Beast for Bank", version = 0.01)
public class EliteGanos extends ActiveScript implements PaintListener, MouseListener, MessageListener {

	private Tree jobs = null;

	public static String status = "loading..";
	private final Image img1 = getImage("http://i46.tinypic.com/15pidr4.png");
	private final Image paint = getImage("http://i45.tinypic.com/174mzs.png");
	private final Image hidebutton = getImage("http://i47.tinypic.com/o0ldl.png");
	Rectangle close = new Rectangle(485, 400, 20, 20);
	private Timer runTime = new Timer(0);
	public static int PrayPotsNum;
	private boolean guiWait = true;
	public boolean showPaint = true;
	public static boolean PrayIsEnab = false;
	public static int NumberPrayPots;
	public static boolean RenewIsEnab = false;
	public static int TypeofRenew = 21630;
	public static int numberofrenew;
	public static int TELETAB;
	public static String MethodToCave;
	public static int WHICHDUNGEON;
	public static boolean ENABLEFOOD = false;
	public static int WHICHFOODTOUSE; 
	public static int howmuchfood;
	public static boolean ENABLEFIRSTRUNE = false;
	public static boolean ENABLESECONDRUNE = false;
	public static boolean ENABLETHIRDRUNE = false;
	public static int SPELLUSING = 0;
	public static int KILLS = 0;
	

	GUI gui = new GUI();

	public void onStart() {
		gui.setVisible(true);

		

		if (Integer.parseInt(Widgets.get(320, 89).getText().toString()) < 95) {
			System.out.println("Do not meet the requirements for this script!");
			stop();
		}

	}

	@Override
	public int loop() {
		
		
		if (guiWait == true) {
			do {
				Task.sleep(500);
			} while (guiWait == true);
		}

		if (jobs == null) {

			jobs = new Tree(new Node[] { new Banking(), new toCaveDA(),
					new toCaveLS(), new ToFairyRing(), new ToSecondFairyRing(),
					new EnterCave(), new toGanoNormal(), new toGanoRes(),
					new toMulti(), new Fight(),new Loot(),new EMERGENCY(),new Pray(),new Renewal(), new Heal(),new Loot(), 
					new Abilities(), new BankFally(),
					new BankVarrock(), new State(), new TeleOut()});
		}
		final Node job = jobs.state();
		if (job != null) {
			jobs.set(job);
			getContainer().submit(job);
			job.join();
			return 10;
		}
		return Random.nextInt(200, 300);
	}

	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;

		}
	}

	public void onStop() {
		System.out.println("Total Time: " + runTime.toElapsedString());
		gui.setVisible(false);
		
	}

	@Override
	public void onRepaint(Graphics g) {

		// int x = Mouse.getX();
		// int y = Mouse.getY();
		if(showPaint){g.drawImage(paint, 0, 215, null);
		g.drawImage(hidebutton,485,400,null);
		// g.drawImage(img1, x, y, null);
	
		g.drawString(runTime.toElapsedString(), 215, 435);
		g.drawString(status, 398, 475);
		g.drawString(Integer.toString(KILLS), 122, 478);
		
		} else if (!showPaint){
			g.drawImage(hidebutton,485,400,null);
		}
		Point p = new Point(Mouse.getX(), Mouse.getY());

		Color Purple = new Color(0, 0, 0);
		Color LowPurple = new Color(6, 19, 157);
		Color Indigo = new Color(185, 3, 3);

		((Graphics2D) g).setRenderingHints(new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));

		Graphics2D spinG = (Graphics2D) g.create();
		Graphics2D spinGRev = (Graphics2D) g.create();
		Graphics2D g2d = (Graphics2D) g;
		spinGRev.setColor(Purple);
		spinGRev.rotate(System.currentTimeMillis() % 2000d / 2000d * (-360d)
				* 2 * Math.PI / 180.0, p.x, p.y);
		spinGRev.setStroke(new BasicStroke(1));
		spinGRev.drawLine(p.x - 8, p.y, p.x + 8, p.y);
		spinGRev.drawLine(p.x, p.y - 8, p.x, p.y + 8);
		spinG.setColor(LowPurple);
		spinG.rotate(System.currentTimeMillis() % 2000d / 2000d * (360d) * 2
				* Math.PI / 180.0, p.x, p.y);
		spinG.drawLine(p.x - 6, p.y, p.x + 6, p.y);
		spinG.drawLine(p.x, p.y - 6, p.x, p.y + 6);
		g2d.setColor(Indigo);
		g2d.drawOval(p.x - 10, p.y - 10, 20, 20);

	}
	
	public void mouseClicked(MouseEvent e) {
	  Point a = e.getPoint();
	  Rectangle close = new Rectangle(495, 409, 100, 100);
	  if (close.contains(a)) {
	  showPaint = !showPaint;
	  }

	 }
	
	
	public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

     class GUI extends JFrame {
    	private JTextField FirstRuneAmount;
    	private JTextField SecondRuneAmount;
    	private JTextField ThirdRuneAmount;

    	/**
    	 * Launch the application.
    	 */
    	public void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					GUI frame = new GUI();
    					frame.setVisible(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    	}

    	/**
    	 * Create the frame.
    	 */
    	public GUI() {
    		
    		
    		setBackground(Color.RED);
    		setTitle("Elite Ganodermics");
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		setBounds(100, 100, 453, 428);
    		
    		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    		tabbedPane.setToolTipText("Loot");
    		
    		JButton StartButton = new JButton("Start");
    		StartButton.setFont(new Font("Castellar", Font.BOLD, 11));
    		
    		
    		JButton ExitButton = new JButton("Exit");
    		ExitButton.setFont(new Font("Castellar", Font.BOLD, 11));
    		ExitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					gui.setVisible(false);
					stop();

				}
			});
    		
    		JLabel lblGanoelites = new JLabel("GANO-ELITES");
    		lblGanoelites.setForeground(Color.RED);
    		lblGanoelites.setFont(new Font("Ravie", Font.PLAIN, 27));
    		GroupLayout groupLayout = new GroupLayout(getContentPane());
    		groupLayout.setHorizontalGroup(
    			groupLayout.createParallelGroup(Alignment.LEADING)
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    						.addGroup(groupLayout.createSequentialGroup()
    							.addGap(90)
    							.addComponent(StartButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
    							.addGap(65)
    							.addComponent(ExitButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
    						.addGroup(groupLayout.createSequentialGroup()
    							.addGap(81)
    							.addComponent(lblGanoelites))
    						.addGroup(groupLayout.createSequentialGroup()
    							.addGap(39)
    							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)))
    					.addContainerGap(68, Short.MAX_VALUE))
    		);
    		groupLayout.setVerticalGroup(
    			groupLayout.createParallelGroup(Alignment.LEADING)
    				.addGroup(groupLayout.createSequentialGroup()
    					.addContainerGap()
    					.addComponent(lblGanoelites)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
    					.addGap(38)
    					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(StartButton)
    						.addComponent(ExitButton))
    					.addContainerGap(23, Short.MAX_VALUE))
    		);
    		
    		JList list = new JList();
    		
    		JPanel panel = new JPanel();
    		tabbedPane.addTab("Travelling", null, panel, null);
    		
    		JLabel lblNewLabel = new JLabel("Method to caves");
    		lblNewLabel.setFont(new Font("Ravie", Font.BOLD, 16));
    		
    		final JComboBox comboBox = new JComboBox();
    		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Al-Kharid Lodestone", "Fairy Ring(BIP)"}));
    		
    		JLabel lblMethodToBank = new JLabel("Method to Bank");
    		lblMethodToBank.setFont(new Font("Ravie", Font.BOLD, 16));
    		
    		final JComboBox comboBox_1 = new JComboBox();
    		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Varrock Tab", "Falador Tab"}));
    		
    		JLabel lblDungeon = new JLabel("Dungeon");
    		lblDungeon.setFont(new Font("Ravie", Font.BOLD, 16));
    		
    		final JComboBox comboBox_4 = new JComboBox();
    		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Resource Dungeon", "Multi-Combat"}));
    		GroupLayout gl_panel = new GroupLayout(panel);
    		gl_panel.setHorizontalGroup(
    			gl_panel.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel.createSequentialGroup()
    					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
    						.addGroup(gl_panel.createSequentialGroup()
    							.addGap(62)
    							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
    								.addComponent(lblMethodToBank, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    						.addGroup(gl_panel.createSequentialGroup()
    							.addGap(81)
    							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
    						.addGroup(gl_panel.createSequentialGroup()
    							.addGap(93)
    							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
    								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
    								.addGroup(gl_panel.createSequentialGroup()
    									.addGap(10)
    									.addComponent(lblDungeon))
    								.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
    							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)))
    					.addGap(77))
    		);
    		gl_panel.setVerticalGroup(
    			gl_panel.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel.createSequentialGroup()
    					.addComponent(lblNewLabel)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addGap(11)
    					.addComponent(lblMethodToBank)
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addComponent(lblDungeon)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addContainerGap(42, Short.MAX_VALUE))
    		);
    		panel.setLayout(gl_panel);
    		
    		JPanel panel_1 = new JPanel();
    		tabbedPane.addTab("Banking", null, panel_1, null);
    		
    		final JComboBox comboBox_2 = new JComboBox();
    		comboBox_2.setEnabled(false);
    		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"}));
    		
    		final JCheckBox Praycheck = new JCheckBox("Enable Prayer");
    		
    		
    		final JComboBox comboBox_3 = new JComboBox(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"});
    		comboBox_3.setEnabled(false);
    		
    		final JCheckBox chckbxEnableRenewals = new JCheckBox("Enable Renewals");
    		
    		
    		final JCheckBox PrayerFlask = new JCheckBox("Flasks?");
    		PrayerFlask.setEnabled(false);
    		
    		final JComboBox foodType = new JComboBox();
    		foodType.setEnabled(false);
    		foodType.setModel(new DefaultComboBoxModel(new String[] {"Monkfish", "Shark", "Manta Ray", "Rocktail"}));
    		
    		final JCheckBox renewalFlasks = new JCheckBox("Flasks?");
    		renewalFlasks.setEnabled(false);
    		
    		final JCheckBox cbEnablefood = new JCheckBox("Enable Food");
    		
    		
    		final JComboBox foodNumber = new JComboBox();
    		foodNumber.setEnabled(false);
    		foodNumber.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
    		
    		JLabel lblRunes = new JLabel("Runes:");
    		lblRunes.setFont(new Font("Ravie", Font.BOLD, 15));
    		
    		final JComboBox FirstRune = new JComboBox();
    		FirstRune.setModel(new DefaultComboBoxModel(new String[] {"None", "Air Rune", "Mind Rune", "Water Rune", "Earth Rune", "Fire Rune", "Body Rune", "Cosmic Rune", "Law Rune", "Death Rune", "Astral Rune", "Blood Rune", "Soul Rune", "Armadyl Rune"}));
    		
    		FirstRuneAmount = new JTextField();
    		FirstRuneAmount.setColumns(10);
    		
    		final JComboBox SecondRune = new JComboBox();
    		SecondRune.setModel(new DefaultComboBoxModel(new String[] {"None", "Air Rune", "Mind Rune", "Water Rune", "Earth Rune", "Fire Rune", "Body Rune", "Cosmic Rune", "Law Rune", "Death Rune", "Astral Rune", "Blood Rune", "Soul Rune", "Armadyl Rune"}));
    		
    		SecondRuneAmount = new JTextField();
    		SecondRuneAmount.setColumns(10);
    		
    		final JComboBox ThirdRune = new JComboBox();
    		ThirdRune.setModel(new DefaultComboBoxModel(new String[] {"None", "Air Rune", "Mind Rune", "Water Rune", "Earth Rune", "Fire Rune", "Body Rune", "Cosmic Rune", "Law Rune", "Death Rune", "Astral Rune", "Blood Rune", "Soul Rune", "Armadyl Rune"}));
    		
    		ThirdRuneAmount = new JTextField();
    		ThirdRuneAmount.setColumns(10);
    		
    		JLabel lblHowMany = new JLabel("How many?");
    		
    		JLabel label = new JLabel("How many?");
    		
    		JLabel label_1 = new JLabel("How many?");
    		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
    		gl_panel_1.setHorizontalGroup(
    			gl_panel_1.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_1.createSequentialGroup()
    					.addContainerGap()
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
    						.addComponent(chckbxEnableRenewals)
    						.addComponent(Praycheck)
    						.addComponent(cbEnablefood))
    					.addGap(18)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
    						.addComponent(foodType, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    						.addComponent(comboBox_3, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    						.addComponent(comboBox_2, Alignment.LEADING, 0, 82, Short.MAX_VALUE))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
    						.addComponent(PrayerFlask)
    						.addComponent(renewalFlasks)
    						.addComponent(foodNumber, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
    					.addContainerGap())
    				.addGroup(gl_panel_1.createSequentialGroup()
    					.addGap(108)
    					.addComponent(lblRunes)
    					.addContainerGap(150, Short.MAX_VALUE))
    				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
    					.addContainerGap()
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
    						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
    							.addComponent(SecondRune, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    							.addComponent(FirstRune, Alignment.LEADING, 0, 81, Short.MAX_VALUE))
    						.addComponent(ThirdRune, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addGap(18)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
    						.addComponent(lblHowMany)
    						.addComponent(label, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
    						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
    						.addGroup(gl_panel_1.createSequentialGroup()
    							.addComponent(ThirdRuneAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
    							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
    								.addComponent(FirstRuneAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    								.addComponent(SecondRuneAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    							.addGap(69))))
    		);
    		gl_panel_1.setVerticalGroup(
    			gl_panel_1.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_1.createSequentialGroup()
    					.addContainerGap()
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
    						.addComponent(Praycheck)
    						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(PrayerFlask))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
    						.addComponent(chckbxEnableRenewals)
    						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(renewalFlasks))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
    						.addComponent(foodType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(cbEnablefood)
    						.addComponent(foodNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addComponent(lblRunes)
    					.addGap(15)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
    						.addComponent(FirstRune, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(FirstRuneAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblHowMany))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
    						.addComponent(SecondRune, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(SecondRuneAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(label))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
    						.addComponent(ThirdRune, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(ThirdRuneAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						.addComponent(label_1))
    					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    		);
    		panel_1.setLayout(gl_panel_1);
    		
    		
    		JPanel panel_2 = new JPanel();
    		tabbedPane.addTab("Looting", null, panel_2, null);
    		
    		JLabel lblWhatToLoot = new JLabel("Looting Options");
    		lblWhatToLoot.setFont(new Font("Ravie", Font.BOLD, 14));
    		
    		final JCheckBox cbFlake = new JCheckBox("Ganodermic Flake");
    		
    		final JCheckBox cbGold = new JCheckBox("Gold Charm");
    		
    		final JCheckBox cbGreen = new JCheckBox("Green Charm");
    		
    		final JCheckBox cbCrimson = new JCheckBox("Crimson Charm");
    		
    		final JCheckBox cbBlue = new JCheckBox("Blue Charm");
    		
    		final JCheckBox cbMagicSeed = new JCheckBox("Magic Seed");
    		
    		final JCheckBox cbTorstol = new JCheckBox("Torstol Seed");
    		
    		final JCheckBox cbYew = new JCheckBox("Yew Seed");
    		
    		final JCheckBox cbPapaya = new JCheckBox("Papaya Tree Seed");
    		
    		final JCheckBox cbPalm = new JCheckBox("Palm Tree Seed");
    		
    		final JCheckBox cbEffigy = new JCheckBox("Effigy");
    		
    		final JCheckBox cbPolyporeSpore = new JCheckBox("Polypore spore");
    		
    		final JCheckBox cbRuneBar = new JCheckBox("Rune bar");
    		
    		final JCheckBox cbAdamantBar = new JCheckBox("Adamant Bar");
    		
    		final JCheckBox cbCleanTorstol = new JCheckBox("Clean Torstol");
    		
    		final JCheckBox cbYewLogs = new JCheckBox("Yew Logs");
    		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
    		gl_panel_2.setHorizontalGroup(
    			gl_panel_2.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_2.createSequentialGroup()
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
    						.addGroup(gl_panel_2.createSequentialGroup()
    							.addGap(6)
    							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
    								.addComponent(cbFlake)
    								.addComponent(cbGold)
    								.addComponent(cbGreen)
    								.addComponent(cbCrimson)
    								.addComponent(cbBlue)
    								.addComponent(cbYew)
    								.addComponent(cbTorstol)
    								.addComponent(cbCleanTorstol))
    							.addGap(83)
    							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
    								.addComponent(cbYewLogs)
    								.addComponent(cbAdamantBar)
    								.addComponent(cbRuneBar)
    								.addComponent(cbPolyporeSpore)
    								.addComponent(cbEffigy)
    								.addComponent(cbPalm)
    								.addComponent(cbPapaya)
    								.addComponent(cbMagicSeed)))
    						.addGroup(gl_panel_2.createSequentialGroup()
    							.addGap(84)
    							.addComponent(lblWhatToLoot)))
    					.addContainerGap(20, Short.MAX_VALUE))
    		);
    		gl_panel_2.setVerticalGroup(
    			gl_panel_2.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_2.createSequentialGroup()
    					.addComponent(lblWhatToLoot)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbFlake)
    						.addComponent(cbMagicSeed))
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbGold)
    						.addComponent(cbPapaya))
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbGreen)
    						.addComponent(cbPalm))
    					.addGap(3)
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbCrimson)
    						.addComponent(cbEffigy))
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbBlue)
    						.addComponent(cbPolyporeSpore))
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbYew)
    						.addComponent(cbRuneBar))
    					.addGap(3)
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbTorstol)
    						.addComponent(cbAdamantBar))
    					.addGap(3)
    					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
    						.addComponent(cbCleanTorstol)
    						.addComponent(cbYewLogs)))
    		);
    		panel_2.setLayout(gl_panel_2);
    		
    		JPanel panel_3 = new JPanel();
    		tabbedPane.addTab("Others", null, panel_3, null);
    		
    		JComboBox whenToBank = new JComboBox();
    		whenToBank.setModel(new DefaultComboBoxModel(new String[] {"Out of food", "Out of Prayer pots", "Out of Runes"}));
    		
    		JLabel lblNewLabel_1 = new JLabel("When to bank?");
    		lblNewLabel_1.setFont(new Font("Ravie", Font.PLAIN, 14));
    		
    		JComboBox comboBox_5 = new JComboBox();
    		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Pray Magic", "Quick Prayer", "Soulsplit"}));
    		JLabel lblNewLabel_2 = new JLabel("Spell");
    		lblNewLabel_2.setFont(new Font("Ravie", Font.PLAIN, 14));
    		
    		
    		
    		JLabel lblPrayer = new JLabel("Prayer");
    		lblPrayer.setFont(new Font("Ravie", Font.PLAIN, 15));
    		
    		JLabel SpellLabel = new JLabel("Spell");
    		SpellLabel.setFont(new Font("Ravie", Font.PLAIN, 14));
    		
    		final JComboBox comboBox_6 = new JComboBox();
    		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"Fire Surge", "Fire Wave", "Polypore Spell"}));
    		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
    		gl_panel_3.setHorizontalGroup(
    			gl_panel_3.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_3.createSequentialGroup()
    					.addContainerGap()
    					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
    						.addGroup(gl_panel_3.createSequentialGroup()
    							.addComponent(lblPrayer)
    							.addContainerGap(247, Short.MAX_VALUE))
    						.addGroup(gl_panel_3.createSequentialGroup()
    							.addComponent(comboBox_5, 0, 115, Short.MAX_VALUE)
    							.addGap(206))
    						.addGroup(gl_panel_3.createSequentialGroup()
    							.addComponent(lblNewLabel_1)
    							.addContainerGap(182, Short.MAX_VALUE))
    						.addGroup(gl_panel_3.createSequentialGroup()
    							.addComponent(whenToBank, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    							.addContainerGap(206, Short.MAX_VALUE))
    						.addGroup(gl_panel_3.createSequentialGroup()
    							.addComponent(SpellLabel)
    							.addContainerGap(277, Short.MAX_VALUE))
    						.addGroup(gl_panel_3.createSequentialGroup()
    							.addGap(10)
    							.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
    							.addContainerGap())))
    		);
    		gl_panel_3.setVerticalGroup(
    			gl_panel_3.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_3.createSequentialGroup()
    					.addContainerGap()
    					.addComponent(lblNewLabel_1)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(whenToBank, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addGap(18)
    					.addComponent(lblPrayer)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addGap(18)
    					.addComponent(SpellLabel)
    					.addPreferredGap(ComponentPlacement.RELATED)
    					.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addContainerGap(44, Short.MAX_VALUE))
    		);
    		panel_3.setLayout(gl_panel_3);
    		getContentPane().setLayout(groupLayout);
    		panel_3.setLayout(gl_panel_3);
    		getContentPane().setLayout(groupLayout);
    		Praycheck.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    comboBox_2.setEnabled(Praycheck.isSelected());
    PrayerFlask.setEnabled(Praycheck.isSelected());
    			}
    		});
    		cbEnablefood.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				foodType.setEnabled(cbEnablefood.isSelected());
    				foodNumber.setEnabled(cbEnablefood.isSelected());
    			}
    		});
    		chckbxEnableRenewals.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				comboBox_3.setEnabled(chckbxEnableRenewals.isSelected());
    				renewalFlasks.setEnabled(chckbxEnableRenewals.isSelected());
    				
    			}
    		});
    		StartButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!FirstRune.getSelectedItem().equals("None")){
						ENABLEFIRSTRUNE = true;
						Banking.amountfirstrune = Integer.parseInt(FirstRuneAmount.getText());
						
						if(FirstRune.getSelectedItem().equals("Air Rune")){
						Banking.FirstRuneType = Banking.AIR_RUNE;
						} else if(FirstRune.getSelectedItem().equals("Mind Rune")){
							Banking.FirstRuneType = Banking.MIND_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Water Rune")){
								Banking.FirstRuneType = Banking.WATER_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Earth Rune")){
								Banking.FirstRuneType = Banking.EARTH_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Fire Rune")){
								Banking.FirstRuneType = Banking.FIRE_RUNE;
							}else if(FirstRune.getSelectedItem().equals("Cosmic Rune")){
								Banking.FirstRuneType = Banking.COSMIC_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Law Rune")){
								Banking.FirstRuneType = Banking.LAW_RUNE;
							} else  if(FirstRune.getSelectedItem().equals("Death Rune")){
								Banking.FirstRuneType = Banking.DEATH_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Astral Rune")){
								Banking.FirstRuneType = Banking.ASTRAL_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Blood Rune")){
								Banking.FirstRuneType = Banking.BLOOD_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Soul Rune")){
								Banking.FirstRuneType = Banking.SOUL_RUNE;
							} else if(FirstRune.getSelectedItem().equals("Armadyl Rune")){
								Banking.FirstRuneType = Banking.ARMADYL_RUNE;
							}
						
					} 
					
					if(!SecondRune.getSelectedItem().equals("None")){
						ENABLESECONDRUNE = true;
						Banking.amountsecondrune = Integer.parseInt(SecondRuneAmount.getText());
						
						if(SecondRune.getSelectedItem().equals("Air Rune")){
						Banking.SecondRuneType = Banking.AIR_RUNE;
						} else if(SecondRune.getSelectedItem().equals("Mind Rune")){
							Banking.SecondRuneType = Banking.MIND_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Water Rune")){
								Banking.SecondRuneType = Banking.WATER_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Earth Rune")){
								Banking.SecondRuneType = Banking.EARTH_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Fire Rune")){
								Banking.SecondRuneType = Banking.FIRE_RUNE;
							}else if(SecondRune.getSelectedItem().equals("Cosmic Rune")){
								Banking.SecondRuneType = Banking.COSMIC_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Law Rune")){
								Banking.SecondRuneType = Banking.LAW_RUNE;
							} else  if(SecondRune.getSelectedItem().equals("Death Rune")){
								Banking.SecondRuneType = Banking.DEATH_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Astral Rune")){
								Banking.SecondRuneType = Banking.ASTRAL_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Blood Rune")){
								Banking.SecondRuneType = Banking.BLOOD_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Soul Rune")){
								Banking.SecondRuneType = Banking.SOUL_RUNE;
							} else if(SecondRune.getSelectedItem().equals("Armadyl Rune")){
								Banking.SecondRuneType = Banking.ARMADYL_RUNE;
							}
						
					} 
					
					if(!ThirdRune.getSelectedItem().equals("None")){
						ENABLETHIRDRUNE = true;
						Banking.amountthirdrune = Integer.parseInt(ThirdRuneAmount.getText());
						
						if(ThirdRune.getSelectedItem().equals("Air Rune")){
						Banking.ThirdRuneType = Banking.AIR_RUNE;
						} else if(ThirdRune.getSelectedItem().equals("Mind Rune")){
							Banking.ThirdRuneType = Banking.MIND_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Water Rune")){
								Banking.ThirdRuneType = Banking.WATER_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Earth Rune")){
								Banking.ThirdRuneType = Banking.EARTH_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Fire Rune")){
								Banking.ThirdRuneType = Banking.FIRE_RUNE;
							}else if(ThirdRune.getSelectedItem().equals("Cosmic Rune")){
								Banking.ThirdRuneType = Banking.COSMIC_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Law Rune")){
								Banking.ThirdRuneType = Banking.LAW_RUNE;
							} else  if(ThirdRune.getSelectedItem().equals("Death Rune")){
								Banking.ThirdRuneType = Banking.DEATH_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Astral Rune")){
								Banking.ThirdRuneType = Banking.ASTRAL_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Blood Rune")){
								Banking.ThirdRuneType = Banking.BLOOD_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Soul Rune")){
								Banking.ThirdRuneType = Banking.SOUL_RUNE;
							} else if(ThirdRune.getSelectedItem().equals("Armadyl Rune")){
								Banking.ThirdRuneType = Banking.ARMADYL_RUNE;
							}
						
					} 
					
					if(comboBox_6.getSelectedItem().equals("Fire Surge")){
						SPELLUSING = 1;
					} else if(comboBox_6.getSelectedItem().equals("Fire Wave")){
						SPELLUSING = 2;
					}
					
					if(cbFlake.isSelected()){
						Loot.pickFlake = true;
					Loot.whattoloot.add(Loot.GANO_FLAKE);
					}
					if(cbGold.isSelected()){
						Loot.pickGold = true;
						Loot.whattoloot.add(Loot.GOLD_CHARM);
					}
					if(cbGreen.isSelected()){
						Loot.pickGreen = true;
						Loot.whattoloot.add(Loot.GREEN_CHARM);
					}
					if (cbCrimson.isSelected()){
						Loot.pickCrim = true;
						Loot.whattoloot.add(Loot.CRIMSON_CHARM);
					}
					if(cbBlue.isSelected()){
						Loot.pickBlue = true;
						Loot.whattoloot.add(Loot.BLUE_CHARM);
					}
					
					if(cbYew.isSelected()){
						Loot.pickYewSeed = true;
						Loot.whattoloot.add(Loot.YEW_SEED);
					}
					
					if(cbTorstol.isSelected()){
						Loot.pickTorstolSeed = true;
						Loot.whattoloot.add(Loot.TORSTOL_SEED);
					}
					if(cbCleanTorstol.isSelected()){
						Loot.pickCleanTorstol = true;
						Loot.whattoloot.add(Loot.CLEAN_TORSTOL);
					}
					if(cbMagicSeed.isSelected()){
						Loot.pickMagic = true;
						Loot.whattoloot.add(Loot.MAGIC_SEED);
					}
					if(cbPapaya.isSelected()){
						Loot.pickPapSeed = true;
						Loot.whattoloot.add(Loot.PAP_SEED);
					}
					if(cbPalm.isSelected()){
						Loot.pickPalm = true;
						Loot.whattoloot.add(Loot.PALM_SEED);
						
					}
					
					if(cbEffigy.isSelected()){
						Loot.pickEff= true;
						Loot.whattoloot.add(Loot.EFFIGY);
					}
					if(cbPolyporeSpore.isSelected()){
						Loot.pickPolypore = true;
						Loot.whattoloot.add(Loot.POLYPORE_SPORE);
					}
					if(cbRuneBar.isSelected()){
						Loot.pickRuneBar = true;
						Loot.whattoloot.add(Loot.RUNE_BAR);
					}
					if(cbAdamantBar.isSelected()){
						Loot.pickAdBar = true;
						Loot.whattoloot.add(Loot.ADAMANT_BAR);
					}
					if(cbYewLogs.isSelected()){
						Loot.pickYewLog = true;
						Loot.whattoloot.add(Loot.YEW_LOG);
					}
					if (comboBox_1.getSelectedItem().equals("Varrock Tab")) {
						TELETAB = Banking.VARROCK_TAB;
					} else if (comboBox_1.getSelectedItem().equals(
							"Falador Tab")) {
						TELETAB = Banking.FALA_TAB;
					}
					
					if(chckbxEnableRenewals.isSelected()){
						RenewIsEnab = true;
						numberofrenew= Integer.parseInt(comboBox_3.getSelectedItem().toString());
						
						if(renewalFlasks.isSelected()){
							TypeofRenew = 23609;
						}
					}

					if (Praycheck.isSelected()) {

						PrayIsEnab = true;
						NumberPrayPots = Integer.parseInt(comboBox_2
								.getSelectedItem().toString());
						
						if(PrayerFlask.isSelected()){
							Banking.WHICHPRAYTYPE = 23243;
							
						}
					} else if (!Praycheck.isSelected()) {
						NumberPrayPots = 0;
						
					}
					if (comboBox.getSelectedItem()
							.equals("Al-Kharid Lodestone")) {
						MethodToCave = "Al-Kharid Lodestone";
					} else if (comboBox.getSelectedItem().equals(
							"Fairy Ring(BIP)")) {
						MethodToCave = "Fairy Ring(BIP)";
					}
					
					if(comboBox_4.getSelectedItem().equals("Normal")){
						WHICHDUNGEON = 1;
					} else if(comboBox_4.getSelectedItem().equals("Resource Dungeon")){
						WHICHDUNGEON = 2;
					
					}
					else if (comboBox_4.getSelectedItem().equals("Multi-Combat")){
						WHICHDUNGEON = 3;
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					if(cbEnablefood.isSelected()){
						ENABLEFOOD = true;
						 howmuchfood = Integer.parseInt(foodNumber.getSelectedItem().toString());
						 
						 if(foodType.getSelectedItem().equals("Monkfish")){
							 WHICHFOODTOUSE = Banking.MONKFISH;
						 } else if(foodType.getSelectedItem().equals("Shark")){
							 WHICHFOODTOUSE = Banking.SHARK;
						 } else if(foodType.getSelectedItem().equals("Manta Ray")){
							 WHICHFOODTOUSE = Banking.MANTARAY;
						 } else if(foodType.getSelectedItem().equals("Rocktail")){
							 WHICHFOODTOUSE = Banking.ROCKTAIL;
						 }
					}

					guiWait = false;
					dispose();
				}
			});
    	}
    }

	@Override
	public void messageReceived(MessageEvent e) {
		if(e.getMessage().toLowerCase().contains("you drink some of your prayer renewal potion.")){
			Fight.useRenewals = false;
		}
		
		if(e.getMessage().toLowerCase().contains("your prayer renewal will run out in 30 seconds.")){
			Fight.useRenewals = true;
		}
		
		
		
	}



}
