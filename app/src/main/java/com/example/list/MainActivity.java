package com.example.list;

import android.content.Intent;
import android.os.Bundle;
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
    private ListView Camera;
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

        Camera = findViewById(R.id.ListTours);
        selection = findViewById(R.id.Selection);

        String[] printers = getResources().getStringArray(R.array.cameras);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, printers);
        Camera.setAdapter(adapter);

        Camera.setOnItemClickListener((adapterView, view, position, id) -> {
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
                "Canon EOS R5",
                "Опис: Повнокадрова бездзеркальна камера високого рівня з роздільною здатністю 45 МП, можливістю запису 8K-відео і системою Dual Pixel CMOS AF II. Ідеально підходить для професійної фотозйомки.",
                "Роздільна здатність: 45 МП",
                "Особливості: 8K-відео, швидкість серійної зйомки 20 кадрів/сек, захист від пилу і вологи."
        });

        information.add(new String[]{
                "Nikon Z7 II",
                "Опис: Повнокадрова бездзеркальна камера з роздільною здатністю 45.7 МП і подвійним процесором Expeed 6. Забезпечує чудову деталізацію і підходить для професійних фотографів.",
                "Роздільна здатність: 45.7 МП",
                "Особливості: 4K UHD-відео, подвійний слот для карт пам'яті, покращений автофокус."
        });

        information.add(new String[]{
                "Sony A7 IV",
                "Опис: Універсальна повнокадрова камера з роздільною здатністю 33 МП, підходить для фото і відеозйомки з високою деталізацією. Має передову систему автофокусу.",
                "Роздільна здатність: 33 МП",
                "Особливості: 4K-відео до 60fps, система автофокусу з AI, інтегрована стабілізація."
        });

        information.add(new String[]{
                "Canon EOS-1D X Mark III",
                "Опис: Професійна дзеркальна камера з роздільною здатністю 20.1 МП, орієнтована на спортивну і репортажну зйомку. Має потужний автофокус і тривалий час роботи від акумулятора.",
                "Роздільна здатність: 20.1 МП",
                "Особливості: 5.5K RAW-відео, швидкість зйомки 16 кадрів/сек, захист від пилу і вологи."
        });

        information.add(new String[]{
                "Canon EOS Rebel T8i",
                "Опис: Компактна і легка дзеркальна камера з роздільною здатністю 24.1 МП, підходить для аматорів і початківців. Пропонує широкий спектр можливостей для фото і відеозйомки.",
                "Роздільна здатність: 24.1 МП",
                "Особливості: 4K-відео, Dual Pixel AF, зручний сенсорний екран."
        });


        images.add(R.drawable.canon);      // Example image for Krakow
        images.add(R.drawable.nikon);      // Example image for Tatra Mountains
        images.add(R.drawable.sony);      // Example image for Warsaw
        images.add( R.drawable.canon1);   // Example image for Wieliczka Salt Mines
        images.add(R.drawable.canon2);    // Example image for Zakopane
    }


    private String formatTourInfo(String[] tourDetails) {
        StringBuilder infoBuilder = new StringBuilder();
        for (String detail : tourDetails) {
            infoBuilder.append(detail).append("\n");
        }
        return infoBuilder.toString().trim();
    }
}
