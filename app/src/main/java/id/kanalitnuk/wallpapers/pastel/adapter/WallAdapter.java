package id.kanalitnuk.wallpapers.pastel.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import id.kanalitnuk.wallpapers.R;
import id.kanalitnuk.wallpapers.pastel.activities.ApplyWallpaper;
import id.kanalitnuk.wallpapers.pastel.items.WallpaperItem;
import id.kanalitnuk.wallpapers.pastel.others.KanalitnuK;
import id.kanalitnuk.wallpapers.pastel.others.Utils;
import id.kanalitnuk.wallpapers.pastel.tasks.ColorGridTask;

import java.util.List;

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.MyViewHolder> {

    private List<WallpaperItem> images;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;
        public TextView name,author;
        public RelativeLayout realBackground;
        public ProgressBar pb;
        public View mainView;

        public MyViewHolder(View view) {
            super(view);

            thumbnail = (ImageView) view.findViewById(R.id.wall_grid_art);
            author = (TextView) view.findViewById(R.id.wall_grid_desc);
            realBackground = (RelativeLayout) view.findViewById(R.id.wall_real_background);
            name = (TextView) view.findViewById(R.id.wall_grid_name);
            pb = (ProgressBar) view.findViewById(R.id.progressBar_wall_grid);

            mainView = view;
        }
    }


    public WallAdapter(Context context, List<WallpaperItem> images) {
        this.context = context;
        this.images = images;
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wall_thum, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        WallpaperItem image = images.get(position);
        holder.name.setText(images.get(position).getName());
        holder.author.setText(images.get(position).getAuthor());
        if(Utils.darkTheme) holder.realBackground.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        Glide.with(context)
                .load(image.getThumb())
                .asBitmap()
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.pb.setVisibility(View.GONE);
                        holder.realBackground.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                        new ColorGridTask(context, resource, holder).execute();
                        return false;
                    }
                })
                .thumbnail(0.2f)
                .into(holder.thumbnail);

        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {

                context.startActivity(
                        new Intent(context, ApplyWallpaper.class)
                                .putExtra(ApplyWallpaper.EXTRA_WALLPAPER, images.get(holder.getAdapterPosition())),
                        ActivityOptionsCompat.makeScaleUpAnimation(view, (int) view.getX(), (int) view.getY(), view.getWidth(), view.getHeight()).toBundle()


                );
                KanalitnuK.getInstance().InterstitialAd(context);
            }
        });


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}