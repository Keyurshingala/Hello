public class MainActivity extends AppCompatActivity {
    ArrayAdapter listadapter;
    ArrayList<String> citylist;
    ActivityMainBinding binding;
    ArrayList<User> userlist;

    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createUserList();
        adapter = new CustomListAdapter(userlist);
        binding.recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView1.setAdapter(adapter);

        adapter.setOnClick(new OnClick() {
            @Override
            public void onButtonClick(View v, User user) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                getMenuInflater().inflate(R.menu.recycler_option_menu_layout, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.edit:
                                Toast.makeText(MainActivity.this, "edit", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.view:
                                Toast.makeText(MainActivity.this, "view", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
                                break;

                        }

                        return true;
                    }
                });
            }
        });

        createData();

        registerForContextMenu(binding.listCity);

        listadapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, citylist);
        binding.listCity.setAdapter(listadapter);


        binding.popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.option_menu_layout, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.first:
                                Toast.makeText(MainActivity.this, "First", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.second:
                                Toast.makeText(MainActivity.this, "Second", Toast.LENGTH_SHORT).show();
                                break;

                        }

                        return true;
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.first:
                Toast.makeText(this, "First", Toast.LENGTH_SHORT).show();
                break;
            case R.id.second:
                Toast.makeText(this, "Second", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    //*****************************************TODO Context Menu*******************************************************//
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_layout, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Dialog dialog = new Dialog(this);
        CustomDilogBinding dialogBinding = CustomDilogBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        dialogBinding.etCityDilog.setText(citylist.get(info.position));
        switch (item.getItemId()) {
            case R.id.edit:
                dialogBinding.btnDelete.setVisibility(View.GONE);
                dialogBinding.btnEdit.setOnClickListener(v -> {
                    String city = dialogBinding.etCityDilog.getText().toString().trim();
                    citylist.set(info.position, city);
                    listadapter.notifyDataSetChanged();
                    dialog.dismiss();
                });
                break;
            case R.id.delete:
                dialogBinding.btnEdit.setVisibility(View.GONE);
                dialogBinding.btnDelete.setOnClickListener(v -> {
                    citylist.remove(info.position);
                    listadapter.notifyDataSetChanged();
                    dialog.dismiss();
                });
                break;
            case R.id.view:
                dialogBinding.btnDelete.setVisibility(View.GONE);
                dialogBinding.btnEdit.setVisibility(View.GONE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.MyViewHolder> {

        ArrayList<User> userlist;
        OnClick onClick;

        public CustomListAdapter(ArrayList<User> userlist) {
            this.userlist = userlist;
        }

        public void setOnClick(OnClick onClick) {
            this.onClick = onClick;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CustomListItemBinding binding = CustomListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            User user = userlist.get(position);

            holder.binding.imgView.setImageResource(user.getImg());
            holder.binding.tvName.setText(user.getName());
            holder.binding.tvEmail.setText(user.getEmail());

            /*todo holder.binding.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                    intent.putExtra("name",user);
                    startActivity(intent);
                }
               });*/

            holder.binding.parent.setOnClickListener(v -> {
                onClick.onButtonClick(v, user);
            });

        }

        @Override
        public int getItemCount() {
            return userlist.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            CustomListItemBinding binding;

            public MyViewHolder(@NonNull CustomListItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

    private void createUserList() {
        userlist = new ArrayList<>();
        userlist.add(new User(R.drawable.anchor_24, "kejgvkvhyur", "shingala.keyur@gmail.com"));
        userlist.add(new User(R.drawable.anchor_24, "jjhhgkeyur", "shingala.keyur@gmail.com"));
    }

    private void createData() {
        citylist = new ArrayList<>();
        citylist.add("Ahmedabad");
        citylist.add("Amreli district");
    }
}
