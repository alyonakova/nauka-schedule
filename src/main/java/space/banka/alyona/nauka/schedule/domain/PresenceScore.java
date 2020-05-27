package space.banka.alyona.nauka.schedule.domain;

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
        if (result.length() > 0 && result.charAt(result.length() - 1) == ';') {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
