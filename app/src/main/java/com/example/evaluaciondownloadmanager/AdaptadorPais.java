package com.example.evaluaciondownloadmanager;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorPais extends ArrayAdapter<Pais> {
    public AdaptadorPais(Context context, ArrayList<Pais>  datos) {
        super(context, R.layout.ly_item, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_item, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.txtTitulo);
        lblTitulo.setText(getItem(position).getNombre());

        ImageView imgPdf = (ImageView )item.findViewById(R.id.imgpdf);
        Glide.with(this.getContext()).load(getItem(position).getImagen()).into(imgPdf);


        //ImageView imageView = (ImageView) item.findViewById(R.id.imgpdf);
        //imageView.setTag(getItem(position).getImagen());

        /*
        Glide.with(this.getContext())
                .load(getItem(position).getURL())
                .error(R.drawable.imgnotfound)
                .into(imageView);
        */

        /*
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = v.getTag().toString();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDescription("PDF Paper");
                request.setTitle("Pdf Artcilee");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filedownload.pdf");
                DownloadManager manager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                try {
                    manager.enqueue(request);
                    Toast.makeText(getContext(),
                            "Descarga completada..",
                            Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(getContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        */
        return (item);
    }
}
