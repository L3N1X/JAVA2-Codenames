package hr.algebra.codenames.model.singleton;

import hr.algebra.codenames.model.TurnLog;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class GameLogger implements Serializable {
    // TODO: 8.11.2022. Game logger is serialized separately from GameState
    private final List<TurnLog> turnLogs;
    private static GameLogger instance;
    private GameLogger(){
        this.turnLogs = new LinkedList<>();
    }

    public static GameLogger getInstance(){
        if(instance == null)
            instance = new GameLogger();
        return instance;
    }

    @Serial
    public Object readResolve() {
        return getInstance( );
    }

    public List<TurnLog> getTurnLogs() {
        return new LinkedList<>(this.turnLogs);
    }

    public void addTurnLog(TurnLog turnLog){
        this.turnLogs.add(turnLog);
    }
}
