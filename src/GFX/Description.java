package GFX;

public class Description {
	
	public static String[] textCounterTile = new String[9];
	public static String advantages;
	public static String disadvantages;

	
	public static void initTextCounterTile (int counter) {
		switch(counter) {
		case 0 :{ //Albali
			textCounterTile[0]="Albali - As a kid raised on the streets of Meriboge, she knew";
			textCounterTile[1]=" how to take care of herself. Some day in a tavern she";
			textCounterTile[2]=" encountered a rich looking man and quickly seduced him. When";
			textCounterTile[3]=" they were alone, he killed him and stole his treasures.";
			textCounterTile[4]=" Amoung the stolen things there was a book of shadow magic.";
			textCounterTile[5]=" Albali took her time but finally she mastered the art of";
			textCounterTile[6]=" shadows. Now she uses that power to acomplish her selfish";
			textCounterTile[7]=" goals of wellfare.";
			textCounterTile[8]="";
			advantages="+SPELL: Uses the shadow magic to become invisible, therefore ";
			disadvantages="unable to get hit. She gets out of the shadows after killing someone";
			
			break;
		}
		case 1:{ //Funi
			textCounterTile[0]="Funi - Being born in a society of demon mages, she was made";
			textCounterTile[1]=" into studying the magic arts. She quickly discovered her";
			textCounterTile[2]=" immense powers and became one of the best  at her university.";
			textCounterTile[3]=" One day a friend of her took her to the forest and by";
			textCounterTile[4]=" mistake set the woods on fire. From that day on Funi";
			textCounterTile[5]=" is obssesed with fire magic and she refuses learning";
			textCounterTile[6]=" anything else. She even had her memory wiped out just";
			textCounterTile[7]=" so she could know only fire magic.";
			textCounterTile[8]="";
			advantages="SPELL: Sets 2 random, not protected and not occupied";
			disadvantages="squares on fire, everyone passing by the flame cease to exist";
			break;
		}
		case 2:{ //Intan
			textCounterTile[0]="Intan - As a holy crusader, he owed to protect the weak,";
			textCounterTile[1]=" however in his eyes, everyone is weak. Because of that he";
			textCounterTile[2]=" tries to protect everyone. Once upon a time  he tried";
			textCounterTile[3]=" to protect, a loved one. He lost him, and a hand. Now he";
			textCounterTile[4]=" seeks revenge. As he can't hold two things at the same time,";
			textCounterTile[5]=" he has chosen the shield to be  his weapon, so he could still";
			textCounterTile[6]=" protect people. His husband is dead, but noone else will";
			textCounterTile[7]=" die in his presence.";
			textCounterTile[8]="";
			advantages="SPELL: Gives a shield to an ally ";
			disadvantages="-Can't shield himself";
			break;
		}
		case 3:{ //Mira
			textCounterTile[0]="Mira - As far as he knows - the last angel alive. Despite";
			textCounterTile[1]=" their immortality, angels they were not prepared for enemies,";
			textCounterTile[2]=" their pride and gluttony blinded them to the obvious danger.";
			textCounterTile[3]=" They were no match for red dragons. Now he roams the world";
			textCounterTile[4]=" in search of one of his kind, hoping that one day";
			textCounterTile[5]=" they will reclaim their Moon kingdom.";
			textCounterTile[6]="";
			textCounterTile[7]="";
			textCounterTile[8]="";
			advantages="+SPELL: revives an ally";
			disadvantages="";
			break;
		}
		case 4:{ //Polaris
			textCounterTile[0]="Polaris - Perhaps it's true that mother always loves her";
			textCounterTile[1]=" child. Perhaps sometimes a little too much. Perhaps those";
			textCounterTile[2]=" loved kids love eating too much. But Polaris would eat them";
			textCounterTile[3]=" all. Being one of those loved ones, he is able to withstand";
			textCounterTile[4]=" a heavy artillery shot, but having him on your team slows";
			textCounterTile[5]=" you down. Perhaps try rolling him?";
			textCounterTile[6]="";
			textCounterTile[7]="";
			textCounterTile[8]="";
			advantages="+It takes 3 attacks to take him down";
			disadvantages="-He moves only a half of rolled value";
			break;
		}
		case 5:{ //Samaya
			textCounterTile[0]="Samaya - From the young years she was taught the ways";
			textCounterTile[1]=" of diplomacy. Her teacher invoked kindness and a deep love";
			textCounterTile[2]=" of the world in her, therefore she is always the first one";
			textCounterTile[3]=" to help those in need and she believes in human goodness.";
			textCounterTile[4]=" She despises violence and everywhere she goes she finds";
			textCounterTile[5]=" a peaceful approach, that  combined with her calm nature";
			textCounterTile[6]=" results in a powerful weapon that doesn't kill anyone, yet";
			textCounterTile[7]=" can't be destroyed.";
			textCounterTile[8]="";
			advantages="+She can't be killed";
			disadvantages="-She can't kill";
			break;
		}
		case 6:{ //Saph
			textCounterTile[0]="Saph - Backstory unknown. All we know is that he is a total";
			textCounterTile[1]=" drunkard. When sober he's more or less calm and light-hearted,";
			textCounterTile[2]=" but those who know him know that's just a calm before the";
			textCounterTile[3]=" storm. When drunk, he becomes unstoppable berserk, fighting";
			textCounterTile[4]=" everybody that he doesn't seem to like, and he isn't fond of";
			textCounterTile[5]=" anyone at that moment. Likes board games. ";
			textCounterTile[6]="";
			textCounterTile[7]="";
			textCounterTile[8]="";
			advantages="+SPELL: Kills everyone on his path ";
			disadvantages="-Everyone";
			break;
		}
		case 7:{ //Venator
			textCounterTile[0]="Aggitarius - A son of royal family cursed by their envious";
			textCounterTile[1]=" maid, who had romance with the king. He tried to fight it, but";
			textCounterTile[2]=" eventually he surrendered himself to his inner demon. He";
			textCounterTile[3]=" slaughetered everyone in the castle making it his lair. From";
			textCounterTile[4]=" time to time he ventures outside to fullfil his blood craving,";
			textCounterTile[5]=" because brutal killings make him stronger. If he don't kill";
			textCounterTile[6]=" anyone for 2 weeks, then he will die, because of that making";
			textCounterTile[7]=" him unable to do that is preferable way of getting rid of him.";
			textCounterTile[8]="";
			advantages="+Killing gives him additional life (max of 3)";
			disadvantages="-Crowd control spells deal damage";
			break;
		}
	}
	}
	
	public static void init() {
	
	}
}
