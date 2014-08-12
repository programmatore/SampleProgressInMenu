package com.ryochin.sampleprogressinmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private final PlaceholderFragment self = this;
        private boolean loading;

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.loading = false;
            this.setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Button start = (Button)this.getView().findViewById(R.id.startBtn);
            Button stop = (Button)this.getView().findViewById(R.id.stopBtn);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    self.loading = true;
                    self.getActivity().supportInvalidateOptionsMenu();
                    self.changeBtnColor();
                }
            });
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    self.loading = false;
                    self.getActivity().supportInvalidateOptionsMenu();
                    self.changeBtnColor();
                }
            });
            this.changeBtnColor();
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.main, menu);
        }

        @Override
        public void onPrepareOptionsMenu(Menu menu) {
            super.onPrepareOptionsMenu(menu);
            MenuItem progressMenuItem = menu.findItem(R.id.progress);
            if (this.loading) {
                // MenuItemを可視化
                progressMenuItem.setVisible(true);
                // ProgressLayoutを設定する
                MenuItemCompat.setActionView(progressMenuItem, R.layout.menu_progress_layout);
            } else {
                // MenuItemを不可視化
                progressMenuItem.setVisible(false);
                // ProgressLayoutを削除
                MenuItemCompat.setActionView(progressMenuItem, null);
            }
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private void changeBtnColor() {
            Button start = (Button)this.getView().findViewById(R.id.startBtn);
            Button stop = (Button)this.getView().findViewById(R.id.stopBtn);
            if (this.loading) {
                start.setBackgroundColor(this.getResources().getColor(R.color.color_blue));
                stop.setBackgroundColor(this.getResources().getColor(R.color.color_light_gray));
            } else {
                start.setBackgroundColor(this.getResources().getColor(R.color.color_light_gray));
                stop.setBackgroundColor(this.getResources().getColor(R.color.color_red));
            }
        }
    }
}
