package com.negaraberdaulat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    AutoCompleteTextView autocomplete;

    String mTitle[] = {"Amerika Serikat", "Argentina", "Australia", "Belanda", "Belgia", "Brazil", "China", "India", "Indonesia", "Jepang", "Jerman"};
    String mDescription[] = {"Amerika Description", "Argentina Description", "Australia Description", "Belanda Description", "Belgia Description", "Brazil Description", "China Description", "India Description", "Indonesia Description", "Jepang Description", "Jerman Description"};
    int images[] = {R.drawable.amerika, R.drawable.argentina, R.drawable.australia, R.drawable.belanda, R.drawable.belgia, R.drawable.brazil, R.drawable.china, R.drawable.india, R.drawable.indonesia, R.drawable.jepang, R.drawable.jerman,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//  Bagian AutoCompleteText
        autocomplete = findViewById(R.id.autocomplete);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, mTitle);
        autocomplete.setAdapter(adapter2);

        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaNegara = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Anda memilih: " +namaNegara, Toast.LENGTH_SHORT).show();
            }
        });

//  Bagian Spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String negara = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Anda klik negara: " +negara, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//  Bagian ListView
        listView = findViewById(R.id.listView);

        MyAdapter adapter1 = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String negara1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Anda klik negara: " +negara1, Toast.LENGTH_SHORT).show();
            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return row;
        }
    }
    }

