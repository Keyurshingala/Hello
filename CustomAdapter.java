public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<Result> userList;
    onClickListener onClickListener;

    public MyAdapter(List<Result> userList, Context context, onClickListener onClickListener) {
        this.context = context;
        this.userList = userList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemBinding binding = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Result user = userList.get(position);

        Glide.with(holder.binding.iv).load(user.getUrls().getSmall()).centerCrop().transition(DrawableTransitionOptions.withCrossFade()).into(holder.binding.iv);
        holder.binding.tvDescription.setText(user.getDescription());
        holder.binding.tvArtist.setText(user.getUser().getName());
        holder.binding.tvPrice.setText("$" + user.getWidth() + ".00");

        holder.binding.card.setOnClickListener(v -> {
            onClickListener.onCardClick(user);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RvItemBinding binding;

        public MyViewHolder(@NonNull RvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClickListener {
        void onCardClick(Result result);
    }
}
