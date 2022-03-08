package com.example.dnd1;

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("new game? Yes / No");
        Scanner sc = new Scanner (System.in);
        String answr = sc.nextLine();
        if (answr.contains("n")) {
            System.out.println("ok");
            launch();
        } else if (answr.contains("y")) {
            System.out.println("loading...");
            //Thread.sleep(2000);
            System.out.println("done");
           // Thread.sleep(500);
            System.out.println("in this game, many decisions are to be made, so choose carefully... or don't, that's up to you.");
            //Thread.sleep(2000);
            System.out.println("your first decision stands before you. Do you want to become the most badass hero ever? Yes / No");
            answr = sc.nextLine();
            if (answr.contains("n")) {
                System.out.println("in another life i guess..");
                //Thread.sleep(500);
                launch();
            } else if (answr.contains("y")) {
                System.out.println("good choice! but that's not enough to make you the best! here's a sword and some armor.");
                System.out.println("\033[3mreceived sword\033[0m");
                System.out.println("\033[3mreceived armor\033[0m");
                //Thread.sleep(500);
                System.out.println("now you look like a true hero! Now, let us begin your story.");
            }
        }
    }
}