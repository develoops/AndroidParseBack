package fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.List;

import adapters.GridDocumentsAdapter;
import mc.neuro2019.R;
import model.Media;

/**
 * Created by Alvaro on 3/8/15.
 */
public class SaludosSociedadFragment extends Fragment {

    public static List<Media> documents;
    GridDocumentsAdapter adapter;
    public static SaludosSociedadFragment newInstance(List<Media> files) {

        // Instantiate a new fragment
        documents = files;
        SaludosSociedadFragment fragment = new SaludosSociedadFragment();
        fragment.setRetainInstance(true);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.documentslistlayout, container , false);
        final ListView listView = (ListView) RootView.findViewById(R.id.documentListView);// crear el
        // gridview a partir del elemento del xml gridview

        adapter = new GridDocumentsAdapter(getActivity(),R.layout.cell_document,documents);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject object = (ParseObject)(listView.getItemAtPosition(position));
                Media mobiFile= ParseObject.createWithoutData(Media.class, object.getObjectId());
                Fragment fragment = SaludosSociedadDetailFragment.newInstance(mobiFile);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        Toolbar toolbar = RootView.findViewById(R.id.doctoolbar);
        toolbar.setVisibility(View.GONE);

        return  RootView;

    }


}
