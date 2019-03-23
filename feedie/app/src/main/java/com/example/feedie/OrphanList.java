package com.example.feedie;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.feedie.Model.OrphanageList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class OrphanList extends AppCompatActivity {

    private DatabaseReference listRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphan_list);

        listRef = FirebaseDatabase.getInstance().getReference().child("orphanages/");

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<OrphanageList> options =
                new FirebaseRecyclerOptions.Builder<OrphanageList>().setQuery(listRef, OrphanageList.class).build();

        FirebaseRecyclerAdapter<OrphanageList, OrphanageViewHolder> adapter =
                new FirebaseRecyclerAdapter<OrphanageList, OrphanageViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull OrphanageViewHolder holder, int position, @NonNull final OrphanageList model)
                    {
                        Log.d("OrphanList",model.toString());

                        showImage(holder.imageView, model.getImage());
                        holder.orphanageName.setText(model.getTitle());
                        holder.orphanageDescription.setText(model.getDescription());
                        holder.orphanageEmail.setText(model.getEmail());
                        holder.orphanageContact.setText(model.getMobile());
                        holder.orphanageAddress.setText(model.getAddress());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = getIntent();

                                try {
                                    String msg = i.getExtras().getString("message");

                                    String toNumber = "91"+model.getMobile();

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+msg));
                                    startActivityForResult(intent, 8);
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        });
                    }



                    @NonNull
                    @Override
                    public OrphanageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orphanage_list, parent, false);
                        return new OrphanageViewHolder(view);
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public void showImage(ImageView v, String uri) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(uri);
            v.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        } catch (IOException e) {
            Log.e("OrphanList", e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 8) {
            Toast.makeText(OrphanList.this, "Message Sent Successfully!!! ", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(OrphanList.this, EndActivity.class);
            startActivity(intent1);
        } else {
            Toast.makeText(OrphanList.this, "Message Failed!!! ", Toast.LENGTH_LONG).show();
        }
    }
}
