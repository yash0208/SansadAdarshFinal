package com.rajaryan.sansadadarsh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.melnykov.fab.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class NodelActivity extends AppCompatActivity {
Adapter adapter;
    RecyclerView nodel_officers;
    FloatingActionButton fab;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodel);
        nodel_officers=findViewById(R.id.mp);
        nodel_officers.setLayoutManager(new LinearLayoutManager(this));
        Query query= FirebaseDatabase.getInstance().getReference().child("Nodel Officer");
        FirebaseRecyclerOptions<Officers_Details> options =
                new FirebaseRecyclerOptions.Builder<Officers_Details>()
                        .setQuery(query,Officers_Details.class)
                        .build();
        adapter=new Adapter(options);
        nodel_officers.setAdapter(adapter);
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
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Nodel Officer").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<Officers_Details> options =
                        new FirebaseRecyclerOptions.Builder<Officers_Details>()
                                .setQuery(firebaseSearchQuery,Officers_Details.class)
                                .build();
                adapter=new Adapter(options);
                nodel_officers.setAdapter(adapter);
                nodel_officers.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String search_text=search.getText().toString().toLowerCase();
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Nodel Officer").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<Officers_Details> options =
                        new FirebaseRecyclerOptions.Builder<Officers_Details>()
                                .setQuery(firebaseSearchQuery,Officers_Details.class)
                                .build();
                adapter=new Adapter(options);
                nodel_officers.setAdapter(adapter);
                nodel_officers.setAdapter(adapter);
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
    public class Adapter extends FirebaseRecyclerAdapter<Officers_Details, Adapter.viewholder> {
        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public Adapter(@NonNull FirebaseRecyclerOptions<Officers_Details> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull Adapter.viewholder viewholder, int i, @NonNull final Officers_Details mp_detail) {
            Glide.with(getApplicationContext()).load(mp_detail.getDP()).fitCenter().into(viewholder.pic);
            viewholder.Name.setText(mp_detail.getName());
            viewholder.Village.setText("Work :"+mp_detail.getWork());
            viewholder.Contact.setText("Contact No: "+mp_detail.getContact());
            viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(NodelActivity.this,R.style.BottomSheetDialogTheme);
                    View bottomsheet= LayoutInflater.from(getApplicationContext()).inflate(R.layout.officer_card, (FrameLayout)findViewById(R.id.container));
                    TextView Name=bottomsheet.findViewById(R.id.sname);
                    TextView Village=bottomsheet.findViewById(R.id.skill);
                    TextView Number=bottomsheet.findViewById(R.id.phone);
                    TextView Description=bottomsheet.findViewById(R.id.des);
                    CircleImageView image=bottomsheet.findViewById(R.id.Con_Pro);
                    Name.setText(mp_detail.getName());
                    Village.setText(mp_detail.getWork());
                    Number.setText(mp_detail.getContact());
                    Description.setText(mp_detail.getDescription());
                    Glide.with(getApplicationContext()).load(mp_detail.getDP()).fitCenter().into(image);
                    bottomSheetDialog.setContentView(bottomsheet);
                    bottomSheetDialog.show();
                }
            });
        }

        @NonNull
        @Override
        public Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mp_card, parent, false);

            return new Adapter.viewholder(view);
        }
        private class viewholder extends RecyclerView.ViewHolder {
            CircleImageView pic;
            TextView Name,Village,Contact;
            public viewholder(@NonNull View itemView) {
                super(itemView);
                pic=itemView.findViewById(R.id.picas);
                Name=itemView.findViewById(R.id.nameas);
                Village=itemView.findViewById(R.id.skillas);
                Contact=itemView.findViewById(R.id.emailas);
            }
        }
    }
}
