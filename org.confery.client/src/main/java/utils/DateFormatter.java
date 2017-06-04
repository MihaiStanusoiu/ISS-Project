package utils;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DateFormatter {

    private Date date;
    private TextField day;
    private ChoiceBox<String> month;
    private TextField year;

    public DateFormatter setDate(Date date) {
        this.date = date;
        return this;
    }

    public DateFormatter setDayField(TextField day) {
        this.day = day;
        return this;
    }

    public DateFormatter setYearField(TextField year) {
        this.year = year;
        return this;
    }

    public DateFormatter setMonthBox(ChoiceBox<String> month) {
        this.month = month;
        return this;
    }

    public void show() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        day.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        month.getSelectionModel().select(calendar.get(Calendar.MONTH));
    }

}
