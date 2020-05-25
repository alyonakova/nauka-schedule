package space.banka.alyona.nauka.schedule.db.entities;

public enum Presence {

    /**
     * Я – полный рабочий день.
     */
    FULL_TIME_WORKDAY("Я"),

    /**
     * Н – отсутствие на рабочее место по невыясненным причинам.
     */
    ABSENCE("Н"),

    /**
     * В – выходные и праздничные дни.
     */
    HOLIDAY("В"),

    /**
     * Рв – работа в праздничные и выходные дни;
     * а также работа в праздничные и выходные дни
     * при нахождении в командировке.
     */
    WORK_ON_HOLIDAY("Рв"),

    /**
     * Б – дни временной нетрудоспособности.
     */
    DISABILITY("Б"),

    /**
     * К – командировочные дни,
     * а также выходные (нерабочие) дни при нахождении в командировке,
     * когда сотрудник отдыхает в соответствии с графиком
     * работы учреждения в командировке.
     */
    BUSINESS_TRIP("К"),

    /**
     * ОТ – ежегодный основной оплаченный отпуск.
     */
    VACATION_PAID("ОТ"),

    /**
     * До – неоплачиваемый отпуск (отпуск за свой счет).
     */
    VACATION_UNPAID("До"),

    /**
     * Хд – хозяйственный день.
     */
    CLEAN_UP_DAY("Хд"),

    /**
     * У – отпуск на период обучения.
     */
    VACATION_STUDY("У"),

    /**
     * Ож – отпуск по уходу за ребенком.
     */
    VACATION_CHILD("Ож"),

    DEFAULT(""),

    ;

    private final String letterCode;

    Presence(String letterCode) {
        this.letterCode = letterCode;
    }

    public String getLetterCode() {
        return letterCode;
    }
}
