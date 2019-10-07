package fragments;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import mc.neuro2019.R;
import model.Media;

/**
 * Created by Alvaro on 2/23/15.
 */
public class SaludosSociedadDetailFragment extends Fragment {

    public static String urlDocument;

    public static SaludosSociedadDetailFragment newInstance(Media url) {

        // Instantiate a new fragment

        SaludosSociedadDetailFragment fragment = new SaludosSociedadDetailFragment();


        if(url!=null){
            urlDocument = url.getArchivo().getUrl();

        }


        fragment.setRetainInstance(true);
        return fragment;

    }
    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.documentdetail, container , false);

        WebView webview = (WebView) RootView.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf= ""+urlDocument+"";
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdf);
        Toolbar toolbar = RootView.findViewById(R.id.webviewtoolbar);
        toolbar.setVisibility(View.GONE);
        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().onBackPressed();

                    return true;
                }
                return false;
            }
        });



    }
}
