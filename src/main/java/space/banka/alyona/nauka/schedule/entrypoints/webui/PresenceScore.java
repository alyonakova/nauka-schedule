package space.banka.alyona.nauka.schedule.entrypoints.webui;

import space.banka.alyona.nauka.schedule.db.entities.Presence;

import java.util.Map;

public class PresenceScore {

    Map<Presence, Integer> scores;

    public PresenceScore(Map<Presence, Integer> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Presence, Integer> entry : scores.entrySet()) {
            result.append(entry.getKey().getLetterCode() + "(" + entry.getValue() + ");");
        }
        return result.toString();
    }
}
