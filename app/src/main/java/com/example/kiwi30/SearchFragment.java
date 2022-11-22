package com.example.kiwi30;

import android.R.layout;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    Button button6;
    EditText editTextTextPersonName6, editTextTextPersonName7, editTextTextPersonName8, editTextDate, editTextDate2;
    Spinner spinner;
    String[] classes = { "Economy", "Premium Economy",
            "Business", "First Class"};
    String classString = "";
    SearchListFragment searchListFragment = new SearchListFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.d("PRESSED", "1");
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        button6 =  (Button) view.findViewById(R.id.button6);
        editTextTextPersonName6 =  (EditText) view.findViewById(R.id.editTextTextPersonName6);
        editTextTextPersonName7 =  (EditText) view.findViewById(R.id.editTextTextPersonName7);
        editTextTextPersonName8 =  (EditText) view.findViewById(R.id.editTextTextPersonName8);
        editTextDate =  (EditText) view.findViewById(R.id.editTextDate);
        editTextDate2 =  (EditText) view.findViewById(R.id.editTextDate2);
        spinner =  (Spinner) view.findViewById(R.id.spinner);
        button6.setOnClickListener( this);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter aD = new ArrayAdapter(getActivity(), layout.simple_spinner_item, classes);
        aD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aD);

//        {"query":{"market":"UK","locale":"en-GB","currency":"GBP","query_legs":[{"origin_place_id":{"iata":"LHR"},"destination_place_id":{"iata":"SIN"},"date":{"year":2022,"month":12,"day":22}}],"adults":1,"cabin_class":"CABIN_CLASS_ECONOMY"}}'
        return view;
    }

    @Override
    public void onClick(View view) {
        Log.d("PRESSED", "2");
        String fromLocation = editTextTextPersonName6.getText().toString();
        String toLocation = editTextTextPersonName7.getText().toString();
        String numberOfAdults = editTextTextPersonName8.getText().toString();

        String depatureDate = editTextDate.getText().toString();
        if(TextUtils.isEmpty(depatureDate)) {
            Toast.makeText(getActivity(), "Enter depature date!", Toast.LENGTH_SHORT).show();
        } else {
            if(depatureDate.contains("/")){
                String[] items= depatureDate.split("/");
                depatureDate = items[2]+"-"+items[1]+"-"+items[0];
            }
            else if(depatureDate.contains("-")){
                String[] items= depatureDate.split("-");
                depatureDate = items[2]+"-"+items[1]+"-"+items[0];
            }
        }


        String returnDate = editTextDate2.getText().toString();
        if(TextUtils.isEmpty(returnDate)) {
            Toast.makeText(getActivity(), "Enter return date!", Toast.LENGTH_SHORT).show();
        } else {
            if(returnDate.contains("/")){
                String[] itemsTwo= returnDate.split("/");
                returnDate = itemsTwo[2]+"-"+itemsTwo[1]+"-"+itemsTwo[0];
            }
            else{
                String[] itemsTwo= returnDate.split("-");
                returnDate = itemsTwo[2]+"-"+itemsTwo[1]+"-"+itemsTwo[0];
            }
        }

        String urlConcatination = String.format("https://skyscanner44.p.rapidapi.com/search?adults=%s&origin=%s&destination=%s&departureDate=%s&returnDate=%s&cabinClass=%s&currency=EUR", numberOfAdults, fromLocation, toLocation, depatureDate, returnDate, classString);
        Log.d("urlConcatination", urlConcatination);

        //https://skyscanner44.p.rapidapi.com/search?adults=1&origin=MUC&destination=BER&departureDate=2022-11-25&returnDate=2022-11-29&currency=EUR
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(urlConcatination)
                            .get()
                            .addHeader("X-RapidAPI-Key", "f0778c8a1cmsh69e2898eec3cea7p1e9eccjsn137222207888")
                            .addHeader("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com")
                            .build();

                        Response response = client.newCall(request).execute();
                        String stringResponse = response.body().string();
//                        Log.d("stringResponse", stringResponse);


                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, searchListFragment);
                    fragmentTransaction.commit();

                    Bundle bundle = new Bundle();
                    bundle.putString("key", stringResponse);
                    searchListFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.frame_layout, searchListFragment).commit();

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        classString = classes[i];
        Log.d("classString", classString);

        if(classString.equals("Economy")){
            classString = "economy";
        }
        else if(classString.equals("Premium Economy")){
            classString = "premiumeconomy";

        }
        else if(classString.equals("Business")){
            classString = "business";
        }
        else if(classString.equals("First Class")){
            classString = "first";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}