package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

import mc.sms.R;
import model.Actividad;
import model.Emision;
import model.Org;

/**
 * Created by Alvaro on 2/21/15.
 */
public class PreguntasListViewAdapter extends BaseAdapter  {

    Context context;
    LayoutInflater inflater;
    private List<Emision> emisions= null;
    private ArrayList<Emision> arraylist;
    public Actividad meetingApp;

    public PreguntasListViewAdapter(Context context,
                                    List<Emision> emisiones) {




        this.context = context;
        this.emisions = emisiones;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(emisions);

    }


    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView name_sponsor;


    }
    @Override
    public int getCount() {
        return emisions.size();
    }

    @Override
    public Object getItem(int position) {
        return emisions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();


            view = inflater.inflate(R.layout.cell_sponsor, null);

            // Locate the TextViews in listview_item.xml
            holder.name_sponsor = (TextView) view.findViewById(R.id.charge);


            // Locate the ImageView in listview_item.xml



            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
            holder.name_sponsor.setText("");


        }


        String firstName = emisions.get(position).getMensajeTexto();

        holder.name_sponsor.setText(firstName);




        return view;
    }
}
