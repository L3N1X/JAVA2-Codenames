package hr.algebra.codenames.model.singleton;

import java.io.Serializable;

public class GameSettings implements Serializable {
    private GameSettings() {}

    //region Resource paths
    public static final String RED_TEAM_IMAGE_PATH = "assets/red_player.png";
    public static final String BLUE_TEAM_IMAGE_PATH = "assets/player_blue.png";
    public static final String ICON_PATH = "assets/codenames_icon.png";
    //endregion

    //region FXML paths
    public static final String SPYMASTER_VIEW_PATH = "view/spymasterGameScreen.fxml";
    public static final String OPERATIVE_VIEW_PATH = "view/operativeGameScreen.fxml";
    public static final String START_VIEW_PATH = "view/startGameScreen.fxml";
    public static final String HIGHSCORE_VIEW_PATH = "view/highscoreGameScreen.fxml";
    public static final String LOGS_VIEW_PATH = "view/logsGameScreen.fxml";
    public static final String GAME_WINNER_VIEW_PATH = "view/winnerGameScreen.fxml";
    //endregion

    //region Game texts

    public static final String GAME_TITLE = "Codenames Java Edition BETA";
    public static final String LOGS_TITLE = "Game logs";
    public static final String HIGHSCORE_TITLE = "Highscore stats";

    //endregion

    //region Game logic constants
    public static final int STARTING_FIRST_CARD_COUNT = 9;
    public static final int STARTING_LAST_CARD_COUNT = 8;
    public static final int PASSANGER_CARD_COUNT = 7;
    public static final int KILLER_CARD_COUNT = 1;
    public static final int SPYMASTER_TURN_DURATION = 60;
    public static final int OPERATIVE_TURN_DURATION = 60;
    public static final int PLAYER_COUNT = 4;
    //endregion

    //region Game CSS classes constants
    public static final String CARD_KILLER_CSS = "card_black";
    public static final String CARD_KILLER_GUESSED_CSS = "card_black_guessed";
    public static final String CARD_RED_CSS = "card_red";
    public static final String CARD_RED_GUESSED_CSS = "card_red_guessed";
    public static final String CARD_BLUE_CSS = "card_blue";
    public static final String CARD_BLUE_GUESSED_CSS = "card_blue_guessed";
    public static final String CARD_PASSANGER_CSS = "card_passanger";
    public static final String CARD_PASSANGER_GUESSED_CSS = "card_passanger_guessed";
    public static final String CARD_DEFAULT_CSS = "card";
    public static final String CARD_SELECTED_CSS = "card_selected";
    public static final String GREEN_TEXT_CSS = "green";
    public static final String RED_TEXT_CSS = "red";
    public static final String BLUE_TEXT_CSS = "blue";
    public static final String BLUE_PANE_CSS = "blue_pane";
    public static final String RED_PANE_CSS = "red_pane";
    public static final String RED_BUTTON_CSS = "btn_red";
    public static final String BLUE_BUTTON_CSS = "btn_blue";
    public static final String GREY_BUTTON_CSS = "btn_grey";
    //endregion
}
