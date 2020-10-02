package com.rajaryan.sansadadarsh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.melnykov.fab.FloatingActionButton;

public class Schemes extends AppCompatActivity {
    Adapter adapter;
    RecyclerView schemes;
    FloatingActionButton fab;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);
        schemes=findViewById(R.id.schemes);
        schemes.setLayoutManager(new LinearLayoutManager(this));
        Query query= FirebaseDatabase.getInstance().getReference().child("Schemes");
        FirebaseRecyclerOptions<Village_Data> options =
                new FirebaseRecyclerOptions.Builder<Village_Data>()
                        .setQuery(query,Village_Data.class)
                        .build();
        adapter=new Adapter(options);
        schemes.setAdapter(adapter);fab=findViewById(R.id.fab);
        search=findViewById(R.id.search_field);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(search.getVisibility()==View.VISIBLE){
                    search.setVisibility(View.GONE);
                }
                else {
                    search.setVisibility(View.VISIBLE);
                }
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search_text=search.getText().toString().toLowerCase();
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Schemes").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<Village_Data> options =
                        new FirebaseRecyclerOptions.Builder<Village_Data>()
                                .setQuery(firebaseSearchQuery,Village_Data.class)
                                .build();
                adapter=new Adapter(options);
                schemes.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String search_text=search.getText().toString().toLowerCase();
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Schemes").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<Village_Data> options =
                        new FirebaseRecyclerOptions.Builder<Village_Data>()
                                .setQuery(firebaseSearchQuery,Village_Data.class)
                                .build();
                adapter=new Adapter(options);
                schemes.setAdapter(adapter);
                adapter.startListening();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    public void back(View view) {
        onBackPressed();
    }

    public class Adapter extends FirebaseRecyclerAdapter<Village_Data,viewholder>{
        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public Adapter(@NonNull FirebaseRecyclerOptions<Village_Data> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull viewholder viewholder, int i, @NonNull final Village_Data village_data) {
            Glide.with(getApplicationContext()).load(village_data.getImage()).fitCenter().into(viewholder.imageView);
            viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Schemes.this,PdfView.class);
                    i.putExtra("Link",village_data.getPDF());
                    i.putExtra("Name",village_data.getName());

                    startActivity(i);
                }
            });
        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.scheme_pic, parent, false);

            return new viewholder(view);
        }
    }

    private class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.images);
        }
    }
}
