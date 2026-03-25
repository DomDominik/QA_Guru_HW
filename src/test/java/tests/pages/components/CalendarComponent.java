package tests.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(String month, String year, String day){
        $(".react-datepicker__month-select").selectOption(month);// Выбираем месяц
        $(".react-datepicker__year-select").selectOption(year);// Выбираем год
        $(".react-datepicker__day.react-datepicker__day--005").click();// Выбираем день

    }
}
