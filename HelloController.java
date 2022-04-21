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
    private Button newgamebutt;

    @FXML
    private Label gamelabel;

    @FXML
    private Button rollbutt;

    @FXML
    protected void onroll() {
        diceroll = rand.nextInt(19);
        bosstemp ++;
        fightone();
    }

    @FXML
    protected void onnewgame() throws InterruptedException {
        gamestart();
        quitbutt.setVisible(false);
        newgamebutt.setVisible(false);
        pole.setVisible(true);
        textarea.setVisible(true);
        gamelabel.setVisible(false);
    }

    @FXML
    protected void onquit() {
        Platform. exit();
    }

    @FXML
    protected void onenter() {
        String answr = pole.getText();

        if (answr.contains("a")) {
            pole.clear();
            answer = true;
            encounternumber ++;
            if (encounternumber == 1) {encounterone();} else
            if (encounternumber == 2) {encounteroneresulta();} else
            if (encounternumber == 3) {fightone();} else
            if (encounternumber == 4) {encountertwo();} else
            if (encounternumber == 5) {encountertworesulta();}

        } else if (answr.contains("b")) {
            pole.clear();
            answer = false;
            encounternumber ++;
            if (encounternumber == 1) {encounterone();} else
            if (encounternumber == 2) {encounteroneresultb();} else
            if (encounternumber == 3) {fightone();} else
            if (encounternumber == 4) {encountertwo();} else
            if (encounternumber == 5) {encountertworesultb();}  

        } else {pole.clear();}
    }

    private void encountertworesultb() {textarea.setText("encounteroneresultb");}

    private void encountertworesulta() {textarea.setText("encounteroneresulta");}

    private void fightone() {
        pole.setVisible(false);
        rollbutt.setVisible(true);
        if (bosstemp == 0) {
            textarea.setText("begin boss sequence" + "\n" + "health: " + health + "\n" + "enemy health: " + enemyhealth );
        }
        if (bosstemp > 0 && health > 0 && enemyhealth > 0) {
            enemyhealth = enemyhealth - (diceroll*10) + damageboost;
            textarea.setText("you rolled " + (diceroll + 1) + "\n" + "enemy health: " + enemyhealth);
        }
        if (health <= 0) {gameover();}
        if (enemyhealth <= 0) {fightonevictory();}
    }

    private void fightonevictory() {
        textarea.setText("you killed 1st boss");
        rollbutt.setVisible(false);
        pole.setVisible(true);
    }

    private void encounteroneresulta() {
        textarea.setText("encounteroneresulta");
    }

    private void encounteroneresultb() {
        textarea.setText("encounteroneresultb");
    }

    private void encountertwo() {
        textarea.setText("encountertwo");
    }

    private void gamestart() throws InterruptedException {
        textarea.setText("gamestart continue[a] quit[b]");
    }



    private void encounterone() {
        textarea.setText("encounterone");
    }

    private void gameover() {
        textarea.setText("GAME OVER!" + "\n" + deathcause + "\n" + "Enemies killed: " + kills);
        pole.setVisible(false);
        quitbutt.setVisible(true);
    }
}