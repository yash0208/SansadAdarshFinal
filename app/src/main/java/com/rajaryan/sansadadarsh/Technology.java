package com.rajaryan.sansadadarsh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.melnykov.fab.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class Technology extends AppCompatActivity {

    RecyclerView technology;
   Adapter adapter;
    FloatingActionButton fab;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        technology=findViewById(R.id.villages);
        technology.setLayoutManager(new LinearLayoutManager(this));
        Query query= FirebaseDatabase.getInstance().getReference().child("Technology");
        FirebaseRecyclerOptions<Technology_Details> options =
                new FirebaseRecyclerOptions.Builder<Technology_Details>()
                        .setQuery(query,Technology_Details.class)
                        .build();
        adapter=new Adapter(options);
        technology.setAdapter(adapter);
        fab=findViewById(R.id.fab);
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
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Technology").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<Technology_Details> options =
                        new FirebaseRecyclerOptions.Builder<Technology_Details>()
                                .setQuery(firebaseSearchQuery,Technology_Details.class)
                                .build();
                adapter=new Adapter(options);
                technology.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String search_text=search.getText().toString().toLowerCase();
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Technology").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<Technology_Details> options =
                        new FirebaseRecyclerOptions.Builder<Technology_Details>()
                                .setQuery(firebaseSearchQuery,Technology_Details.class)
                                .build();
                adapter=new Adapter(options);
                technology.setAdapter(adapter);
                adapter.startListening();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    public class Adapter extends FirebaseRecyclerAdapter<Technology_Details,viewholder> {
        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public Adapter(@NonNull FirebaseRecyclerOptions<Technology_Details> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final viewholder viewholder, int i, @NonNull final Technology_Details village_data) {
            viewholder.arrowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewholder.expandableView.getVisibility()==View.GONE){
                        TransitionManager.beginDelayedTransition(viewholder.cardView, new AutoTransition());
                        viewholder.expandableView.setVisibility(View.VISIBLE);
                        viewholder.arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    } else {
                        TransitionManager.beginDelayedTransition(viewholder.cardView, new AutoTransition());
                        viewholder.expandableView.setVisibility(View.GONE);
                        viewholder.arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    }

                }
            });
            viewholder.name.setText(village_data.getName());
            viewholder.des.setText(village_data.getField());
            viewholder.number.setText(village_data.getContact());
            viewholder.mp.setText(village_data.getInnovator());
            Glide.with(getApplicationContext()).load(village_data.getImage()).fitCenter().into(viewholder.image);
            viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Technology.this,PdfView.class);
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
                    .inflate(R.layout.technology_card, parent, false);

            return new viewholder(view);
        }
    }

    private class viewholder extends RecyclerView.ViewHolder {
        ConstraintLayout expandableView;
        Button arrowBtn;
        CardView cardView;
        private GestureDetector gestureDetector;
        View.OnTouchListener gestureListener;
        TextView name,mp,number,email,des;
        ImageButton flora, fauna;
        CircleImageView circleImageView;
        String id,form;
        ImageView image;
        Intent go;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            des=itemView.findViewById(R.id.desc);
            mp=itemView.findViewById(R.id.phoneNumber);
            number=itemView.findViewById(R.id.mailNumber);
            arrowBtn=itemView.findViewById(R.id.arrowBtn);
            image=itemView.findViewById(R.id.imageView);
            circleImageView=itemView.findViewById(R.id.circleImage);
            expandableView = itemView.findViewById(R.id.expandableView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
