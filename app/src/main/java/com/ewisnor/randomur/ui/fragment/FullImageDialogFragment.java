package com.ewisnor.randomur.ui.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ewisnor.randomur.R;
import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.task.LoadFullImageTask;

/**
 * Created by evan on 2015-01-04.
 */
public class FullImageDialogFragment extends DialogFragment {

    private Integer imageId;

    public FullImageDialogFragment(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.dialogfragment_full_image, null);
        dialog.setContentView(view);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        RandomurApp appContext = (RandomurApp) getActivity().getApplication();
        Bitmap imageBitmap = appContext.getImageCache().getFullImage(imageId);
        if (imageBitmap != null) {
            imageView.setImageBitmap(imageBitmap);
        }
        else {
            new LoadFullImageTask(appContext, imageView).execute(imageId);
        }

        return dialog;
    }
}
