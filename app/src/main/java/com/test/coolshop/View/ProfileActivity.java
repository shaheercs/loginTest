package com.test.coolshop.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.test.coolshop.Model.UserDetailsResponse;
import com.test.coolshop.R;
import com.test.coolshop.Setting.Utils;
import com.test.coolshop.databinding.ActivityProfileBindingImpl;
import com.test.coolshop.viewModel.ProfileViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    private ProfileViewModel profileViewModel;
    private ActivityProfileBindingImpl activityProfileBinding;
    private String userId = "";
    private long MB = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        activityProfileBinding.setProfile(profileViewModel);
        userId = Utils.readSharedSetting(this, "userID", "");
        /**
         * we need check empty check too in this if condition
         */
        if (userId != null) {
            profileViewModel.userInfo(userId).observe(this, new Observer<UserDetailsResponse>() {
                @Override
                public void onChanged(UserDetailsResponse userDetailsResponse) {
                    if (userDetailsResponse != null) {
                        profileImage(userDetailsResponse.getAvatar_url(), activityProfileBinding.img);
                        activityProfileBinding.useremail.setText(userDetailsResponse.getEmail());
                    }
                }
            });
        }
    }

    /**
     * setting the profile picture in
     */
    private void profileImage(String image_url, final ImageView img) {
        if (image_url != null) {
            Glide.with(this)
                    .asBitmap()
                    .placeholder(R.drawable.ic_dummy_user)
                    .load(image_url)
                    .transform(new CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            img.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            img.setImageDrawable(placeholder);
                        }
                    });
        }
    }

    public void getImage(View view) {
        startPicker();
    }

    private void startPicker() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Bitmap bitmap = null;
                if (result != null) {
                    bitmap = getScaledBitmap(result.getUri());
                    if (bitmap != null && bitmap.getByteCount() < MB) {
                        activityProfileBinding.img.setImageBitmap(bitmap);
                        updateImage(bitmap);
                    } else {
                        Utils.showSnackbar(activityProfileBinding.getRoot(), getString(R.string.image_limit));
                    }
                }
            }
        }
    }

    /**
     *update image to middleware
     */

    private void updateImage(Bitmap bitmap) {
        profileViewModel.uploadImage(userId, convertBase64(bitmap)).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String data) {
                if (data != null && !data.equals("")) {
                    Utils.showSnackbar(activityProfileBinding.getRoot(), getString(R.string.successfully_updated));
                } else {
                    //TODO  need to remove the unComment this line and toast message . since we not API integrated
                    //activityProfileBinding.img.setImageResource(R.drawable.ic_dummy_user);
                    Toast.makeText(ProfileActivity.this, "since we not API integrated,just setting image ", Toast.LENGTH_SHORT).show();
                    Utils.showSnackbar(activityProfileBinding.getRoot(), getString(R.string.failed_updated));
                }
            }
        });
    }

    private Bitmap getScaledBitmap(Uri uri) {
        try {
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = 5;
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap scaledBitmap = BitmapFactory.decodeStream(inputStream, null, bitmapOptions);
            return scaledBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    @Override
    public void onBackPressed() {
        ProfileActivity.this.moveTaskToBack(true);
    }
}
