package hr.algebra.codenames.controller;

import hr.algebra.codenames.CodenamesApplication;
import hr.algebra.codenames.model.Card;
import hr.algebra.codenames.model.enums.CardColor;
import hr.algebra.codenames.model.singleton.GameState;
import hr.algebra.codenames.model.singleton.GlobalGameSettings;
import hr.algebra.codenames.utils.DialogUtils;
import hr.algebra.codenames.utils.FXMLLoaderUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.*;


public class SpymasterGameController {

    //region FXML controls
    @FXML
    private Label lblRedOperative;
    @FXML
    private Label lblRedSpymaster;
    @FXML
    private Label lblBlueOperative;
    @FXML
    private Label lblBlueSpymaster;
    @FXML
    private Label lblCountdown;
    @FXML
    private Label lblCardCount;
    @FXML
    private Text lblRedPoints;
    @FXML
    private Text lblBluePoints;
    @FXML
    private TextField tfClue;
    @FXML
    private GridPane cardsGrid;
    //endregion
    private HashMap<String, Card> cards;
    private int secondsLeft = GlobalGameSettings.SPYMASTER_TURN_DURATION;
    private Integer wordCount;
    private List<String> selectedWords;

    public SpymasterGameController() {
        this.wordCount = 0;
    }
    private void updateCardCounter(Integer delta) {
        this.wordCount += delta;
        this.lblCardCount.setText(this.wordCount.toString());
    }
    @FXML
    private void giveClueClick(){
        if(!formValid())
            return;
        try {
            GameState.toOperatorTurn(this.tfClue.getText(), this.wordCount);
            FXMLLoaderUtils.loadScreen(CodenamesApplication.getMainStage(), "Codenames Java Edition", "view/operativeGameScreen.fxml");
        } catch (IOException e) {
            DialogUtils.showErrorDialog("Error", "Fatal application error", e.getMessage());
        }
    }

    private boolean formValid() {
        String message = "";
        boolean valid = true;
        if(this.wordCount == 0){
            message += "You must select one or more words. ";
            valid = false;
        }
        if(this.tfClue.getText().isBlank()){
            message += "You must give clue to your operative";
            valid = false;
        }
        if(!message.isEmpty())
            DialogUtils.showInformationDialog("", "Problem!", message);
        return valid;
    }

    private void cardSelected(ActionEvent actionEvent){
        Button selectedCard = (Button)actionEvent.getSource();
        if(cards.get(selectedCard.getText()).getColor() == CardColor.Killer
                || cards.get(selectedCard.getText()).getColor() == CardColor.Passanger)
            return;
        if(cards.get(selectedCard.getText()).getColor() != GameState.getCurrentTeam().getTeamColor())
            return;
        if(selectedCard.getStyleClass().contains(GlobalGameSettings.CARD_SELECTED_CSS)){
            selectedCard.getStyleClass().removeAll(GlobalGameSettings.CARD_SELECTED_CSS);
            this.updateCardCounter(-1);
            this.selectedWords.remove(selectedCard.getText());
            return;
        }
        selectedCard.getStyleClass().add(GlobalGameSettings.CARD_SELECTED_CSS);
        this.selectedWords.add(selectedCard.getText());
        this.updateCardCounter(1);
    }

    @FXML
    protected void initialize() {
        this.selectedWords = new ArrayList<>();
        initializeLabels();
        updateCardCounter(0);
        initializeCards();
        startCountdown();
    }

    private void initializeLabels() {
        this.lblRedOperative.setText(GameState.getRedTeam().getOperative().getName());
        this.lblRedSpymaster.setText(GameState.getRedTeam().getSpymaster().getName());
        this.lblBlueOperative.setText(GameState.getBlueTeam().getOperative().getName());
        this.lblBlueSpymaster.setText(GameState.getBlueTeam().getSpymaster().getName());

        if(GameState.getCurrentTeam().getTeamColor() == CardColor.Red)
            this.lblRedSpymaster.setText(this.lblRedSpymaster.getText() + " (YOU)");
        else
            this.lblBlueSpymaster.setText(this.lblBlueSpymaster.getText() + " (YOU)");

        this.lblRedPoints.setText(String.valueOf(GameState.getRedTeam().getPoints()));
        this.lblBluePoints.setText(String.valueOf(GameState.getBlueTeam().getPoints()));
    }

    private void initializeCards() {
        List<Button> physicalCards = new ArrayList<>();
        for(Node component : this.cardsGrid.getChildren()){
            if(component instanceof Button){
                physicalCards.add((Button)component);
                ((Button)component).setOnAction(this::cardSelected);
            }
        }
        this.cards = GameState.getCardsMap();
        int i = 0;
        for (Card card : cards.values()) {
            physicalCards.get(i).setText(card.getWord());
            physicalCards.get(i).getStyleClass().clear();
            if(card.getIsGuessed()){
                physicalCards.get(i).setText("");
                if(card.getColor() == CardColor.Red)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_RED_GUESSED_CSS);
                else if (card.getColor() == CardColor.Blue)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_BLUE_GUESSED_CSS);
                else if (card.getColor() == CardColor.Passanger)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_PASSANGER_GUESSED_CSS);
                else if (card.getColor() == CardColor.Killer)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_KILLER_GUESSED_CSS);
            } else {
                if(card.getColor() == CardColor.Red)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_RED_CSS);
                else if (card.getColor() == CardColor.Blue)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_BLUE_CSS);
                else if (card.getColor() == CardColor.Passanger)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_PASSANGER_CSS);
                else if (card.getColor() == CardColor.Killer)
                    physicalCards.get(i).getStyleClass().addAll(GlobalGameSettings.CARD_KILLER_CSS);
            }
            physicalCards.get(i).setTextAlignment(TextAlignment.CENTER);
            i++;
        }
    }

    private void startCountdown() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    secondsLeft--;
                    lblCountdown.setText(secondsLeft + "s");
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0 , 1000);
    }
}
