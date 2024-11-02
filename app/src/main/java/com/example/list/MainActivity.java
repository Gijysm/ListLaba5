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
                "Тур до Говерли",
                "Опис: Цей тур включає підйом на найвищу вершину України - Говерлу, що має висоту 2061 метр над рівнем моря. Мандрівники зможуть насолоджуватися чудовими краєвидами та свіжим гірським повітрям.",
                "Тривалість: 1 день",
                "Додаткові послуги: гід, трансфер, обід."
        });

        information.add(new String[]{
                "Поход до озера Синевир",
                "Опис: Походи до найглибшого озера України - Синевир, яке оточене мальовничими горами та хвойними лісами. Ідеальне місце для любителів природи та фотомиттєвостей.",
                "Тривалість: 2 дні",
                "Додаткові послуги: гід, кемпінг."
        });

        information.add(new String[]{
                "Екскурсія до Яремче",
                "Опис: Яремче - це популярний курорт, відомий своїми водоспадами, дерев’яними церквами та культурними традиціями. Тур включає відвідування місцевих майстерень та дегустацію карпатської кухні.",
                "Тривалість: 1 день",
                "Додаткові послуги: транспорт, обід."
        });

        information.add(new String[]{
                "Відвідування Карпатського національного парку",
                "Опис: Екскурсія до Карпатського національного парку, де мандрівники можуть насолодитися прогулянками по стежках, спостерігати за дикими тваринами та відвідувати природні пам'ятки.",
                "Тривалість: 3 дні",
                "Додаткові послуги: гід, проживання."
        });

        information.add(new String[]{
                "Зимовий відпочинок на Буковелі",
                "Опис: Популярний зимовий курорт з лижними трасами, термальними водами та розвиненою інфраструктурою. Пропонуємо різноманітні активності для любителів зимових видів спорту.",
                "Тривалість: 5 днів",
                "Додаткові послуги: прокат спорядження, уроки катання на лижах."
        });

        images.add(                        R.drawable.hoverla);
        images.add(                  R.drawable.licensedimage);
        images.add(                            R.drawable.pxl);
        images.add(                           R.drawable.park);
        images.add(R.drawable.d6e68a915c14f2ace79d15d1033b014);
    }

    private String formatTourInfo(String[] tourDetails) {
        StringBuilder infoBuilder = new StringBuilder();
        for (String detail : tourDetails) {
            infoBuilder.append(detail).append("\n");
        }
        return infoBuilder.toString().trim();
    }
}
