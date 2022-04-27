package com.example.dnd2;

import javafx.application.Application;
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
    int fightswon = 0;
    int damageboost = 0;
    int diceroll = 0;
    int bosstemp = 0;
    int enemyhealth = 200;
    int health = 200;
    int encounternumber = 0;
    // tenhle int si pamatuje pocet zabiti, zobrazi se pri smrti, nebo vyhre
    int kills = 0;
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
            pole.clear();
            answer = false;
            encounternumber ++;
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
            if (encounternumber == 3) {fight1();} else
            if (encounternumber == 4) {encounter2decider();} else
            if (encounternumber == 5) {
                if (activeencounter == 0)encounter2Aresultb();
                if (activeencounter == 1)encounter2Bresultb();
                if (activeencounter == 2)encounter2Cresultb();
                if (activeencounter == 3)encounter2Dresultb();
            } else
            if (encounternumber == 6) {fight2();} else
            if (encounternumber == 7) {encounter3decider();} else
            if (encounternumber == 8) {
                if (activeencounter == 0)encounter3Aresultb();
                if (activeencounter == 1)encounter3Bresultb();
                if (activeencounter == 2)encounter3Cresultb();
                if (activeencounter == 3)encounter3Dresultb();
            } else
            if (encounternumber == 9) {fight3();}

        } else {pole.clear();}
    }

    private void encounter3Dresultb() {
        textarea.setText("encounter3Dresultb");
    }

    private void encounter3Cresultb() {
        textarea.setText("encounter3Cresultb");
    }

    private void encounter3Bresultb() {
        textarea.setText("encounter3Bresultb");
    }

    private void encounter3Aresultb() {
        textarea.setText("encounter3Aresultb");
    }

    private void encounter3Dresulta() {
        textarea.setText("encounter3Dresulta");
    }

    private void encounter3Cresulta() {
        textarea.setText("encounter3Cresulta");
    }

    private void encounter3Bresulta() {
        textarea.setText("encounter3Bresulta");
    }

    private void encounter3Aresulta() {
        textarea.setText("encounter3Aresulta");
    }

    private void encounter2Aresultb() {
        textarea.setText("encounter2Aresultb");
    }

    private void encounter2Bresultb() {
        textarea.setText("encounter2Bresultb");
    }

    private void encounter2Cresultb() {
        textarea.setText("encounter2Cresultb");
    }

    private void encounter2Dresultb() {
        textarea.setText("encounter2Dresultb");
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
        } else if (d5 == 3) {
            encounter3D();
        }
    }

    private void encounter3D() {
        activeencounter = 3;
        textarea.setText("encounter3D");
    }

    private void encounter3C() {
        activeencounter = 2;
        textarea.setText("encounter3C");
    }

    private void encounter3B() {
        activeencounter = 1;
        textarea.setText("encounter3B");
    }

    private void encounter3A() {
        activeencounter = 0;
        textarea.setText("encounter3A");
    }

    private void encounter2Aresulta() {
        textarea.setText("encounter2Aresulta");
    }

    private void encounter2Bresulta() {
        textarea.setText("encounter2Bresulta");
    }

    private void encounter2Cresulta() {
        textarea.setText("encounter2Cresulta");
    }

    private void encounter2Dresulta() {
        textarea.setText("encounter2Dresulta");
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
        } else if (d5 == 3) {
            encounter2D();
        }
    }

    private void encounter2D() {
        activeencounter = 3;
        textarea.setText("encounter2D");
    }

    private void encounter2C() {
        activeencounter = 2;
        textarea.setText("encounter2C");
    }

    private void encounter2B() {
        activeencounter = 1;
        textarea.setText("encounter2B");
    }

    private void encounter2A() {
        activeencounter = 0;
        textarea.setText("encounter2A");
    }

    private void encounter1Aresultb() {textarea.setText("encounter1Aresultb");}

    private void encounter1Bresultb() {textarea.setText("encounter1Bresultb");}

    private void encounter1Cresultb() {textarea.setText("encounter1Cresultb");}

    private void encounter1Dresultb() {textarea.setText("encounter1Dresultb");}

    private void encounter1Dresulta() {textarea.setText("encounter1Dresulta");}

    private void encounter1Cresulta() {textarea.setText("encounter1Cresulta");}

    private void encounter1Bresulta() {textarea.setText("encounter1Bresulta");}

    private void encounter1Aresulta() {textarea.setText("encounter1Aresulta");}

    private void encounter1decider() {
        Random rand = new Random();
        int d5 = rand.nextInt(4);
        if (d5 == 0) {
            encounter1A();
        } else if (d5 == 1) {
            encounter1B();
        } else if (d5 == 2) {
            encounter1C();
        } else if (d5 == 3) {
            encounter1D();
        }
    }

    private void encounter1D() {
        activeencounter = 3;
        textarea.setText("encounter1D");
    }

    private void encounter1C() {
        activeencounter = 2;
        textarea.setText("encounter1C");
    }

    private void encounter1B() {
        activeencounter = 1;
        textarea.setText("encounter1B");
    }

    private void encounter1A() {
        activeencounter = 0;
        textarea.setText("encounter1A");
    }

    private void fight3() {

        pole.setVisible(false);
        rollbutt.setVisible(true);
        if (bosstemp == 0) {
            enemyhealth = 200;
            textarea.setText("begin boss sequence1" + "\n" + "health: " + health + "\n" + "enemy health: " + enemyhealth );
        }
        if (bosstemp > 0 && health > 0 && enemyhealth > 0) {
            if (diceroll == 0) {
                rollbutt.setVisible(false);
                deathcause = "fight three instadeath";
                gameover();
            }
            if (diceroll == 19) {
                rollbutt.setVisible(false);
                fight3victory();
            }
            if (diceroll == 18 || diceroll == 17 || diceroll == 16) {
                enemyhealth = enemyhealth - 50;
            }
            if (diceroll == 15 || diceroll == 14 || diceroll == 13 || diceroll == 12) {
                enemyhealth = enemyhealth - 40;
                health = (health - 10) - weakness;
            }
            if (diceroll == 11 || diceroll == 10 || diceroll == 9 || diceroll == 8) {
                enemyhealth = enemyhealth - 30;
                health = (health - 25) - weakness;
            }
            if (diceroll == 7 || diceroll == 6 || diceroll == 5 || diceroll == 4) {
                enemyhealth = enemyhealth - 20;
                health = (health - 40) - weakness;
            }
            if (diceroll == 3 || diceroll == 2 || diceroll == 1) {
                enemyhealth = enemyhealth - 10;
                health = (health - 50) - weakness;
            }
            if (diceroll != 0 && diceroll != 19) {
                textarea.setText("you rolled " + (diceroll + 1) + "\n" + "enemy health: " + enemyhealth + "\n" + "health: " + health);
            }
        }
        if (health <= 0) {
            rollbutt.setVisible(false);
            deathcause = "fight three death";
            gameover();
        }
        if (enemyhealth <= 0) {fight3victory();}
    }


    private void fight3victory() {
        textarea.setText("you killed 3nd boss");
        rollbutt.setVisible(false);
        pole.setVisible(true);
    }

    private void fight1() {
        pole.setVisible(false);
        rollbutt.setVisible(true);
        if (bosstemp == 0) {
            enemyhealth = 200;
            textarea.setText("begin boss sequence1" + "\n" + "health: " + health + "\n" + "enemy health: " + enemyhealth );
        }
        if (bosstemp > 0 && health > 0 && enemyhealth > 0) {
            if (diceroll == 0) {
                rollbutt.setVisible(false);
                deathcause = "fight one instadeath";
                gameover();
            }
            if (diceroll == 19) {
                rollbutt.setVisible(false);
                fight1instavictory();
            }
            if (diceroll == 18 || diceroll == 17 || diceroll == 16) {
                enemyhealth = enemyhealth - 50;
            }
            if (diceroll == 15 || diceroll == 14 || diceroll == 13 || diceroll == 12) {
                enemyhealth = enemyhealth - 40;
                health = (health - 10) - weakness;
            }
            if (diceroll == 11 || diceroll == 10 || diceroll == 9 || diceroll == 8) {
                enemyhealth = enemyhealth - 30;
                health = (health - 25) - weakness;
            }
            if (diceroll == 7 || diceroll == 6 || diceroll == 5 || diceroll == 4) {
                enemyhealth = enemyhealth - 20;
                health = (health - 40) - weakness;
            }
            if (diceroll == 3 || diceroll == 2 || diceroll == 1) {
                enemyhealth = enemyhealth - 10;
                health = (health - 50) - weakness;
            }
            if (diceroll != 0 && diceroll != 19) {
                textarea.setText("you rolled " + (diceroll + 1) + "\n" + "enemy health: " + enemyhealth + "\n" + "health: " + health);
            }
        }
        if (health <= 0) {
            rollbutt.setVisible(false);
            deathcause = "fight one death";
            gameover();
        }
        if (enemyhealth <= 0) {fight1victory();}
    }

    private void fight1instavictory() {
        pole.setVisible(true);
        textarea.setText("fight 1 insta victory");
    }

    private void fight2() {
        pole.setVisible(false);
        rollbutt.setVisible(true);
        if (bosstemp == 0) {
            enemyhealth = 200;
            textarea.setText("begin boss sequence2" + "\n" + "health: " + health + "\n" + "enemy health: " + enemyhealth );
        }
        if (bosstemp > 0 && health > 0 && enemyhealth > 0) {
            if (diceroll == 0) {
                rollbutt.setVisible(false);
                deathcause = "fight two instadeath";
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
            deathcause = "fight two death";
            gameover();
            rollbutt.setVisible(false);
        }
        if (enemyhealth <= 0) {fight2victory();}
    }

    private void fight2instavictory() {
        pole.setVisible(true);
        rollbutt.setVisible(false);
        textarea.setText("fight 2 insta victory");
    }

    private void fight2victory() {
        textarea.setText("you killed 2nd boss" + "\n" + "health: " + health);
        rollbutt.setVisible(false);
        pole.setVisible(true);
        fightswon++;
        enemyhealth = 200;
        bosstemp = 0;
    }

    private void fight1victory() {
        textarea.setText("you killed 1st boss"  + "\n" + "health: " + health);
        rollbutt.setVisible(false);
        pole.setVisible(true);
        fightswon++;
        enemyhealth = 200;
        bosstemp = 0;
    }

    private void gamestart() {
        textarea.setText("you are on a noble quest to find your dream knight, located in rose castle." + "\n" + "But to get there, you must defeat the guards in each town on the way." + "\n" + "Are you ready to embark on your epic adventure? yes[a] no[b]");
    }

    private void gameover() {
        textarea.setText("GAME OVER!" + "\n" + deathcause + "\n" + "Enemies killed: " + kills);
        pole.setVisible(false);
        quitbutt.setVisible(true);
        tryagainbutt.setVisible(true);
    }
}