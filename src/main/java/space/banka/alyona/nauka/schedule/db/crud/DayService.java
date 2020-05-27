package space.banka.alyona.nauka.schedule.db.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.banka.alyona.nauka.schedule.db.entities.Calendar;
import space.banka.alyona.nauka.schedule.db.entities.Day;
import space.banka.alyona.nauka.schedule.db.entities.DayType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
public class DayService {

    @Autowired
    private DayRepository dayRepository;

    public List<Day> findByYearAndMonth(Integer year, Integer month) {
        final List<Day> daysFromDatabase = dayRepository.findByYearYearAndIdMonth(year, month);
        final int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        final ArrayList<Day> days = new ArrayList<>();
        IntStream.range(1, daysInMonth).forEach(dayOfMonth -> {
            final Optional<Day> dayFromDatabase = findDayInList(daysFromDatabase, dayOfMonth);
            days.add(dayFromDatabase.orElseGet(() -> makeDefaultDay(year, month, dayOfMonth)));
        });
        return days;
    }

    public Day findOrCreate(int year, int month, int dayOfMonth) {
        return dayRepository.findById(new Day.Id(year, month, dayOfMonth))
                .orElseGet(() -> insertDefaultDay(year, month, dayOfMonth));
    }

    private Day insertDefaultDay(Integer year, Integer month, Integer dayOfMonth) {
        final Day day = makeDefaultDay(year, month, dayOfMonth);
        return dayRepository.save(day);
    }

    private Day makeDefaultDay(Integer year, Integer month, Integer dayOfMonth) {
        final Day.Id dayId = new Day.Id(year, month, dayOfMonth);
        DayType dayType = determineDayType(year, month, dayOfMonth);
        return Day.builder()
                .id(dayId)
                .type(dayType)
                .year(Calendar.builder().year(year).build())
                .build();
    }

    private DayType determineDayType(Integer year, Integer month, Integer dayOfMonth) {
        DayType dayType;
        final DayOfWeek dayOfWeek = LocalDate.of(year, month, dayOfMonth).getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            dayType = DayType.WEEKEND;
        } else {
            dayType = DayType.BUSINESS;
        }
        return dayType;
    }

    private Optional<Day> findDayInList(List<Day> daysFromDatabase, int dayOfMonth) {
        return daysFromDatabase.stream()
                .filter(day -> day.getId().getDay() == dayOfMonth)
                .findAny();
    }
}
