package com.mireia.mediaproyectt;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.DialogFragment;
import java.io.IOException;

public class MediaDialogFragment extends DialogFragment {

    private static final String ARG_MEDIA_ITEM = "media_item";
    private MediaItem mediaItem;

    private VideoView videoView;
    private WebView webView;
    private MediaPlayer mediaPlayer;

    public static MediaDialogFragment newInstance(MediaItem mediaItem) {
        MediaDialogFragment fragment = new MediaDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MEDIA_ITEM, mediaItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_media, container, false);

        mediaItem = getArguments().getParcelable(ARG_MEDIA_ITEM);

        videoView = view.findViewById(R.id.videoView);
        webView = view.findViewById(R.id.webView);
        Button closeButton = view.findViewById(R.id.closeButton);

        videoView.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);

        if (mediaItem.getType() == 1) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(mediaItem.getResource());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        switch (mediaItem.getType()) {
            case 0: // Video
                videoView.setVisibility(View.VISIBLE);
                videoView.setVideoPath(mediaItem.getResource());
                MediaController mediaController = new MediaController(getContext());
                videoView.setMediaController(mediaController);
                videoView.start();
                break;

            case 1:
                break;

            case 2:
                webView.setVisibility(View.VISIBLE);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(mediaItem.getResource());
                break;
        }

        closeButton.setOnClickListener(v -> dismiss());

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}