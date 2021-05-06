CustomAdapter adapter = new CustomAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new UserFragment(), "Users");
        adapter.addFragment(new PostFragment(), "Posts");
        binding.ViewPager.setAdapter(adapter);
        binding.tabLayOut.setupWithViewPager(binding.ViewPager);



public class CustomAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();

    public CustomAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
