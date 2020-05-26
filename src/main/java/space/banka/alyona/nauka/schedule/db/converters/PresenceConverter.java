package space.banka.alyona.nauka.schedule.db.converters;

import org.springframework.stereotype.Component;
import space.banka.alyona.nauka.schedule.db.entities.Presence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * Converts entities' attributes of the type {@link Presence}
 * to their {@linkplain Presence#getLetterCode() letter codes}
 * for persisting in the database.
 */
@Converter(autoApply = true)
@Component
public class PresenceConverter implements AttributeConverter<Presence, String> {

    @Override
    public String convertToDatabaseColumn(Presence presence) {
        if (presence == null) {
            return null;
        }
        return presence.getLetterCode();
    }

    @Override
    public Presence convertToEntityAttribute(String letterCode) {
        if (letterCode == null) {
            return null;
        }
        return Stream.of(Presence.values())
                .filter(p -> p.getLetterCode().equals(letterCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown value of " + Presence.class + ": " + letterCode));
    }
}
