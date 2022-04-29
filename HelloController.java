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
    boolean answer = true;
    int weakness = 0;
    int activeencounter = 0;
    // tenhle int si pamatuje pocet zabiti, zobrazi se pri smrti, nebo vyhre
    int fightswon = 0;
    int damageboost = 0;
    int diceroll = 0;
    int bosstemp = 0;
    int enemyhealth = 200;
    //tento int si pamatuje zdravi hrace, pokud dosahne 0, hra konci
    int health = 200;
    int encounternumber = 0;
    // tenhle string rika duvod smrti hrace
    String deathcause = "DUVODSMRTI";

    @FXML
    private TextArea textarea;

    @FXML
    private TextField pole;

    @FXML
    private Button quitbutt;

    @FXML
    private Button tryagainbutt;

    @FXML
    private Button newgamebutt;

    @FXML
    private Label gamelabel;

    @FXML
    private Button rollbutt;

    @FXML
    protected void onroll() {
        diceroll = rand.nextInt(20);
        bosstemp ++;
        if (fightswon == 0) {fight1();} else
        if (fightswon == 1) {fight2();} else
        if (fightswon == 2) {fight3();}
    }

    @FXML
    protected void ontryagain() {
        weakness = 0;
        activeencounter = 0;
        fightswon = 0;
        damageboost = 0;
        bosstemp = 0;
        encounternumber = 0;
        health = 200;
        gamestart();
        quitbutt.setVisible(false);
        newgamebutt.setVisible(false);
        pole.setVisible(true);
        textarea.setVisible(true);
        gamelabel.setVisible(false);
        tryagainbutt.setVisible(false);
    }

    @FXML
    protected void onnewgame() {
        gamestart();
        quitbutt.setVisible(false);
        newgamebutt.setVisible(false);
        pole.setVisible(true);
        textarea.setVisible(true);
        gamelabel.setVisible(false);
    }

    @FXML
    protected void onquit() {
        Platform.exit();
    }

    @FXML
    protected void onenter() {
        String answr = pole.getText();

        if (answr.contains("a")) {
            pole.clear();
            answer = true;
            encounternumber ++;
            if (encounternumber == 1) {encounter1decider();} else
            if (encounternumber == 2) {
                if (activeencounter == 0)encounter1Aresulta();
                if (activeencounter == 1)encounter1Bresulta();
                if (activeencounter == 2)encounter1Cresulta();
                if (activeencounter == 3)encounter1Dresulta();
            } else
            if (encounternumber == 3) {fight1();} else
            if (encounternumber == 4) {encounter2decider();} else
            if (encounternumber == 5) {
                if (activeencounter == 0)encounter2Aresulta();
                if (activeencounter == 1)encounter2Bresulta();
                if (activeencounter == 2)encounter2Cresulta();
                if (activeencounter == 3)encounter2Dresulta();
            } else
            if (encounternumber == 6) {fight2();} else
            if (encounternumber == 7) {encounter3decider();} else
            if (encounternumber == 8) {
                if (activeencounter == 0)encounter3Aresulta();
                if (activeencounter == 1)encounter3Bresulta();
                if (activeencounter == 2)encounter3Cresulta();
                if (activeencounter == 3)encounter3Dresulta();
            } else
            if (encounternumber == 9) {fight3();}

        } else if (answr.contains("b")) {
            encounternumber ++;
            pole.clear();
            answer = false;

            if (encounternumber == 1) {
                deathcause = "you decided to stay in bed :(";
                gameover();
            } else
            if (encounternumber == 2) {
                if (activeencounter == 0)encounter1Aresultb();
                if (activeencounter == 1)encounter1Bresultb();
                if (activeencounter == 2)encounter1Cresultb();
                if (activeencounter == 3)encounter1Dresultb();
            } else
            if (encounternumber == 3) {pole.clear();} else
            if (encounternumber == 4) {pole.clear();} else
            if (encounternumber == 5) {
                if (activeencounter == 0)encounter2Aresultb();
                if (activeencounter == 1)encounter2Bresultb();
                if (activeencounter == 2)encounter2Cresultb();
                if (activeencounter == 3)encounter2Dresultb();
            } else
            if (encounternumber == 6) {pole.clear();} else
            if (encounternumber == 7) {pole.clear();} else
            if (encounternumber == 8) {
                if (activeencounter == 0)encounter3Aresultb();
                if (activeencounter == 1)encounter3Bresultb();
                if (activeencounter == 2)encounter3Cresultb();
                if (activeencounter == 3)encounter3Dresultb();
            } else
            if (encounternumber == 9) {pole.clear();}

        } else {pole.clear();}
    }

    private void encounter3Dresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textarea.setText("""
                    You go to the fountain, throw a coin in, and actually find some gear in the grass behind it.
                    Cool.
                    
                    -gained damage boost
                    [a]continue
                    """);
            damageboost = damageboost + 10;
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {
                health = 200;
            }
            deathcause = """
                    You go to the fountain, throw a coin in and look around it for some gear,
                    but then you step in a bear trap.
                    Some goblins come out of a bush near the fountain and shoot you with a blowgun.
                    You fall unconscious.
                    """;
            gameover();
        }
    }

    private void encounter3Cresultb() {
        health = health + 50;
        if (health > 200) {health =200;}
        textarea.setText("You sniff the herbs and feel really refreshed"
                         + "\n" + "They smell really good."
                         + "\n" + ""
                         + "\n" + "-healed"
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
    }

    private void encounter3Bresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textarea.setText("""
                You don't let the rain bother you at all and continue on your quest.
                Your clothes are soaked and heavy.
                
                -gained weakness
                """);
            weakness = weakness + 10;
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {
                health = 200;
            }
            deathcause = """
                    You don't let the rain bother you at all and continue on your quest.
                    But then a lightening strikes you, killing you instantly.
                    """;
            gameover();
        }
    }

    private void encounter3Aresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textarea.setText("""
                    You take the nest and look up into the tree above you.
                    As you try to put the nest back, you notice a sword stuck into a branch on the tree.
                    It looks pretty cool, so you take it.
                    
                    -gained damage boost
                    [a]continue
                    """
            );
            damageboost = damageboost + 10;
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {health =200;}
            deathcause = """
                    You try to climb the tree to put the nest back.
                    As you are climbing you grab a weak branch and it breaks.
                    You lose your balance and fall on the ground and break your neck.
                    """;
            gameover();
        }
    }

    private void encounter3Dresulta() {
        deathcause = """
                You go behind the inn, just like the note said.
                There's a dark alley with some stuff on the ground at the end of it.
                When you get near the end, you get ambushed by a group of bandits.
                Should have known it would be a trap.
                """;
        gameover();
    }

    private void encounter3Cresulta() {
        textarea.setText("""
                When you eat them, you immediately feel sick.
                You vomit and fall unconscious, never to wake up again.
                """
        );
    }

    private void encounter3Bresulta() {
        deathcause = """
                You run into the nearest house you see and shut the door behind you.
                It's pitch black inside, but you can hear some movement.
                Suddenly a door opens, letting some light in and you notice a group of cultists were hidden in the darkness.
                They surround you and beat you to death.
                """;
        gameover();
    }

    private void encounter3Aresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathcause = """
                    When you eat them, you immediately feel sick.
                    You vomit and fall unconscious, never to wake up again.
                    """;
            gameover();
        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {health =200;}
            textarea.setText("You decide to eat them, with the shell and all."
                    + "\n" + "They taste really good."
                    + "\n" + ""
                    + "\n" +"-healed"
                    + "\n" + "health: "+ health
                    + "\n" + "[a]continue"
                    );
        }
    }

    private void encounter2Aresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathcause = """
                    "Well that's just rude!"
                    He says, and stabs you in the heart with a switchblade.
                    "never decline such an offer!"
                    """;
            gameover();
        }
        if(temp == 8 || temp == 9) {
            textarea.setText("""
                    "Alright man, have a great day"
                    He says and goes back into his inn.
                    
                    [a]continue
                    """);
        }
    }

    private void encounter2Bresultb() {
        textarea.setText("""
                "How dare you?", he shouts.
                "By the power of god i curse you for eternity!"
                You suddenly feel week.
                
                -gained weakness
                [a]continue
                """);
        weakness = weakness + 20;
    }

    private void encounter2Cresultb() {
        health = health + 20;
        if (health > 200) {health =200;}
        textarea.setText("\"Well okay then.\" He says and you go to drink some fresh water from the town's fountain instead."
                         + "\n" + "You feel very refreshed."
                         + "\n" + ""
                         + "\n" + "-healed"
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
    }

    private void encounter2Dresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathcause = """
                    "Well then it looks like i'll have to eat you!"
                    His eyes turn red and all his muscles suddenly get bigger.
                    He grabs you by the foot and rips you in half.
                    """;
            gameover();
        }
        if(temp == 8 || temp == 9) {
            textarea.setText("""
                    "Well then it looks like i'll have to eat you!"
                    His eyes turn red and all his muscles suddenly get bigger.
                    Luckily, your reflexes kick in and you unsheathe your sword.
                    He tries to grab you by the foot, but you manage to cut off his hand with one swift strike.
                    He screams in pain as you deliver the finishing strike, cutting his head off.
                    
                    [a]continue
                    """);
        }
    }

    private void encounter3decider() {
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

    private void encounter3D() {
        activeencounter = 3;
        textarea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                On the ground ahead, you notice a note.
                It says, that there is a hidden stash of gear behind the city's inn and some near the city's fountain.
                
                [a]go behind the inn [b]go to the fountain
                """);
    }

    private void encounter3C() {
        activeencounter = 2;
        textarea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                On the ground ahead, you notice a bag with some herbs.
                They look like healing herbs.
                
                [a]eat them [b]sniff them
                """
        );
    }

    private void encounter3B() {
        activeencounter = 1;
        textarea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                Suddenly, a huge storm starts to form above the city.
                It starts raining heavily. You can hear lightening in the distance.
                
                [a]hide in the nearest house [b]continue on your path
                """
        );
    }

    private void encounter3A() {
        activeencounter = 0;
        textarea.setText("""
                You walk into the city, looking for something to heal your wounds with.
                Right next to you, you see a nest that has fallen out of a tree.
                There are some delicious looking eggs in it.
                
                [a]eat them [b]put the nest back into the tree
                """
        );
    }

    private void encounter2Aresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            health = health + 50;
            if (health > 200) {health =200;}

            textarea.setText("You accept his offer and share a beer with him in his inn."
                             + "\n" + "You tell him about your adventure so far and he hands you some health potions, because he thinks you're a cool dude."
                             + "\n" + ""
                             + "\n" + "-healed"
                             + "\n" + "health: " + health
                             + "\n" + "[a]continue"
            );
        }
        if(temp == 8 || temp == 9) {
            deathcause = """
                    You accept his offer and share a beer with him in his inn.
                    You take a big sip and after a while you start to feel dizzy.
                    You fall unconscious, never to wake up again""";
            gameover();
        }
    }

    private void encounter2Bresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp == 0 || temp == 1 || temp == 2 || temp == 3 || temp == 4 ) {
            textarea.setText("""
                    The priest takes you to the church along with several other people.
                    He then starts saying what you assume are prayers in a language you don't understand.
                    When he finishes, you suddenly feel stronger.
                    
                    -received damage boost
                    [a]continue
                    """
            );
            damageboost = damageboost + 10;
        }
        if(temp == 5 || temp == 6 || temp == 7 || temp == 8 || temp == 9) {
            deathcause = """
                    The priest takes you to the church along with several other people.
                    He then starts saying what you assume are prayers in a language you don't understand.
                    Suddenly you get attacked by a group of cultists that were hidden until now.
                    They take you into the church basement and tie you up on a ritual table.
                    You watch in terror as they open up your rib cage and take your heart out.
                    """;
            gameover();
        }
    }

    private void encounter2Cresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathcause = """
                    You accept his offer and take the barrel, but it's really heavy
                    Maybe if you drink some of the wine, it'll be easier to carry.
                    You drink some of the wine straight from the barrel.
                    Then you drink some more.
                    And some more.
                    Until you black out and die of alcohol poisoning.
                    """;
            gameover();

        }
        if(temp == 8 || temp == 9) {
            health = health + 50;
            if (health > 200) {health =200;}
            textarea.setText("You accept his offer and take the barrel."
                             + "\n" + "You drink a little bit of the wine straight from the barrel."
                             + "\n" + "Feeling refreshed, you leave the barrel there and continue your journey."
                             + "\n" + ""
                             + "\n" + "-healed"
                             + "\n" + "health: " + health
                             + "\n" + "[a] continue"
            );
        }
    }

    private void encounter2Dresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textarea.setText("""
                    "Thank you so much!"
                    He says and happily eats the gingerbread.
                    "I found this weird paper on the graveyard earlier, you can keep it."
                    He says and hands you a scroll.
                    It's a power scroll. You read it and feel it's power going into you.
                
                    -gained damage boost
                    [a]continue"""
            );
            damageboost = damageboost + 10;
        }
        if(temp == 8 || temp == 9) {
            deathcause = """
                    "Ew, gingerbread?"
                    He yells and kicks you in the nuts so hard, that you pass out.
                    """;
            gameover();
        }
    }

    private void encounter2decider() {
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

    private void encounter2D() {
        activeencounter = 3;
        textarea.setText("""
                You enter the first town and look around.
                As you're looking around, you see a goblin running towards you.
                He looks very hungry. He asks if you have any spare food.
                You have some gingerbread that you've been looking forward to eating.
                
                [a]give him the gingerbread [b]say that you don't have any food
                """);
    }

    private void encounter2C() {
        activeencounter = 2;
        textarea.setText("""
                You enter the first town and look around.
                As you're looking around, you see someone near an inn waving their arms at you.
                When you come close he points at a barrel of wine and asks you if you want it.
                He claims that he accidentally ordered too many and doesn't have space for it.
                
                [a]take it [b]decline
                """
        );
    }

    private void encounter2B() {
        activeencounter = 1;
        textarea.setText("""
                You enter the first town and look around.
                As you're looking around, you see someone coming towards you.
                It's the town's priest and he asks you if you want to participate in a ceremony
                
                [a]accept [b]decline
                """
        );
    }

    private void encounter2A() {
        activeencounter = 0;
        textarea.setText("""
                You enter the first town and look around.
                As you're looking around, you see someone coming towards you.
                It's the town's innkeep and he wants to share a beer with you.

                [a]accept [b]politely decline"""
        );
    }

    private void encounter1Aresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textarea.setText("""
                    You wait patiently for about 20 minutes and she comes back.
                    she takes the basket and and gives you a dagger.
                    Without a word, she starts running away.
                    You look at the dagger she gave you. It's in the shape of a snake and has a green tip.
                    You put it in your pocket and continue your journey, extremely confused

                    gained damage boost
                    [a]continue"""
            );
            damageboost = 10;
        }
        if(temp == 8 || temp == 9) {
            deathcause = """
                    You wait patiently for about 20 minutes and she comes back.
                    She comes closer and stabs you in the heart with a dagger.
                    She smiles and winks at you and watches you die in confusion.""";
            gameover();
        }
    }

    private void encounter1Bresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textarea.setText("""
                    You approach the group of goons and unsheathe your sword.
                    You drag it across the cobblestone pathway making a scary metallic noise.
                    As the goons notice you, they get scared and run away.
                    You help the poor man get back up and he gives you a book that says "how to use a sword properly".
                    You read it and find out, that you're supposed to swing the sword and not just drag it on the floor.

                    -gained damage boost
                    [a]continue"""
            );
            damageboost = 10;
        }
        if(temp == 8 || temp == 9) {
            deathcause = """
                    You approach the group of goons and unsheathe your sword.
                    You drag it across the cobblestone pathway making a scary metallic noise.
                    All of the goons notice you, but suddenly your sword breaks.
                    They laugh at you and then swarm you, beating you until you bleed out.""";
            gameover();
        }
    }

    private void encounter1Cresultb() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathcause = "You try to lift the wagon, but your hand slips and you get crushed under it's weight.";
            gameover();
        }
        if(temp == 8 || temp == 9) {
            textarea.setText("""
                    You effortlessly flip the wagon and find a shiny new sword.
                    You throw out your old one and continue walking.

                    -gained damage boost
                    [a]continue"""
            );
            damageboost = 10;
        }
    }

    private void encounter1Dresultb() {
        textarea.setText("""
                You unsheathe your sword and kill them.
                You feel like a monster, but at least you don't have to listen to them anymore.
                [a] continue"""
        );
    }

    private void encounter1Dresulta() {
            deathcause = "You come closer and ask what the problem is."
                    + "\n" + "They try to explain what is wrong, but the reason is so stupid, that your head explodes.";
            gameover();
    }

    private void encounter1Cresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            textarea.setText("""
                    You search the stuff on the ground and find a shiny new sword.
                    You like it so much, that you forget that there might be someone stuck under the wagon.

                    -gained damage boost
                    [a] continue"""
            );
            damageboost = 10;
        }
        if(temp == 8 || temp == 9) {
            deathcause = """
                    You search the stuff on the ground and find a shiny new sword.
                    You like it so much, that you don't even notice, that somebody is crawling from underneath the wagon.
                    He sneaks up on you, because you're looking at your new sword and stabs you in the neck, killing you instantly.""";
            gameover();
        }
    }

    private void encounter1Bresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8 ) {
            textarea.setText("""
                    You try to walk past the group of goons, but one of them notices you and shouts "Hey! Get him too!"
                    You start running, but they catch you and beat the shit out of you.
                    After about 10 minutes, they leave and one of them spits on you.

                    -gained weakness
                    [a]continue"""
            );
            weakness = 10;
        }
        if(temp == 8 || temp == 9) {
            textarea.setText("""
                    You walk past the group of goons and none of them notice you.
                    You feel guilt for not helping the poor man, but at least you don't feel any broken bones.

                    [a]continue"""
            );
        }
    }

    private void encounter1Aresulta() {
        Random rand = new Random();
        int temp = rand.nextInt(10);
        if (temp < 8) {
            deathcause = "Your curiosity wins over you and you take the rag off."
                    + "\n" + "As soon as you do, a snake comes out of the basket and bites you in the neck, killing you instantly.";
            gameover();
        }
        if(temp == 8 || temp == 9) {
            textarea.setText("""
                    Your curiosity wins over you and you take the rag off.
                    Inside the basket lies a dagger in the shape of a snake with a green tip.
                    You take it and leave, hoping the lady doesn't notice you.

                    -gained damage boost
                    [a]continue"""
            );
            damageboost = 10;
        }
    }

    private void encounter1decider() {
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

    private void encounter1D() {
        activeencounter = 3;
        textarea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice two women on the side of the road arguing about something.
                They are really loud and it pisses you off.

                [a]try to solve their problem [b]kill them\s"""
        );
    }

    private void encounter1C() {
        activeencounter = 2;
        textarea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice some rubble on the side of the road.
                When you get closer, you can see that it's a flipped wagon.
                You can see some equipment on the ground and you have a feeling someone might be stuck under the wagon.

                [a]search the equipment and leave [b]try to flip the wagon"""
        );
    }

    private void encounter1B() {
        activeencounter = 1;
        textarea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice a group of goons beating up a poor looking man.
                They all look pretty weak.

                [a]walk past and hope they don't notice you [b]help the poor man"""
        );
    }

    private void encounter1A() {
        activeencounter = 0;
        textarea.setText("""
                You take your trusty sword and set out on your big journey.
                As you walk trough your home town, you notice a strange lady holding a basket.
                You approach her and she asks if you can hold her basket for a moment and that she will come back.
                She gives you the basket, but you can't see what's inside, because it's covered with a rag
                She walks into the distance, leaving you with a decision.

                [a]look inside [b]wait until she comes back"""
        );
    }

    private void fight3() {

        pole.setVisible(false);
        rollbutt.setVisible(true);
        if (bosstemp == 0) {
            enemyhealth = 200;
            textarea.setText("You exit the city and make your way to rose castle."
                             + "\n" + "Your final destination."
                             + "\n" + "The only thing keeping you from meeting your dream knight is the evil wizard, "
                             + "\n" + "that guards rose castle."
                             + "\n" + "You heard, that he can control both ice and fire."
                             + "\n" + "You take a deep breath and get ready for the fight of your life."
                             + "\n" + ""
                             + "\n" + "health: " + health
                             + "\n" + "enemy health: " + enemyhealth );
        }
        if (bosstemp > 0 && health > 0 && enemyhealth > 0) {
            if (diceroll == 0) {
                rollbutt.setVisible(false);
                deathcause = """
                        The evil wizard shoots an ice shard and hits you right in the heart.
                        All your blood freezes and you slowly die.
                        """;
                gameover();
            }
            if (diceroll == 19) {
                rollbutt.setVisible(false);
                fight3victory();
            }
            if (diceroll == 18 || diceroll == 17 || diceroll == 16) {
                enemyhealth = (enemyhealth - 50) - damageboost;
            }
            if (diceroll == 15 || diceroll == 14 || diceroll == 13 || diceroll == 12) {
                enemyhealth = (enemyhealth - 40) - damageboost;
                health = (health - 10) - weakness;
            }
            if (diceroll == 11 || diceroll == 10 || diceroll == 9 || diceroll == 8) {
                enemyhealth = (enemyhealth - 30) - damageboost;
                health = (health - 25) - weakness;
            }
            if (diceroll == 7 || diceroll == 6 || diceroll == 5 || diceroll == 4) {
                enemyhealth = (enemyhealth - 20) - damageboost;
                health = (health - 40) - weakness;
            }
            if (diceroll == 3 || diceroll == 2 || diceroll == 1) {
                enemyhealth = (enemyhealth - 10) - damageboost;
                health = (health - 50) - weakness;
            }
            if (diceroll != 0 && diceroll != 19) {
                textarea.setText("you rolled " + (diceroll + 1) + "\n" + "enemy health: " + enemyhealth + "\n" + "health: " + health);
            }
        }
        if (health <= 0) {
            rollbutt.setVisible(false);
            deathcause = """
                    The wizard has hit you several times with his ice shards and fireballs.
                    You also hit him with your sword a couple times, but he doesn't seem weaker.
                    He charges up a big ice shard and shoots your feet.
                    Your feet freeze and you get stuck to the ground.
                    He charges up a fireball and hits you straight in the face.
                    """;
            gameover();
        }
        if (enemyhealth <= 0) {fight3victory();}
    }


    private void fight3victory() {
        textarea.setText("fight 3 victory"  + "\n" + "health: " + health);
        rollbutt.setVisible(false);
        pole.setVisible(true);
        fightswon++;
        enemyhealth = 200;
        bosstemp = 0;
    }

    private void fight1() {
        pole.setVisible(false);
        rollbutt.setVisible(true);
        if (bosstemp == 0) {
            enemyhealth = 200;
            textarea.setText("You carry on with your adventure and walk out of town."
                    + "\n" + "When you get to the next town, you see a guard at the gates."
                    + "\n" + "He looks about twice your size and has a scary looking sword."
                    + "\n" + "You unsheathe your sword and run towards him."
                    + "\n" + ""
                    + "\n" + "health: " + health
                    + "\n" + "enemy health: " + enemyhealth
            );
        }
        if (bosstemp > 0 && health > 0 && enemyhealth > 0) {
            if (diceroll == 0) {
                rollbutt.setVisible(false);
                deathcause = "You rolled a 1!"
                        + "\n" + "The guard grabs you by the neck and and stabs you through the heart.";
                gameover();
            }
            if (diceroll == 19) {
                rollbutt.setVisible(false);
                fight1instavictory();
            }
            if (diceroll == 18 || diceroll == 17 || diceroll == 16) {
                enemyhealth = (enemyhealth - 50) - damageboost;
            }
            if (diceroll == 15 || diceroll == 14 || diceroll == 13 || diceroll == 12) {
                enemyhealth = (enemyhealth - 40) - damageboost;
                health = (health - 10) - weakness;
            }
            if (diceroll == 11 || diceroll == 10 || diceroll == 9 || diceroll == 8) {
                enemyhealth = (enemyhealth - 30) - damageboost;
                health = (health - 25) - weakness;
            }
            if (diceroll == 7 || diceroll == 6 || diceroll == 5 || diceroll == 4) {
                enemyhealth = (enemyhealth - 20) - damageboost;
                health = (health - 40) - weakness;
            }
            if (diceroll == 3 || diceroll == 2 || diceroll == 1) {
                enemyhealth = (enemyhealth - 10) - damageboost;
                health = (health - 50) - weakness;
            }
            if (diceroll != 0 && diceroll != 19) {
                textarea.setText("you rolled " + (diceroll + 1) + "\n" + "enemy health: " + enemyhealth + "\n" + "health: " + health);
            }
        }
        if (health <= 0) {
            rollbutt.setVisible(false);
            deathcause = """
                    You took lots of hits and you are heavily injured.
                    You are exhausted and on the edge of dying.
                    You see the guard coming in for a finishing strike.
                    You try to block it, but you're too exhausted and slow.
                    The guard swings his mighty sword and splits your head in two."""
            ;
            gameover();
        }
        if (enemyhealth <= 0) {fight1victory();}
    }

    private void fight1instavictory() {
        health = 200;
        textarea.setText("You rolled a 20!"
                + "\n" + "You jump towards the guard and swing your sword with such precision, that you cut off his head."
                + "\n" + "When you search his body, you find some healing potions."
                + "\n" + ""
                + "\n" + "health: " + health
                + "\n" + "[a]continue"
        );
        rollbutt.setVisible(false);
        pole.setVisible(true);
        fightswon++;
        enemyhealth = 200;
        bosstemp = 0;
    }

    private void fight2() {
        pole.setVisible(false);
        rollbutt.setVisible(true);
        if (bosstemp == 0) {
            enemyhealth = 200;
            textarea.setText("You walk out of town and head for the big city."
                             + "\n" + "From a distance you can see the city's guard."
                             + "\n" + "He's much bigger than you and has a huge axe."
                             + "\n" + "When you get closer you can see how big he really is and suddenly you really don't want to fight him."
                             + "\n" + "But you gather all your courage and charge towards him."
                             + "\n" + "health: " + health
                             + "\n" + "enemy health: " + enemyhealth
            );
        }
        if (bosstemp > 0 && health > 0 && enemyhealth > 0) {
            if (diceroll == 0) {
                rollbutt.setVisible(false);
                deathcause = """
                        You rolled a 1!
                        His attacks are pretty slow and you can easily dodge them.
                        But suddenly your foot gets stuck on a root and you can't move it.
                        The guard swings his mighty axe over his head and chops you in half.""";
                gameover();
            }
            if (diceroll == 19) {
                fight2instavictory();
            }
            if (diceroll == 18 || diceroll == 17 || diceroll == 16) {
                enemyhealth = (enemyhealth - 50) - damageboost;
            }
            if (diceroll == 15 || diceroll == 14 || diceroll == 13 || diceroll == 12) {
                enemyhealth = (enemyhealth - 40) - damageboost;
                health = (health - 10) - weakness;
            }
            if (diceroll == 11 || diceroll == 10 || diceroll == 9 || diceroll == 8) {
                enemyhealth = (enemyhealth - 30) - damageboost;
                health = (health - 25) - weakness;
            }
            if (diceroll == 7 || diceroll == 6 || diceroll == 5 || diceroll == 4) {
                enemyhealth = (enemyhealth - 20) - damageboost;
                health = (health - 40) - weakness;
            }
            if (diceroll == 3 || diceroll == 2 || diceroll == 1) {
                enemyhealth = (enemyhealth - 10) - damageboost;
                health = (health - 50) - weakness;
            }
            if (diceroll != 0 && diceroll != 19) {
                textarea.setText("you rolled " + (diceroll + 1) + "\n" + "enemy health: " + enemyhealth + "\n" + "health: " + health);
            }
        }
        if (health <= 0) {
            deathcause = """
                    You took so many hits, that you can barely move anymore.
                    You are exhausted and your vision is fading away.
                    Gathering all your remaining energy, you swing your sword for the last time.
                    It's a solid hit, but merely a scratch for the city's guard.
                    He swings his axe over his head, hits your shoulder and cuts off your arm, ending your life.""";
            gameover();
            rollbutt.setVisible(false);
        }
        if (enemyhealth <= 0) {fight2victory();}
    }

    private void fight2instavictory() {
        textarea.setText("You rolled a 20!"
                         + "\n" + "You wait for him to attack and dodge perfectly."
                         + "\n" + "His axe gets stuck in the ground and He's struggling to pull it out."
                         + "\n" + "You quickly swing your sword and cut off both of his arms."
                         + "\n" + "He looks at them and falls to the ground, defeated."
                         + "\n" + "health: " + health
        );
        rollbutt.setVisible(false);
        pole.setVisible(true);
        fightswon++;
        enemyhealth = 200;
        bosstemp = 0;
    }

    private void fight2victory() {
        textarea.setText("You get hit a few times, but you start to memorise the guard's movements."
                         + "\n" + "After some time you can dodge almost all of his attacks and strike back."
                         + "\n" + "You see frustration and exhaustion in his eyes as you keep striking him."
                         + "\n" + "His movements are now very slow and he can't hit you anymore."
                         + "\n" + "You hit him a few more times until he falls to the ground, defeated."
                         + "\n" + ""
                         + "\n" + "health: " + health
                         + "\n" + "[a]continue"
        );
        rollbutt.setVisible(false);
        pole.setVisible(true);
        fightswon++;
        enemyhealth = 200;
        bosstemp = 0;
    }

    private void fight1victory() {
        textarea.setText("After many successful strikes your sword is soaked in the guard's blood."
                + "\n" + "He looks very weak and tired."
                + "\n" + "You gather all your strength, run towards him and pierce his heart."
                + "\n" + ""
                + "\n" + "health: " + health
                + "\n" + "[a]continue"
        );
        rollbutt.setVisible(false);
        pole.setVisible(true);
        fightswon++;
        enemyhealth = 200;
        bosstemp = 0;
    }

    private void gamestart() {
        textarea.setText("""
                you are on a noble quest to find your dream knight, located in rose castle.
                But to get there, you must defeat the guards in the town and in the city, as well as the evil wizard,
                that guards rose castle.
                Are you ready to embark on your epic adventure?

                [a]yes [b]no"""
        );
    }

    private void gameover() {
        textarea.setText("GAME OVER!"
                         + "\n" + deathcause
                         + "\n" + "Enemies killed: " + fightswon);
        pole.setVisible(false);
        quitbutt.setVisible(true);
        tryagainbutt.setVisible(true);
    }
}
