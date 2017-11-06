package adapters;

/**
 * Created by Alvaro on 11/6/17.
 */


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import mc.sms.R;
import model.Emision;

public class PreguntasRecyclerViewAdapter extends RecyclerView.Adapter<PreguntasRecyclerViewAdapter.MyViewHolder> {

    private List<Emision> emisionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textoPregunta;

    public MyViewHolder(View view) {
        super(view);
        textoPregunta = (TextView) view.findViewById(R.id.charge);

    }
}


    public PreguntasRecyclerViewAdapter(List<Emision> listaPreguntas) {
        this.emisionList = listaPreguntas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_pregunta, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Emision emision = emisionList.get(position);
        holder.textoPregunta.setText(emision.getMensajeTexto());
    }

    @Override
    public int getItemCount() {
        return emisionList.size();
    }
}

