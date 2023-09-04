import java.util.ArrayList;
import java.util.List;

class Date {
    int day, month, year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}

public class DateSortingWithoutInbuilt {
    public static void main(String[] args) {
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(15, 7, 2023));
        dates.add(new Date(25, 12, 2022));
        dates.add(new Date(5, 4, 2023));
        dates.add(new Date(10, 9, 2022));

        sortDates(dates);

        for (Date date : dates) {
            System.out.printf("%02d %02d %04d\n", date.day, date.month, date.year);
        }
    }

    private static void sortDates(List<Date> dates) {
        int n = dates.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareDates(dates.get(j), dates.get(j + 1)) > 0) {
                    swap(dates, j, j + 1);
                }
            }
        }
    }

    private static int compareDates(Date date1, Date date2) {
        if (date1.year != date2.year) {
            return date1.year - date2.year;
        }
        if (date1.month != date2.month) {
            return date1.month - date2.month;
        }
        return date1.day - date2.day;
    }

    private static void swap(List<Date> dates, int i, int j) {
        Date temp = dates.get(i);
        dates.set(i, dates.get(j));
        dates.set(j, temp);
    }
}
