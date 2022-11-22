package com.example.kiwi30;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchListFragment extends Fragment{
    String stringResults = null;
    ArrayList<Flight> flights =  new ArrayList<Flight>();
    RecyclerView recyclerView;

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        // Inflate the layout for this fragment
        //
//        recyclerView  = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
 //       recyclerView.setAdapter(new RandomNumListAdapter(1234));

//        flights = Flight.createFlightsList(20);
        // Create adapter passing in the sample user data
//        SearchListAdapter adapter = new SearchListAdapter(flights);
        // Attach the adapter to the recyclerview to populate items
//        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle bundle = this.getArguments();
        if(bundle != null){
            stringResults = bundle.getString("key");
//            Log.d("stringResults", stringResults);
        }

        try {
            JSONObject jsonReseults = new JSONObject(stringResults);
//            Log.d("jsonReseults", jsonReseults.getJSONObject("itineraries").toString());
            JSONObject jsonResultsTwo = jsonReseults.getJSONObject("itineraries");
//            Log.d("jsonReseultsTwo", jsonResultsTwo.toString());
//            Log.d("jsonReseultsTwo", String.valueOf(jsonResultsTwo.getJSONArray("buckets")));
            JSONArray jsonArrayOne = jsonResultsTwo.getJSONArray("buckets");
//            Log.d("jsonArrayOne : ", String.valueOf(jsonArrayOne));


            String stringId = null;
            String originCity = null;
            String destinationCity = null;
            String stringOne = null;
            String airlineName = null;
            String logoURL = null;
            String[] stringTwoArray = new String[2];
            String[] stringThreeArray = new String[2];
            String priceOfTicket = null;
            String durationInMinutes = null;
            for (int i = 0; i < jsonArrayOne.length(); i++) {
                JSONObject jsonObjectOne = jsonArrayOne.getJSONObject(i);
                JSONArray jsonArrayTwo = jsonObjectOne.getJSONArray("items");

                for (int j = 0; j < jsonArrayTwo.length(); j++) {
                    JSONObject jsonObjectTwo = jsonArrayTwo.getJSONObject(j);
                    Log.d("jsonObjectTwo", String.valueOf(jsonObjectTwo));//12 instances //where to put flight class?? - The loop
                    JSONObject jsonObjectThree = jsonObjectTwo.getJSONObject("price");

                    priceOfTicket = jsonObjectThree.getString("formatted");

                    JSONArray jsonArrayFour = jsonObjectTwo.getJSONArray("legs");


                    originCity = jsonArrayFour.getJSONObject(0).getJSONObject("origin").getString("city");//12 instances

                    JSONObject jsondestinationCity =jsonArrayFour.getJSONObject(0);
                    destinationCity = jsondestinationCity.getJSONObject("destination").getString("city");

                    JSONObject jsondurationInMinutes = jsonArrayFour.getJSONObject(0);
                    durationInMinutes = jsondurationInMinutes.getString("durationInMinutes");


                    JSONObject jsonstringId = jsonArrayFour.getJSONObject(0);
                    stringId = jsonstringId.getString("id");

                    JSONObject jsonstringTwo = jsonArrayFour.getJSONObject(0);
                    String stringTwo = jsonstringTwo.getString("departure");

                    stringTwoArray = stringTwo.split("T");


                    JSONObject jsonstringThree = jsonArrayFour.getJSONObject(0);
                    String stringThree =  jsonstringThree.getString("arrival");
                    stringThreeArray = stringThree.split("T");

                    JSONObject jsonObjectSeven = jsonArrayFour.getJSONObject(0);

                    JSONObject jsoncarrier = jsonObjectSeven.getJSONObject("carriers");
                    JSONArray jsonArrayMarketing = jsoncarrier.getJSONArray("marketing");
                    JSONObject jsonmarketing0 = jsonArrayMarketing.getJSONObject(0);
                    logoURL = jsonmarketing0.getString("logoUrl");

                    airlineName = jsonmarketing0.getString("name");

                    Log.d("stringId", stringId);
                    Flight flightOne = new Flight(stringId, originCity, destinationCity, durationInMinutes, airlineName, logoURL, stringTwoArray[0], stringTwoArray[1], stringThreeArray[0], stringThreeArray[1], priceOfTicket);
                    Log.d("flightOne", String.valueOf(flightOne));
                    Log.d("String.valueOf(flightOne.getfId())", String.valueOf(flightOne.getfId()));
                    flights.add(flightOne);

                }

            }
            recyclerView  = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(new RandomNumListAdapter(flights, 65675));
            Log.d("flights.size()", String.valueOf(flights.size()));
//            Log.d("flights", String.valueOf(flights.get(1)));
//            Log.d("flights", String.valueOf(flights.get(1).getfAirline()));

 ///           recyclerView  = (RecyclerView) view.findViewById(R.id.recyclerView);
 ///           recyclerView.setHasFixedSize(true);
///            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
///            recyclerView.setAdapter(new RandomNumListAdapter(flights));

//            SearchListAdapter adapter = new SearchListAdapter(flights);
            // Attach the adapter to the recyclerview to populate items
//            recyclerView.setAdapter(adapter);
            // Set layout manager to position the items
//            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            adapter.notifyItemInserted(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

}