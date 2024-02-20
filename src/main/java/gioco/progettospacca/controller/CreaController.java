package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Bot;
import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreaController {
    public void BackToHome(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label lbl_code;
    @FXML
    private Button btn_crea;
    @FXML
    private TextField txt_gioc1;
    @FXML
    private TextField txt_gioc2;
    @FXML
    private TextField txt_gioc3;
    @FXML
    private TextField txt_gioc4;
    @FXML
    private TextField txt_gioc5;

    @FXML
    private CheckBox chk_gioc1;

    @FXML
    private CheckBox chk_gioc2;
    @FXML
    private CheckBox chk_gioc3;

    @FXML
    private CheckBox chk_gioc4;
    @FXML
    private CheckBox chk_gioc5;


    public void EventoCreaCodicePartita(ActionEvent actionEvent)
    {
        try {
            Giocatore temp;

            int id= Utili.intCasuale(10000,99999);

            lbl_code.setText(String.valueOf(id));

            List<Giocatore> g = new ArrayList<>();

            temp = Utili.controllaNome(txt_gioc1.getText(),id);
            if (temp!=null && !chk_gioc1.isSelected()) {
                g.add(temp);
            }
            if(chk_gioc1.isSelected()){
                g.add(new Bot(txt_gioc1.getText()));
            }

            temp = Utili.controllaNome(txt_gioc2.getText(),id);
            if (temp!=null && !chk_gioc2.isSelected()) {
                g.add(temp);
            }
            if(chk_gioc2.isSelected()){
                g.add(new Bot(txt_gioc2.getText()));
            }

            temp = Utili.controllaNome(txt_gioc3.getText(),id);
            if (temp!=null && !chk_gioc3.isSelected()) {
                g.add(temp);
            }
            if(chk_gioc3.isSelected()){
                g.add(new Bot(txt_gioc3.getText()));
            }

            temp = Utili.controllaNome(txt_gioc4.getText(),id);
            if (temp!=null && !chk_gioc4.isSelected()) {
                g.add(temp);
            }
            if(chk_gioc4.isSelected()){
                g.add(new Bot(txt_gioc4.getText()));
            }

            temp = Utili.controllaNome(txt_gioc5.getText(),id);
            if (temp!=null && !chk_gioc5.isSelected()) {
                g.add(temp);
            }
            if(chk_gioc5.isSelected()){
                g.add(new Bot(txt_gioc5.getText()));
            }
            Giocatore[] giocatori = g.toArray(new Giocatore[g.size()]);

            Partita p=new Partita(id,giocatori,false);

        }catch(Exception e)
        {
            System.out.println(e);
        }

    }


    public void checkBox1(ActionEvent actionEvent) {
        if(chk_gioc1.isSelected()){
            txt_gioc1.setDisable(true);
            txt_gioc1.setText("bot1");
        }
        else{
            txt_gioc1.setDisable(false);
            txt_gioc1.setText("");
        }
    }
    public void checkBox2(ActionEvent actionEvent) {
        if(chk_gioc2.isSelected()){
            txt_gioc2.setDisable(true);
            txt_gioc2.setText("bot2");
        }
        else{
            txt_gioc2.setDisable(false);
            txt_gioc2.setText("");
        }
    }
    public void checkBox3(ActionEvent actionEvent) {
        if(chk_gioc3.isSelected()){
            txt_gioc3.setDisable(true);
            txt_gioc3.setText("bot3");
        }
        else{
            txt_gioc3.setDisable(false);
            txt_gioc3.setText("");
        }
    }
    public void checkBox4(ActionEvent actionEvent) {
        if(chk_gioc4.isSelected()){
            txt_gioc4.setDisable(true);
            txt_gioc4.setText("bot4");
        }
        else{
            txt_gioc4.setDisable(false);
            txt_gioc4.setText("");
        }
    }
    public void checkBox5(ActionEvent actionEvent) {
        if(chk_gioc5.isSelected()){
            txt_gioc5.setDisable(true);
            txt_gioc5.setText("bot5");
            txt_gioc5.setText("");
        }
        else{
            txt_gioc5.setDisable(false);
            txt_gioc5.setText("");
        }
    }
}