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
import com.facebook.ads.Ad;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.melnykov.fab.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class mp_details extends AppCompatActivity {
    Adapter adapter;
    RecyclerView mp;
    FloatingActionButton fab;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_details);
        mp=findViewById(R.id.mp);
        mp.setLayoutManager(new LinearLayoutManager(this));
        Query query= FirebaseDatabase.getInstance().getReference().child("MP_Details");
        FirebaseRecyclerOptions<MP_DETAIL> options =
                new FirebaseRecyclerOptions.Builder<MP_DETAIL>()
                        .setQuery(query,MP_DETAIL.class)
                        .build();
        adapter=new Adapter(options);
        mp.setAdapter(adapter);
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
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("MP_Details").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<MP_DETAIL> options =
                        new FirebaseRecyclerOptions.Builder<MP_DETAIL>()
                                .setQuery(firebaseSearchQuery,MP_DETAIL.class)
                                .build();
                adapter=new Adapter(options);
                mp.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String search_text=search.getText().toString().toLowerCase();
                Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("MP_Details").orderByChild("tag")
                        .startAt(search_text.toLowerCase())
                        .endAt(search_text.toLowerCase() + "\uf8ff");
                FirebaseRecyclerOptions<MP_DETAIL> options =
                        new FirebaseRecyclerOptions.Builder<MP_DETAIL>()
                                .setQuery(firebaseSearchQuery,MP_DETAIL.class)
                                .build();
                adapter=new Adapter(options);
                mp.setAdapter(adapter);
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
    public class Adapter extends FirebaseRecyclerAdapter<MP_DETAIL, Adapter.viewholder>{
        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public Adapter(@NonNull FirebaseRecyclerOptions<MP_DETAIL> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull viewholder viewholder, int i, @NonNull final MP_DETAIL mp_detail) {
            Glide.with(getApplicationContext()).load(mp_detail.getDP()).fitCenter().into(viewholder.pic);
            viewholder.Name.setText(mp_detail.getName());
            viewholder.Village.setText("Village :"+mp_detail.getVillage());
            viewholder.Contact.setText("Contact No: "+mp_detail.getContact());
            viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(mp_details.this,R.style.BottomSheetDialogTheme);
                    View bottomsheet= LayoutInflater.from(getApplicationContext()).inflate(R.layout.mp_card_main, (FrameLayout)findViewById(R.id.container));
                    TextView Name=bottomsheet.findViewById(R.id.sname);
                    TextView Village=bottomsheet.findViewById(R.id.skill);
                    TextView Number=bottomsheet.findViewById(R.id.phone);
                    TextView Description=bottomsheet.findViewById(R.id.des);
                    CircleImageView image=bottomsheet.findViewById(R.id.Con_Pro);
                    Name.setText(mp_detail.getName());
                    Village.setText(mp_detail.getVillage());
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
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mp_card, parent, false);

            return new viewholder(view);
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
