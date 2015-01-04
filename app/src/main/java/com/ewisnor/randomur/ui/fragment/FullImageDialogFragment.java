package com.ewisnor.randomur.ui.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ewisnor.randomur.R;
import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.iface.OnImageDownloadedListener;
import com.ewisnor.randomur.task.CacheFullImageTask;

/**
 * Created by evan on 2015-01-04.
 */
public class FullImageDialogFragment extends DialogFragment implements OnImageDownloadedListener {
    public static final String IMAGE_ID_ARGUMENT = "imageIdArgument";

    private View view;

    public FullImageDialogFragment() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Integer imageId = getArguments().getInt(IMAGE_ID_ARGUMENT);

        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.dialogfragment_full_image, null);
        dialog.setContentView(view);

//        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        RandomurApp appContext = (RandomurApp) getActivity().getApplication();
        Bitmap imageBitmap = appContext.getImageCache().getFullImage(imageId);
        if (imageBitmap != null) {
            showImage(imageBitmap);
        }
        else {
            new CacheFullImageTask(appContext, this).execute(imageId);
        }

        return dialog;
    }

    public void showImage(Bitmap image) {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        progressBar.setVisibility(View.INVISIBLE);
        imageView.setImageBitmap(image);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnImageDownloaded(Bitmap image) {
        if (image == null) {
            RandomurLogger.error("Failed to show full size image");
        }
        else {
            showImage(image);
        }
    }
}
