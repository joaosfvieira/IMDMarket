package br.ufrn.imd.imdmarket.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.ufrn.imd.imdmarket.R;

public class AlertDialogUtils {

    public static void showDetailsAlert(Context context, String title, String details) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.alert_details, null);

        builder.setView(view);
        builder.setTitle(title);
        TextView detailsTextView = view.findViewById(R.id.alert_tv_01);
        detailsTextView.setText(details);
        builder.setPositiveButton("OK", null);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}
