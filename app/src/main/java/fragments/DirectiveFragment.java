package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import adapters.DirectiveListViewAdapter;

import bolts.Continuation;
import bolts.Task;
import mc.sms2017.R;
import model.PersonaRolOrg;

/**
 * Created by Alvaro on 2/19/15.
 */
public class DirectiveFragment extends Fragment {

    ListView listview;
    SwipeDetector swipeDetector;
    DirectiveListViewAdapter adapter;
    public static List<PersonaRolOrg> staff;


    public static DirectiveFragment newInstance() {

        // Instantiate a new fragment

        DirectiveFragment fragment = new DirectiveFragment();


        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);


        ParseQuery<PersonaRolOrg> queryPersonaRolOrg = ParseQuery.getQuery(PersonaRolOrg.class);
        queryPersonaRolOrg.include("persona.pais");
        queryPersonaRolOrg.include("org");
        queryPersonaRolOrg.whereEqualTo("tipo", "sociedad");
        queryPersonaRolOrg.fromLocalDatastore().findInBackground().continueWithTask(new Continuation<List<PersonaRolOrg>, Task<List<PersonaRolOrg>>>() {
            @Override
            public Task<List<PersonaRolOrg>> then(Task<List<PersonaRolOrg>> task) throws Exception {
                staff=task.getResult();
                return task;
            }
        });

       /* ParseQuery<PersonaRolOrg> queryPersonaRolOrg = ParseQuery.getQuery(PersonaRolOrg.class);
        queryPersonaRolOrg.include("persona.pais");
        queryPersonaRolOrg.include("org");
        queryPersonaRolOrg.whereEqualTo("tipo", "sociedad");
        queryPersonaRolOrg.fromLocalDatastore().findInBackground(new FindCallback<PersonaRolOrg>() {
            @Override
            public void done(List<PersonaRolOrg> objects, ParseException e) {
                staff = objects;
            }
        });*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.common_list_layout, container , false);
        listview = (ListView)RootView.findViewById(R.id.commonListView);

        listview.setOnTouchListener(swipeDetector);


        //startLoading();

        return RootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(staff!=null){
//            Collections.sort(actors,new Comparator<PersonaRolOrg>() {
//                @Override
//                public int compare(PersonaRolOrg lhs, PersonaRolOrg rhs) {
//                    return lhs.getPerson().getLastName().compareTo(rhs.getPerson().getLastName());
//                }
//            });
            adapter = new DirectiveListViewAdapter(getActivity(),staff, true);
        }
        else {

        }

        // Binds the Adapter to the ListView
        listview.setAdapter(adapter);
        Log.i("HOLA", "HOLA");
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                PersonaRolOrg actor = (PersonaRolOrg) (listview.getItemAtPosition(position));
                Fragment fragment = DirectiveDetailFragment.newInstance(actor, true);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,fragment);
                ft.addToBackStack(null);
                ft.commit();
                /*
                MeetingApp meetingApp = ParseObject.createWithoutData(MeetingApp.class, object.getObjectId());
                Fragment fragment = SplashEventFragment.newInstance(meetingApp);
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container,fragment);
                ft.addToBackStack(null);
                ft.commit();*/

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        View v = mTabHost.getTabWidget().getChildAt(0);
        v.setBackgroundResource(R.drawable.programa);
*/

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener


                    return true;
                }
                return false;
            }
        });

    }


}