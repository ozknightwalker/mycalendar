package com.example.oz_fanalis.mycalendar;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Toast;

import com.example.oz_fanalis.mycalendar.Decorators.EventDecorator;
import com.example.oz_fanalis.mycalendar.Decorators.HighlightWeekendsDecorator;
import com.example.oz_fanalis.mycalendar.Decorators.MySelectorDecorator;
import com.example.oz_fanalis.mycalendar.Decorators.OneDayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import static com.example.oz_fanalis.mycalendar.R.drawable.mcv_action_previous;

public class MainActivity extends AppCompatActivity implements OnDateSelectedListener {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    MaterialCalendarView widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        widget  = (MaterialCalendarView) findViewById(R.id.calendar);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_DEFAULTS);


        Calendar calendar = Calendar.getInstance();

        widget.setSelectedDate(calendar.getTime());
        widget.setSelectionColor(R.color.colorAccent);
//
//        widget.addDecorators(
//                new MySelectorDecorator(this),
//                oneDayDecorator
//        );
        widget.setOnDateChangedListener(this);
        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
    }
    @Override
    public void onDateSelected( MaterialCalendarView widget, CalendarDay date, boolean selected) {
//        Toast.makeText(getApplicationContext(), date.getDate().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Activity_List.class);
        startActivity(intent);
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,0);
            ArrayList<CalendarDay> dates = new ArrayList<>();

            CalendarDay day = CalendarDay.from(calendar);
            dates.add(day);
//            calendar.add(Calendar.DATE, 5);

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            widget.addDecorator(new EventDecorator(Color.GREEN, calendarDays));
        }
    }

}
