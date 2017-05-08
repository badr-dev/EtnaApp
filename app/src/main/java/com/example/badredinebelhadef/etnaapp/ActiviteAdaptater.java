package com.example.badredinebelhadef.etnaapp;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by badredinebelhadef on 06/05/2017.
 */


public class ActiviteAdaptater extends ArrayAdapter<Activite> {

    public ActiviteAdaptater(Context context, List<Activite> activites) {
        super(context, 0, activites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_activities_home, parent, false);
        }

        ActiviteViewHolder viewHolder = (ActiviteViewHolder) convertView.getTag();
        if( viewHolder == null ){

            viewHolder = new ActiviteViewHolder();
            viewHolder.moduleName = (TextView) convertView.findViewById(R.id.module_name);
            /*viewHolder.hrView = (View) convertView.findViewById(R.id.hrView);*/
            viewHolder.projectName = (TextView) convertView.findViewById(R.id.project_name);
            viewHolder.projectDeadLine = (TextView) convertView.findViewById(R.id.project_dead_line);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List des <activites>
        Activite activite = getItem(position);
        viewHolder.moduleName.setText(activite.getModuleName());
        viewHolder.projectName.setText(activite.getProjectName());
        viewHolder.projectDeadLine.setText(activite.getProjectDeadLine());

        return convertView;
    }

    private class ActiviteViewHolder{

        public TextView moduleName;
        /*public View hrView;*/
        public TextView projectName;
        public TextView projectDeadLine;
    }

}





