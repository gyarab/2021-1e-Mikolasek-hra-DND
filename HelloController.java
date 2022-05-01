package com.example.dnd2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Random;



public class HelloController {
    Random rand = new Random();


    int activeEncounter = 0;

    // tenhle int si pamatuje počet zabitých nepřatel, zobrazí se při smrti
    int fightsWon = 0;

    //pri některých zápletkách může hráč zvýšení poškození až +30
    int damageBoost = 0;

    //take muze ziskat oslabení, takže bude naopak dostávat od nepŕátel více poškození
    int weakness = 0;

    //tenhle int se používá na generování náhodných čísel pro házení kostkou při souboji
    int diceRoll = 0;

    int bossTemp = 0;

    //zdraví nepřátel, hráč vyhraje souboj, pokud zdraví nepřitele dosáhne nuly, nebo pokud na kostce hodí 20
    int enemyHealth = 200;

    //zdraví hráče, pokud dosáhne nuly, hra končí
    int health = 200;

    int encounterNumber = 0;

    // tenhle string rika duvod smrti hrace
    String deathCause = "DUVODSMRTI";

    //textové pole, kde se zobrazuje příběh
    @FXML
    private TextArea textArea;

    //textové pole, kam hráč píše svojí odpověď
    @FXML
    private TextField textField;

    //tlačítko pro ukončení hry
    @FXML
    private Button quitButton;

    //tlačítko pro restartování hry při smrti hráče
    @FXML
    private Button tryAgainButton;

    //tlačítko pro začátek hry
    @FXML
    private Button newGameButton;

    //jméno hry
    @FXML
    private Label gameLabel;

    //tlačítko pro hození kostky při souboji
    @FXML
    private Button rollButton;

    @FXML
    protected void onNewGame() {
        gameStart();
        quitButton.setVisible(false);
        newGameButton.setVisible(false);
        textField.setVisible(true);
        textArea.setVisible(true);
        gameLabel.setVisible(false);
    }

    /*
    *při kliknutí na tlačítko Roll! se zobrazí kolik hráčovi padlo od 1 do 20 a podle toho dá nepříteli poškození.
    * Když padne vysoké číslo, hráč dá nepřiteli hodně poškození a nepřítel dá málo poškození hráčovi a naopak.
    */

    @FXML
    protected void onRoll() {
        diceRoll = rand.nextInt(20);
        bossTemp++;
        if (fightsWon == 0) {fight1();} else
        if (fightsWon == 1) {fight2();} else
        if (fightsWon == 2) {fight3();}
    }

    //tlačítko Try again má stejnou funkci jako tlačítko New game, zobrazí se při smrti hráče aby mohl rovnou začít od začátku
    @FXML
    protected void onTryAgain() {
        weakness = 0;
        activeEncounter = 0;
        fightsWon = 0;
        damageBoost = 0;
        bossTemp = 0;
        encounterNumber = 0;
        health = 200;
        gameStart();
        quitButton.setVisible(false);
        newGameButton.setVisible(false);
        textField.setVisible(true);
        textArea.setVisible(true);
        gameLabel.setVisible(false);
        tryAgainButton.setVisible(false);
    }

    //tlačítko Quit ukončí program
    @FXML
    protected void onQuit() {
        Platform.exit();
    }

    //když hráč zadá buď a nebo b do textového pole a stiskne enter
    @FXML
    protected void onEnter() {
        String answer = textField.getText();

        if (answer.contains("a")) {
            textField.clear();
            encounterNumber++;
            if (encounterNumber == 1) {
                encounter1Decider();} else
            if (encounterNumber == 2) {
                if (activeEncounter == 0) encounter1AResultA();
                if (activeEncounter == 1) encounter1BResultA();
                if (activeEncounter == 2) encounter1CResultA();
                if (activeEncounter == 3) encounter1DResultA();
            } else
            if (encounterNumber == 3) {fight1();} else
            if (encounterNumber == 4) {
                encounter2Decider();} else
            if (encounterNumber == 5) {
                if (activeEncounter == 0) encounter2AResultA();
                if (activeEncounter == 1) encounter2BResultA();
                if (activeEncounter == 2) encounter2CResultA();
                if (activeEncounter == 3) encounter2DResultA();
            } else
            if (encounterNumber == 6) {fight2();} else
            if (encounterNumber == 7) {
                encounter3Decider();} else
            if (encounterNumber == 8) {
                if (activeEncounter == 0) encounter3AResultA();
                if (activeEncounter == 1) encounter3BResultA();
                if (activeEncounter == 2) encounter3CResultA();
                if (activeEncounter == 3) encounter3DResultA();
            } else
            if (encounterNumber == 9) {fight3();}

        } else if (answer.contains("b")) {
            encounterNumber++;
            textField.clear();

            if (encounterNumber == 1) {
                deathCause = "you decided to stay in bed :(";
                gameOver();
            } else
            if (encounterNumber == 2) {
                if (activeEncounter == 0) encounter1AResultB();
                if (activeEncounter == 1) encounter1BResultB();
                if (activeEncounter == 2) encounter1CResultB();
                if (activeEncounter == 3) encounter1DResultB();
            } else
            if (encounterNumber == 3) {
                textField.clear();} else
            if (encounterNumber == 4) {
                textField.clear();} else
            if (encounterNumber == 5) {
                if (activeEncounter == 0) encounter2AResultB();
                if (activeEncounter == 1) encounter2BResultB();
                if (activeEncounter == 2) encounter2CResultB();
                if (activeEncounter == 3) encounter2DResultB();
            } else
            if (encounterNumber == 6) {
                textField.clear();} else
            if (encounterNumber == 7) {
                textField.clear();} else
            if (encounterNumber == 8) {
                if (activeEncounter == 0) encounter3AResultB();
                if (activeEncounter == 1) encounter3BResultB();
                if (activeEncounter == 2) encounter3CResultB();
                if (activeEncounter == 3) encounter3DResultB();
            } else
            if (encounterNumber == 9) {
                textField.clear();}

        } else {
            textField.clear();}
    }

    private void gameStart() {
        textArea.setText("""
                you are on a noble quest to find your dream knight, located in rose castle.
                But to get there, you must defeat the guards in the town and in the city, as well as the evil wizard,
                that guards rose castle.
                Are you ready to embark on your epic adventure?

                [a]yes [b]no"""
        );
    }

    //pokud hrá
    private void gameOver() {
        textArea.setText("GAME OVER!"
                         + "\n" + deathCause
                         + "\n" + "Enemies killed: " + fightsWon);
        textField.setVisible(false);
        quitButton.setVisible(true);
        tryAgainButton.setVisible(true);
    }

    private void encounter1Decider() {
        Random rand = new Random();
        int d5 = rand.nextInt(4);
        if (d5 == 0) {
            encounter1A();
        } else if (d5 == 1) {
            encounter1B();
        } else if (d5 == 2) {
            encounter1C();
        } else encounter1D();

    }

    private void encounter1A() {
        activeEncounter = 0;
        textArea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice a strange lady holding a basket.
                You approach her and she asks if you can hold her basket for a moment and that she will come back.
                She gives you the basket, but you can't see what's inside, because it's covered with a rag
                She walks into the distance, leaving you with a decision.

                [a]look inside [b]wait until she comes back"""
        );
    }

    private void encounter1B() {
        activeEncounter = 1;
        textArea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice a group of goons beating up a poor looking man.
                They all look pretty weak.

                [a]walk past and hope they don't notice you [b]help the poor man"""
        );
    }

    private void encounter1C() {
        activeEncounter = 2;
        textArea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice some rubble on the side of the road.
                When you get closer, you can see that it's a flipped wagon.
                You can see some equipment on the ground and you have a feeling someone might be stuck under the wagon.

                [a]search the equipment and leave [b]try to flip the wagon"""
        );
    }

    private void encounter1D() {
        activeEncounter = 3;
        textArea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice two women on the side of the road arguing about something.
                They are really loud and it pisses you off.

                [a]try to solve their problem [b]kill them\s"""
        );
    }

    private void encounter1AResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathCause = "Your curiosity wins over you and you take the rag off."
                         + "\n" + "As soon as you do, a snake comes out of the basket and bites you in the neck, killing you instantly.";
            gameOver();
        }
        if(temp == 8 || temp == 9) {
            textArea.setText("""
                    Your curiosity wins over you and you take the rag off.
                    Inside the basket lies a dagger in the shape of a snake with a green tip.
                    You take it and leave, hoping the lady doesn't notice you.

                    -gained damage boost
                    [a]continue"""
            );
            damageBoost = 10;
        }
    }

    private void encounter1AResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textArea.setText("""
                    You wait patiently for about 20 minutes and she comes back.
                    she takes the basket and and gives you a dagger.
                    Without a word, she starts running away.
                    You look at the dagger she gave you. It's in the shape of a snake and has a green tip.
                    You put it in your pocket and continue your journey, extremely confused

                    gained damage boost
                    [a]continue"""
            );
            damageBoost = 10;
        }
        if(temp == 8 || temp == 9) {
            deathCause = """
                    You wait patiently for about 20 minutes and she comes back.
                    She comes closer and stabs you in the heart with a dagger.
                    She smiles and winks at you and watches you die in confusion.""";
            gameOver();
        }
    }

    private void encounter1BResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8 ) {
            textArea.setText("""
                    You try to walk past the group of goons, but one of them notices you and shouts "Hey! Get him too!"
                    You start running, but they catch you and beat the shit out of you.
                    After about 10 minutes, they leave and one of them spits on you.

                    -gained weakness
                    [a]continue"""
            );
            weakness = 10;
        }
        if(temp == 8 || temp == 9) {
            textArea.setText("""
                    You walk past the group of goons and none of them notice you.
                    You feel guilt for not helping the poor man, but at least you don't feel any broken bones.

                    [a]continue"""
            );
        }
    }

    private void encounter1BResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textArea.setText("""
                    You approach the group of goons and unsheathe your sword.
                    You drag it across the cobblestone pathway making a scary metallic noise.
                    As the goons notice you, they get scared and run away.
                    You help the poor man get back up and he gives you a book that says "how to use a sword properly".
                    You read it and find out, that you're supposed to swing the sword and not just drag it on the floor.

                    -gained damage boost
                    [a]continue"""
            );
            damageBoost = 10;
        }
        if(temp == 8 || temp == 9) {
            deathCause = """
                    You approach the group of goons and unsheathe your sword.
                    You drag it across the cobblestone pathway making a scary metallic noise.
                    All of the goons notice you, but suddenly your sword breaks.
                    They laugh at you and then swarm you, beating you until you bleed out.""";
            gameOver();
        }
    }

    private void encounter1CResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathCause = "You try to lift the wagon, but your hand slips and you get crushed under it's weight.";
            gameOver();
        }
        if(temp == 8 || temp == 9) {
            textArea.setText("""
                    You effortlessly flip the wagon and find a shiny new sword.
                    You throw out your old one and continue walking.

                    -gained damage boost
                    [a]continue"""
            );
            damageBoost = 10;
        }
    }
    private void encounter1CResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textArea.setText("""
                    You search the stuff on the ground and find a shiny new sword.
                    You like it so much, that you forget that there might be someone stuck under the wagon.

                    -gained damage boost
                    [a] continue"""
            );
            damageBoost = 10;
        }
        if(temp == 8 || temp == 9) {
            deathCause = """
                    You search the stuff on the ground and find a shiny new sword.
                    You like it so much, that you don't even notice, that somebody is crawling from underneath the wagon.
                    He sneaks up on you, because you're looking at your new sword and stabs you in the neck, killing you instantly.""";
            gameOver();
        }
    }

    private void encounter1DResultB() {
        textArea.setText("""
                You unsheathe your sword and kill them.
                You feel like a monster, but at least you don't have to listen to them anymore.
                [a] continue"""
        );
    }

    private void encounter1DResultA() {
        deathCause = "You come closer and ask what the problem is."
                     + "\n" + "They try to explain what is wrong, but the reason is so stupid, that your head explodes.";
        gameOver();
    }

    private void fight1() {
        textField.setVisible(false);
        rollButton.setVisible(true);
        if (bossTemp == 0) {
            enemyHealth = 200;
            textArea.setText("You carry on with your adventure and walk out of town."
                             + "\n" + "When you get to the next town, you see a guard at the gates."
                             + "\n" + "He looks about twice your size and has a scary looking sword."
                             + "\n" + "You unsheathe your sword and run towards him."
                             + "\n" + ""
                             + "\n" + "health: " + health
                             + "\n" + "enemy health: " + enemyHealth
            );
        }
        if (bossTemp > 0 && health > 0 && enemyHealth > 0) {
            if (diceRoll == 0) {
                rollButton.setVisible(false);
                deathCause = "You rolled a 1!"
                             + "\n" + "The guard grabs you by the neck and and stabs you through the heart.";
                gameOver();
            }
            if (diceRoll == 19) {
                rollButton.setVisible(false);
                fight1InstaVictory();
            }
            if (diceRoll < 19 && diceRoll > 15) {
                enemyHealth = (enemyHealth - 50) - damageBoost;
            }
            if (diceRoll < 16 && diceRoll > 11) {
                enemyHealth = (enemyHealth - 40) - damageBoost;
                health = (health - 10) - weakness;
            }
            if (diceRoll < 12 && diceRoll > 7) {
                enemyHealth = (enemyHealth - 30) - damageBoost;
                health = (health - 25) - weakness;
            }
            if (diceRoll < 8 && diceRoll > 3) {
                enemyHealth = (enemyHealth - 20) - damageBoost;
                health = (health - 40) - weakness;
            }
            if (diceRoll < 4 && diceRoll > 0) {
                enemyHealth = (enemyHealth - 10) - damageBoost;
                health = (health - 50) - weakness;
            }
            if (diceRoll != 0 && diceRoll != 19) {
                textArea.setText("you rolled " + (diceRoll + 1) + "\n" + "enemy health: " + enemyHealth + "\n" + "health: " + health);
            }
        }
        if (health <= 0) {
            rollButton.setVisible(false);
            deathCause = """
                    You took lots of hits and you are heavily injured.
                    You are exhausted and on the edge of dying.
                    You see the guard coming in for a finishing strike.
                    You try to block it, but you're too exhausted and slow.
                    The guard swings his mighty sword and splits your head in two."""
            ;
            gameOver();
        }
        if (health > 0 && enemyHealth <= 0) {
            fight1Victory();
        }
    }

    private void fight1Victory() {
        textArea.setText("After many successful strikes your sword is soaked in the guard's blood."
                         + "\n" + "He looks very weak and tired."
                         + "\n" + "You gather all your strength, run towards him and pierce his heart."
                         + "\n" + ""
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
        rollButton.setVisible(false);
        textField.setVisible(true);
        fightsWon++;
        enemyHealth = 200;
        bossTemp = 0;
    }

    private void fight1InstaVictory() {
        health = 200;
        textArea.setText("You rolled a 20!"
                         + "\n" + "You jump towards the guard and swing your sword with such precision, that you cut off his head."
                         + "\n" + "When you search his body, you find some healing potions."
                         + "\n" + ""
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
        rollButton.setVisible(false);
        textField.setVisible(true);
        fightsWon++;
        enemyHealth = 200;
        bossTemp = 0;
    }

    private void encounter2Decider() {
        Random rand = new Random();
        int d5 = rand.nextInt(4);
        if (d5 == 0) {
            encounter2A();
        } else if (d5 == 1) {
            encounter2B();
        } else if (d5 == 2) {
            encounter2C();
        } else encounter2D();
    }

    private void encounter2A() {
        activeEncounter = 0;
        textArea.setText("""
                You enter the first town and look around.
                As you're looking around, you see someone coming towards you.
                It's the town's innkeep and he wants to share a beer with you.

                [a]accept [b]politely decline"""
        );
    }

    private void encounter2B() {
        activeEncounter = 1;
        textArea.setText("""
                You enter the first town and look around.
                As you're looking around, you see someone coming towards you.
                It's the town's priest and he asks you if you want to participate in a ceremony
                
                [a]accept [b]decline
                """
        );
    }

    private void encounter2C() {
        activeEncounter = 2;
        textArea.setText("""
                You enter the first town and look around.
                As you're looking around, you see someone near an inn waving their arms at you.
                When you come close he points at a barrel of wine and asks you if you want it.
                He claims that he accidentally ordered too many and doesn't have space for it.
                
                [a]take it [b]decline
                """
        );
    }

    private void encounter2D() {
        activeEncounter = 3;
        textArea.setText("""
                You enter the first town and look around.
                As you're looking around, you see a goblin running towards you.
                He looks very hungry. He asks if you have any spare food.
                You have some gingerbread that you've been looking forward to eating.
                
                [a]give him the gingerbread [b]say that you don't have any food
                """);
    }

    private void encounter2AResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathCause = """
                    "Well that's just rude!"
                    He says, and stabs you in the heart with a switchblade.
                    "never decline such an offer!"
                    """;
            gameOver();
        }
        if(temp == 8 || temp == 9) {
            textArea.setText("""
                    "Alright man, have a great day"
                    He says and goes back into his inn.
                    
                    [a]continue
                    """);
        }
    }

    private void encounter2BResultB() {
        textArea.setText("""
                "How dare you?", he shouts.
                "By the power of god i curse you for eternity!"
                You suddenly feel week.
                
                -gained weakness
                [a]continue
                """);
        weakness = weakness + 20;
    }

    private void encounter2CResultB() {
        health = health + 20;
        if (health > 200) {health =200;}
        textArea.setText("\"Well okay then.\" He says and you go to drink some fresh water from the town's fountain instead."
                         + "\n" + "You feel very refreshed."
                         + "\n" + ""
                         + "\n" + "-healed"
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
    }

    private void encounter2DResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathCause = """
                    "Well then it looks like i'll have to eat you!"
                    His eyes turn red and all his muscles suddenly get bigger.
                    He grabs you by the foot and rips you in half.
                    """;
            gameOver();
        }
        if(temp == 8 || temp == 9) {
            textArea.setText("""
                    "Well then it looks like i'll have to eat you!"
                    His eyes turn red and all his muscles suddenly get bigger.
                    Luckily, your reflexes kick in and you unsheathe your sword.
                    He tries to grab you by the foot, but you manage to cut off his hand with one swift strike.
                    He screams in pain as you deliver the finishing strike, cutting his head off.
                    
                    [a]continue
                    """);
        }
    }

    private void encounter2AResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            health = health + 50;
            if (health > 200) {health =200;}

            textArea.setText("You accept his offer and share a beer with him in his inn."
                             + "\n" + "You tell him about your adventure so far and he hands you some health potions, because he thinks you're a cool dude."
                             + "\n" + ""
                             + "\n" + "-healed"
                             + "\n" + "health: " + health
                             + "\n" + "[a]continue"
            );
        }
        if(temp == 8 || temp == 9) {
            deathCause = """
                    You accept his offer and share a beer with him in his inn.
                    You take a big sip and after a while you start to feel dizzy.
                    You fall unconscious, never to wake up again""";
            gameOver();
        }
    }

    private void encounter2BResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp == 0 || temp == 1 || temp == 2 || temp == 3 || temp == 4 ) {
            textArea.setText("""
                    The priest takes you to the church along with several other people.
                    He then starts saying what you assume are prayers in a language you don't understand.
                    When he finishes, you suddenly feel stronger.
                    
                    -received damage boost
                    [a]continue
                    """
            );
            damageBoost = damageBoost + 10;
        }
        if(temp == 5 || temp == 6 || temp == 7 || temp == 8 || temp == 9) {
            deathCause = """
                    The priest takes you to the church along with several other people.
                    He then starts saying what you assume are prayers in a language you don't understand.
                    Suddenly you get attacked by a group of cultists that were hidden until now.
                    They take you into the church basement and tie you up on a ritual table.
                    You watch in terror as they open up your rib cage and take your heart out.
                    """;
            gameOver();
        }
    }

    private void encounter2CResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathCause = """
                    You accept his offer and take the barrel, but it's really heavy
                    Maybe if you drink some of the wine, it'll be easier to carry.
                    You drink some of the wine straight from the barrel.
                    Then you drink some more.
                    And some more.
                    Until you black out and die of alcohol poisoning.
                    """;
            gameOver();

        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {health =200;}
            textArea.setText("You accept his offer and take the barrel."
                             + "\n" + "You drink a little bit of the wine straight from the barrel."
                             + "\n" + "Feeling refreshed, you leave the barrel there and continue your journey."
                             + "\n" + ""
                             + "\n" + "-healed"
                             + "\n" + "health: " + health
                             + "\n" + "[a] continue"
            );
        }
    }

    private void encounter2DResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textArea.setText("""
                    "Thank you so much!"
                    He says and happily eats the gingerbread.
                    "I found this weird paper on the graveyard earlier, you can keep it."
                    He says and hands you a scroll.
                    It's a power scroll. You read it and feel it's power going into you.
                
                    -gained damage boost
                    [a]continue"""
            );
            damageBoost = damageBoost + 10;
        }
        if(temp == 8 || temp == 9) {
            deathCause = """
                    "Ew, gingerbread?"
                    He yells and kicks you in the nuts so hard, that you pass out.
                    """;
            gameOver();
        }
    }

    private void fight2() {
        textField.setVisible(false);
        rollButton.setVisible(true);
        if (bossTemp == 0) {
            enemyHealth = 200;
            textArea.setText("You walk out of town and head for the big city."
                             + "\n" + "From a distance you can see the city's guard."
                             + "\n" + "He's much bigger than you and has a huge axe."
                             + "\n" + "When you get closer you can see how big he really is and suddenly you really don't want to fight him."
                             + "\n" + "But you gather all your courage and charge towards him."
                             + "\n" + "health: " + health
                             + "\n" + "enemy health: " + enemyHealth
            );
        }
        if (bossTemp > 0 && health > 0 && enemyHealth > 0) {
            if (diceRoll == 0) {
                rollButton.setVisible(false);
                deathCause = """
                        You rolled a 1!
                        His attacks are pretty slow and you can easily dodge them.
                        But suddenly your foot gets stuck on a root and you can't move it.
                        The guard swings his mighty axe over his head and chops you in half.""";
                gameOver();
            }
            if (diceRoll == 19) {
                fight2InstaVictory();
            }
            if (diceRoll < 19 && diceRoll > 15) {
                enemyHealth = (enemyHealth - 50) - damageBoost;
            }
            if (diceRoll < 16 && diceRoll > 11) {
                enemyHealth = (enemyHealth - 40) - damageBoost;
                health = (health - 10) - weakness;
            }
            if (diceRoll < 12 && diceRoll > 7) {
                enemyHealth = (enemyHealth - 30) - damageBoost;
                health = (health - 25) - weakness;
            }
            if (diceRoll < 8 && diceRoll > 3) {
                enemyHealth = (enemyHealth - 20) - damageBoost;
                health = (health - 40) - weakness;
            }
            if (diceRoll < 4 && diceRoll > 0) {
                enemyHealth = (enemyHealth - 10) - damageBoost;
                health = (health - 50) - weakness;
            }
            if (diceRoll != 0 && diceRoll != 19) {
                textArea.setText("you rolled " + (diceRoll + 1) + "\n" + "enemy health: " + enemyHealth + "\n" + "health: " + health);
            }
        }
        if (health <= 0) {
            deathCause = """
                    You took so many hits, that you can barely move anymore.
                    You are exhausted and your vision is fading away.
                    Gathering all your remaining energy, you swing your sword for the last time.
                    It's a solid hit, but merely a scratch for the city's guard.
                    He swings his axe over his head, hits your shoulder and cuts off your arm, ending your life.""";
            gameOver();
            rollButton.setVisible(false);
        }
        if (health > 0 && enemyHealth <= 0) {
            fight2Victory();
        }
    }

    private void fight2Victory() {
        textArea.setText("You get hit a few times, but you start to memorise the guard's movements."
                         + "\n" + "After some time you can dodge almost all of his attacks and strike back."
                         + "\n" + "You see frustration and exhaustion in his eyes as you keep striking him."
                         + "\n" + "His movements are now very slow and he can't hit you anymore."
                         + "\n" + "You hit him a few more times until he falls to the ground, defeated."
                         + "\n" + ""
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
        rollButton.setVisible(false);
        textField.setVisible(true);
        fightsWon++;
        enemyHealth = 200;
        bossTemp = 0;
    }

    private void fight2InstaVictory() {
        textArea.setText("You rolled a 20!"
                         + "\n" + "You wait for him to attack and dodge perfectly."
                         + "\n" + "His axe gets stuck in the ground and He's struggling to pull it out."
                         + "\n" + "You quickly swing your sword and cut off both of his arms."
                         + "\n" + "He looks at them and falls to the ground, defeated."
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
        rollButton.setVisible(false);
        textField.setVisible(true);
        fightsWon++;
        enemyHealth = 200;
        bossTemp = 0;
    }

    private void encounter3Decider() {
        Random rand = new Random();
        int d5 = rand.nextInt(4);
        if (d5 == 0) {
            encounter3A();
        } else if (d5 == 1) {
            encounter3B();
        } else if (d5 == 2) {
            encounter3C();
        } else encounter3D();
    }

    private void encounter3A() {
        activeEncounter = 0;
        textArea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                Right next to you, you see a nest that has fallen out of a tree.
                There are some delicious looking eggs in it.
                
                [a]eat them [b]put the nest back into the tree
                """
        );
    }

    private void encounter3B() {
        activeEncounter = 1;
        textArea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                Suddenly, a huge storm starts to form above the city.
                It starts raining heavily. You can hear lightening in the distance.
                
                [a]hide in the nearest house [b]continue on your path
                """
        );
    }

    private void encounter3C() {
        activeEncounter = 2;
        textArea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                On the ground ahead, you notice a bag with some herbs.
                They look like healing herbs.
                
                [a]eat them [b]sniff them
                """
        );
    }

    private void encounter3D() {
        activeEncounter = 3;
        textArea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                On the ground ahead, you notice a note.
                It says, that there is a hidden stash of gear behind the city's inn and some near the city's fountain.
                
                [a]go behind the inn [b]go to the fountain
                """);
    }

    private void encounter3DResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textArea.setText("""
                    You go to the fountain, throw a coin in, and actually find some gear in the grass behind it.
                    Cool.
                    
                    -gained damage boost
                    [a]continue
                    """);
            damageBoost = damageBoost + 10;
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {
                health = 200;
            }
            deathCause = """
                    You go to the fountain, throw a coin in and look around it for some gear,
                    but then you step in a bear trap.
                    Some goblins come out of a bush near the fountain and shoot you with a blowgun.
                    You fall unconscious.
                    """;
            gameOver();
        }
    }

    private void encounter3CResultB() {
        health = health + 50;
        if (health > 200) {health =200;}
        textArea.setText("You sniff the herbs and feel really refreshed"
                         + "\n" + "They smell really good."
                         + "\n" + ""
                         + "\n" + "-healed"
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
    }

    private void encounter3BResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textArea.setText("""
                You don't let the rain bother you at all and continue on your quest.
                Your clothes are soaked and heavy.
                
                -gained weakness
                [a]continue
                """);
            weakness = weakness + 10;
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {
                health = 200;
            }
            deathCause = """
                    You don't let the rain bother you at all and continue on your quest.
                    But then a lightening strikes you, killing you instantly.
                    """;
            gameOver();
        }
    }

    private void encounter3AResultB() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textArea.setText("""
                    You take the nest and look up into the tree above you.
                    As you try to put the nest back, you notice a sword stuck into a branch on the tree.
                    It looks pretty cool, so you take it.
                    
                    -gained damage boost
                    [a]continue
                    """
            );
            damageBoost = damageBoost + 10;
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {health =200;}
            deathCause = """
                    You try to climb the tree to put the nest back.
                    As you are climbing you grab a weak branch and it breaks.
                    You lose your balance and fall on the ground and break your neck.
                    """;
            gameOver();
        }
    }

    private void encounter3DResultA() {
        deathCause = """
                You go behind the inn, just like the note said.
                There's a dark alley with some stuff on the ground at the end of it.
                When you get near the end, you get ambushed by a group of bandits.
                Should have known it would be a trap.
                """;
        gameOver();
    }

    private void encounter3CResultA() {
        textArea.setText("""
                When you eat them, you immediately feel sick.
                You vomit and fall unconscious, never to wake up again.
                """
        );
    }

    private void encounter3BResultA() {
        deathCause = """
                You run into the nearest house you see and shut the door behind you.
                It's pitch black inside, but you can hear some movement.
                Suddenly a door opens, letting some light in and you notice a group of cultists were hidden in the darkness.
                They surround you and beat you to death.
                """;
        gameOver();
    }

    private void encounter3AResultA() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathCause = """
                    When you eat them, you immediately feel sick.
                    You vomit and fall unconscious, never to wake up again.
                    """;
            gameOver();
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {health =200;}
            textArea.setText("You decide to eat them, with the shell and all."
                             + "\n" + "They taste really good."
                             + "\n" + ""
                             + "\n" + "-healed"
                             + "\n" + "health: " + health
                             + "\n" + "[a]continue"
                    );
        }
    }

    private void fight3() {

        textField.setVisible(false);
        rollButton.setVisible(true);
        if (bossTemp == 0) {
            enemyHealth = 200;
            textArea.setText("You exit the city and make your way to rose castle."
                             + "\n" + "Your final destination."
                             + "\n" + "The only thing keeping you from meeting your dream knight is the evil wizard, "
                             + "\n" + "that guards rose castle."
                             + "\n" + "You heard, that he can control both ice and fire."
                             + "\n" + "You take a deep breath and get ready for the fight of your life."
                             + "\n" + ""
                             + "\n" + "health: " + health
                             + "\n" + "enemy health: " + enemyHealth
            );
        }

        if (bossTemp > 0 && health > 0 && enemyHealth > 0) {
            if (diceRoll == 0) {
                rollButton.setVisible(false);
                deathCause = """
                        You rolled a 1!
                        The evil wizard shoots an ice shard and hits you right in the heart.
                        All your blood freezes and you slowly die.
                        """;
                gameOver();
            }
            if (diceRoll == 19) {
                rollButton.setVisible(false);
                fight3Victory();
            }
            if (diceRoll < 19 && diceRoll > 15) {
                enemyHealth = (enemyHealth - 50) - damageBoost;
            }
            if (diceRoll < 16 && diceRoll > 11) {
                enemyHealth = (enemyHealth - 40) - damageBoost;
                health = (health - 10) - weakness;
            }
            if (diceRoll < 12 && diceRoll > 7) {
                enemyHealth = (enemyHealth - 30) - damageBoost;
                health = (health - 25) - weakness;
            }
            if (diceRoll < 8 && diceRoll > 3) {
                enemyHealth = (enemyHealth - 20) - damageBoost;
                health = (health - 40) - weakness;
            }
            if (diceRoll < 4 && diceRoll > 0) {
                enemyHealth = (enemyHealth - 10) - damageBoost;
                health = (health - 50) - weakness;
            }
            if (diceRoll != 0 && diceRoll != 19) {
                textArea.setText("you rolled " + (diceRoll + 1) + "\n" + "enemy health: " + enemyHealth + "\n" + "health: " + health);
            }
        }

        if (health <= 0) {
            rollButton.setVisible(false);
            deathCause = """
                    The wizard has hit you several times with his ice shards and fireballs.
                    You also hit him with your sword a couple times, but he doesn't seem weaker.
                    He charges up a big ice shard and shoots your feet.
                    Your feet freeze and you get stuck to the ground.
                    He charges up a fireball and hits you right in the face.
                    """;
            gameOver();
        }

        if (health > 0 && enemyHealth <= 0) {
            fight3Victory();
        }
    }

    private void fight3Victory() {
        textArea.setText("You've come this far and you won't let anything stop you now."
                         + "\n" + "You know what You're doing and concentrate on dodging the wizard's attacks."
                         + "\n" + "health: " + health);
        rollButton.setVisible(false);
        quitButton.setVisible(true);
        fightsWon++;
        enemyHealth = 200;
        bossTemp = 0;
    }
}