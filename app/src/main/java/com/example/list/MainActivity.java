package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private ListView Printers;
    private TextView selection;
    private Vector<String[]> information = new Vector<>();
    private Vector<Integer> images = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initializeData();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Printers = findViewById(R.id.ListTours);
        selection = findViewById(R.id.Selection);

        String[] printers = getResources().getStringArray(R.array.tours);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, printers);
        Printers.setAdapter(adapter);

        Printers.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedItem = printers[position];
            selection.setText(selectedItem);

            String infoString = formatTourInfo(information.get(position));

            Intent intent = new Intent(MainActivity.this, SelectedActivity.class);
            intent.putExtra("Name", selectedItem);
            intent.putExtra("Image", images.get(position));
            intent.putExtra("Info", infoString);
            startActivity(intent);
        });
    }

    private void initializeData() {
        information.add(new String[]{
                "Екскурсія до старого міста Кракова",
                "Опис: Відвідування історичного центру Кракова, зокрема площі Ринок, де можна побачити Маріацький костел і Вавельський замок. Прогулянка старовинними вуличками та знайомство з багатою культурною спадщиною.",
                "Тривалість: 1 день",
                "Додаткові послуги: гід, обід, трансфер."
        });

        information.add(new String[]{
                "Поход до Татр",
                "Опис: Захоплюючий похід по польській частині гір Татри, де відкриваються неймовірні пейзажі і можна відчути дотик дикої природи.",
                "Тривалість: 2 дні",
                "Додаткові послуги: гід, кемпінг, харчування."
        });

        information.add(new String[]{
                "Екскурсія до Варшави",
                "Опис: Відвідування столиці Польщі з оглядом її сучасної та історичної архітектури, включаючи Старе місто, палаци та музеї.",
                "Тривалість: 1 день",
                "Додаткові послуги: транспорт, обід, екскурсійний супровід."
        });

        information.add(new String[]{
                "Відвідування соляних копалень у Величці",
                "Опис: Екскурсія по соляних шахтах Велички – унікальному об’єкту зі скульптурами і каплицями, що створені з солі. Ідеальний варіант для любителів історії та мистецтва.",
                "Тривалість: 1 день",
                "Додаткові послуги: гід, трансфер, сувеніри."
        });

        information.add(new String[]{
                "Зимовий відпочинок в Закопаному",
                "Опис: Відпочинок у гірському курорті Закопане – центрі зимових видів спорту. Включає катання на лижах, сауну, термальні джерела.",
                "Тривалість: 5 днів",
                "Додаткові послуги: прокат спорядження, уроки катання, проживання."
        });

        images.add(R.drawable.krakow);      // Example image for Krakow
        images.add(R.drawable.tatras);      // Example image for Tatra Mountains
        images.add(R.drawable.warsaw);      // Example image for Warsaw
        images.add( R.drawable.wawel_castle);   // Example image for Wieliczka Salt Mines
        images.add(R.drawable.baltic_sea);    // Example image for Zakopane
    }


    private String formatTourInfo(String[] tourDetails) {
        StringBuilder infoBuilder = new StringBuilder();
        for (String detail : tourDetails) {
            infoBuilder.append(detail).append("\n");
        }
        return infoBuilder.toString().trim();
    }
}
