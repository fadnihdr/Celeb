package com.example.fadni.celepguessapp;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private GridView gridView;
    private SharedPreferences preferences;
    private ArrayAdapter<String> names;
    private Toast currentToast;
    private ImageView img;
    private int numberOfChoice;
    private ArrayList<String> celebList;
    private ArrayList<Integer> photos;
    private ArrayList<String> nameForChoose;
    private Map<String, Integer> nameMapping;
    private int currentPicNumber;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        preferences = getActivity().getSharedPreferences("choices", MODE_PRIVATE);
        names = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        img = (ImageView) v.findViewById(R.id.celebImage);
        gridView = (GridView) v.findViewById(R.id.gridView);
        gridView.setAdapter(names);
        celebList = new ArrayList<>();
        photos = new ArrayList<>();
        nameForChoose = new ArrayList<>();

        nameMapping = new HashMap<String, Integer>();
        nameMapping.put("Kiera Knightley", R.drawable.knightley);
        nameMapping.put("Emma Stone", R.drawable.stone);
        nameMapping.put("Natalie Portman", R.drawable.portman);
        nameMapping.put("John Oliver", R.drawable.oliver);
        nameMapping.put("Benedict Cumberbatch", R.drawable.cumberbatch);
        nameMapping.put("Hugh Laurie", R.drawable.laurie);
        nameMapping.put("Emma Watson", R.drawable.watson);
        nameMapping.put("Idris Elba", R.drawable.elba);

        for (Map.Entry<String, Integer> entry : nameMapping.entrySet()) {
            String name = entry.getKey();
            celebList.add(name);
            int value = entry.getValue();
            photos.add(value);
        }

        celebList.add("Jimmy Kimmel");
        celebList.add("Steven Colbert");
        celebList.add("Chris Evans");
        celebList.add("Brie Larson");
        celebList.add("Milla Jovovich");
        celebList.add("Spongebob");
        celebList.add("Kevin Durant");
        celebList.add("Amanda Seyfried");
        celebList.add("Scarlett Johanson");
        celebList.add("Jennifer Lawrence");


        photoPicker();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        gameSetter();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (nameMapping.containsKey(parent.getItemAtPosition(position).toString())) {
                    if (img.getDrawable().getConstantState() == getResources().getDrawable(nameMapping.get(parent.getItemAtPosition(position).toString())).getConstantState()) {
                        if (currentToast != null) {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT);
                        currentToast.show();
                    } else {
                        if (currentToast != null) {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT);
                        currentToast.show();
                    }
                    photoPicker();
                    gameSetter();
                } else {
                    if (currentToast != null) {
                        currentToast.cancel();
                    }
                    currentToast = Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT);
                    currentToast.show();
                    photoPicker();
                    gameSetter();
                }


            }
        });
    }

    public void gameSetter() {
        numberOfChoice = preferences.getInt("number", 4);
        names.clear();
        nameForChoose.add(celebList.get(currentPicNumber));
        while (nameForChoose.size() < numberOfChoice) {
            int randomNameGenerator = (int) (Math.random() * celebList.size());
            String nameToPut = celebList.get(randomNameGenerator);
            if (nameForChoose.contains(nameToPut)) {
                System.out.println("Name is already inside");
            } else {
                nameForChoose.add(nameToPut);
            }
        }

        for (int i = 0; i < numberOfChoice; i++) {
            int randomNamePutter = (int) (Math.random() * nameForChoose.size());
            names.add(nameForChoose.get(randomNamePutter));
            nameForChoose.remove(randomNamePutter);
        }
    }


    public void photoPicker() {
        int randomiser = (int) (Math.random() * photos.size());
        img.setImageResource(photos.get(randomiser));
        currentPicNumber = randomiser;
    }
}