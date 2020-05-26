package space.banka.alyona.nauka.schedule.entrypoints.webui.objects;

import lombok.Value;

import java.time.format.TextStyle;
import java.util.Locale;

@Value
public class Month {

    private int value;

    private String name;

    public static Month fromJavaTime(java.time.Month month) {
        return new Month(
                month.getValue(),
                month.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru", "ru")));
    }
}
