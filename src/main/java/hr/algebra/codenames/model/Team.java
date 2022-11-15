package hr.algebra.codenames.model;

import hr.algebra.codenames.model.enums.PlayerRole;
import hr.algebra.codenames.model.enums.CardColor;
import hr.algebra.codenames.model.singleton.GameSettings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

public class Team implements Serializable {
    private final CardColor cardType;
    private final Boolean startedFirst;
    private Integer points;
    private final Player spymaster;
    private final Player operative;

    public Team(CardColor cardType, Boolean isStartingFirst, String spymasterName, String operativeName) {
        this.cardType = cardType;
        this.startedFirst = isStartingFirst;
        this.points = this.startedFirst ? GameSettings.STARTING_FIRST_CARD_COUNT : GameSettings.STARTING_LAST_CARD_COUNT;
        this.spymaster = new Player(spymasterName, this.cardType, PlayerRole.Spymaster);
        this.operative = new Player(operativeName, this.cardType, PlayerRole.Operative);
    }
    public CardColor getTeamColor(){
        return this.cardType;
    }

    public Player getOperative() {
        return operative;
    }

    public Player getSpymaster() {
        return spymaster;
    }

    public Integer getPoints() {
        return points;
    }

    public void decrementPoints() {
        //this.points.set(points.get() - 1);
        this.points--;
    }
}
