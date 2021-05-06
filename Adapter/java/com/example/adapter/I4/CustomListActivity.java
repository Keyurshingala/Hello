package com.example.adapter.I4;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.adapter.R;
import com.example.adapter.databinding.ActivityCustomListBinding;
import com.example.adapter.databinding.CustomListItemBinding;
import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity implements CustomReAdapter.onClickListener {

    CustomListActivity context;
    ActivityCustomListBinding binding;
    ArrayList<CustomListUser> userList;

    CustomListAdapter adapter;
    CustomReAdapter reAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        createDataSet();

        reAdapter = new CustomReAdapter(userList, context, this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(context));
        binding.recycler.setAdapter(reAdapter);


        adapter = new CustomListAdapter(userList);
        binding.list.setAdapter(adapter);

        binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* CustomListUser user = userList.get(position);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);*/
                //Toast.makeText(CustomListActivity.this, ""+user.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onCardClick(int pos) {
        Toast.makeText(CustomListActivity.this, ""+userList.get(pos).getName(), Toast.LENGTH_SHORT).show();
    }

    private void createDataSet() {
        userList = new ArrayList<>();
        userList.add(new CustomListUser(1, "Lopa", "Java", "1 Jan", "lopa@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(2, "Vijay", "Java", "1 Jan", "vijay@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(3, "Keyur", "Java", "1 Jan", "keyur@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(4, "Dipesh", "Java", "1 Jan", "dipesh@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(5, "Amit", "Java", "1 Jan", "amit@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(1, "Lopa", "Java", "1 Jan", "lopa@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(2, "Vijay", "Java", "1 Jan", "vijay@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(3, "Keyur", "Java", "1 Jan", "keyur@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(4, "Dipesh", "Java", "1 Jan", "dipesh@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(5, "Amit", "Java", "1 Jan", "amit@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(1, "Lopa", "Java", "1 Jan", "lopa@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(2, "Vijay", "Java", "1 Jan", "vijay@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(3, "Keyur", "Java", "1 Jan", "keyur@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(4, "Dipesh", "Java", "1 Jan", "dipesh@gmail.com", R.drawable.ic_launcher_background));
        userList.add(new CustomListUser(5, "Amit", "Java", "1 Jan", "amit@gmail.com", R.drawable.ic_launcher_background));
    }

    class CustomListAdapter extends BaseAdapter {

        private ArrayList<CustomListUser> userList;

        public CustomListAdapter(ArrayList<CustomListUser> userList) {
            this.userList = userList;
        }

        @Override
        public int getCount() {
            return userList.size();
        }

        @Override
        public Object getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CustomListItemBinding binding = CustomListItemBinding.inflate(getLayoutInflater());

            CustomListUser user = userList.get(position);
            binding.tvName.setText(user.getName());
            binding.tvPosition.setText(user.getPosition());
            binding.tvDate.setText(user.getDate());
            binding.tvEmail.setText(user.getEmail());
            binding.ivProfile.setImageResource(user.getImage());

            return binding.getRoot();
        }
    }

}