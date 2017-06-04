package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import transfarable.Edition;
import utils.DateFormatter;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ControllerEdition {

    @FXML
    protected TextField startingDateDay;

    @FXML
    protected ChoiceBox<String> startingDateMonth;

    @FXML
    protected TextField startingDateYear;

    @FXML
    protected TextField endingDateDay;

    @FXML
    protected ChoiceBox<String> endingDateMonth;

    @FXML
    protected TextField endingDateYear;

    @FXML
    protected TextField abstractDateDay;

    @FXML
    protected ChoiceBox<String> abstractDateMonth;

    @FXML
    protected TextField abstractDateYear;

    @FXML
    protected TextField paperDateDay;

    @FXML
    protected ChoiceBox<String> paperDateMonth;

    @FXML
    protected TextField paperDateYear;

    @FXML
    protected TextField biddingDateDay;

    @FXML
    protected ChoiceBox<String> biddingDateMonth;

    @FXML
    protected TextField biddingDateYear;

    @FXML
    protected TextField evaluationDateDay;

    @FXML
    protected ChoiceBox<String> evaluationDateMonth;

    @FXML
    protected TextField evaluationDateYear;


    protected void showDates(Edition edition) {
        new DateFormatter().setDate(edition.getStartDate()).setDayField(startingDateDay)
                .setMonthBox(startingDateMonth).setYearField(startingDateYear).show();
        new DateFormatter().setDate(edition.getEndDate()).setDayField(endingDateDay)
                .setMonthBox(endingDateMonth).setYearField(endingDateYear).show();
        new DateFormatter().setDate(edition.getAbstractDeadline()).setDayField(abstractDateDay)
                .setMonthBox(abstractDateMonth).setYearField(abstractDateYear).show();
        new DateFormatter().setDate(edition.getPaperDeadline()).setDayField(paperDateDay)
                .setMonthBox(paperDateMonth).setYearField(paperDateYear).show();
        new DateFormatter().setDate(edition.getBiddingDeadline()).setDayField(biddingDateDay)
                .setMonthBox(biddingDateMonth).setYearField(biddingDateYear).show();
        new DateFormatter().setDate(edition.getEvaluationDeadline()).setDayField(evaluationDateDay)
                .setMonthBox(evaluationDateMonth).setYearField(evaluationDateYear).show();
    }

}
