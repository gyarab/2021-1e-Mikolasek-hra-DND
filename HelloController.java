package com.example.dnd2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class HelloController {
    boolean answer = true;
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
                if (encounternumber == 2) {encountertwo();}

        } else if (answr.contains("b")) {
            pole.clear();
            answer = false;
            encounternumber ++;
            if (encounternumber == 1) {encounterone();} else
            if (encounternumber == 2) {encountertwo();}
        } else {pole.clear();}
    }

    private void encountertwo() {
        textarea.setText("encountertwo");
    }

    private void gamestart() throws InterruptedException {
        textarea.setText("ZACATEK HRY + prvni vyber ano ne");

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