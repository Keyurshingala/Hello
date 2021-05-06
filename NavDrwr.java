public class MainActivity extends AppCompatActivity {

    MainActivity context;
    ActivityMainBinding binding;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        setSupportActionBar(binding.include.toolBar);

        addFragment(new ArtworkFragment());

        drawerToggle = new ActionBarDrawerToggle(context, binding.drawerLayout, binding.include.toolBar, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.itemArtwork:
                    addFragment(new ArtworkFragment());
                    break;
                case R.id.drwrUser:

                    break;
                case R.id.drwrPost:

                    break;
                case R.id.drwrDataBase:

                    break;
            }
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayMainScreen, fragment);
        transaction.commit();
    }
}
