package jp.gr.java_conf.tmatz.text_processer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;

public class MainActivity extends FragmentActivity
implements StageFragment.StageFragmentListener
{

	@Override
	public void updateStage(StageFragment sf)
	{
		// TODO: Implement this method
	}
	
	public static final String ACTION_INTERCEPT = "com.adamrocker.android.simeji.ACTION_INTERCEPT";
	public static final String EXTRA_REPLACE_KEY = "replace_key";
	private static final String TAG = "MainActivity";

	private final StringBuilder mLogBuilder = new StringBuilder();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		mLogBuilder.setLength(0);
		Log.i(TAG, mLogBuilder
			  .append("onCreate")
			  .append((savedInstanceState != null) ? "with state" : "")
			  .toString());

		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String origString = null;

		setContentView(R.layout.main_activity);
		final ViewPager pager = (ViewPager) findViewById(R.id.pager);
		PagerAdapter pa = new FragmentStatePagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return 2;
			}

			@Override
			public Fragment getItem(int position)
			{
				switch (position)
				{
					case 0:
						return new InputFragment();
						
					default:
						return new SortFragment();
				}
			}
			
			@Override
			public CharSequence getPageTitle(int position)
			{
				switch (position)
				{
					case 0:
						return getResources().getString(R.string.input);
					default:
						return "Sort";
				}
			}
		};
		pager.setAdapter(pa);
	
		
		/*
		 
		final TextView textView = (TextView) findViewById(R.id.text);
		final Button copyButton = (Button) findViewById(R.id.copy_btn);
		final Button shareButton = (Button) findViewById(R.id.share_btn);
		final Button okButton = (Button) findViewById(R.id.ok_btn);
		final Button cancelButton = (Button) findViewById(R.id.cancel_btn);

		String action = intent.getAction();
		if (Intent.ACTION_SEND.equals(action))
		{
			Bundle extras = intent.getExtras();
			if (extras != null)
			{
				CharSequence ext = extras.getCharSequence(Intent.EXTRA_TEXT);
				if (ext != null)
				{
					origString = ext.toString();
				}
			}
		}
		else if (ACTION_INTERCEPT.equals(action))
		{
			origString = intent.getStringExtra(EXTRA_REPLACE_KEY);
		}
		else
		{
			origString = StringUtils.readTextFromClipboard(this);
		}

		if (origString != null && origString.length() > 0)
		{
			List<String> lines = StringUtils.SplitLines(origString);

			Collator c = Collator.getInstance();
			Collections.sort(lines, c);

			textView.setText(StringUtils.JoinLines(lines));
		}

		copyButton.setOnClickListener(new OnClickListener()
            {
                public void onClick(View v)
				{
                    copy(textView.getText().toString());
                }
            });

		shareButton.setOnClickListener(new OnClickListener()
            {
                public void onClick(View v)
				{
                    share(textView.getText().toString());
                }
            });

		okButton.setOnClickListener(new OnClickListener()
            {
				public void onClick(View v)
                {
					replace(textView.getText().toString());
                }
            });


		cancelButton.setOnClickListener(new OnClickListener()
            {
				public void onClick(View v)
                {
					finish();
                }
            });
			*/
	}

	private void copy(String result)
	{
		Log.i(TAG, "copy");

		StringUtils.copyTextToClipboard(this, result);
		Toast.makeText(this, R.string.copy_success, Toast.LENGTH_SHORT).show();
	}

	private void share(String result)
	{
		Log.i(TAG, "share");

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, result);
		startActivity(intent);
	}

	// send back result string to calling package.
	private void replace(String result)
	{
		Log.i(TAG, "replace");

		String action = getIntent().getAction();
		if (Intent.ACTION_SEND.equals(action))
		{
			// do nothing
		}
		else if (ACTION_INTERCEPT.equals(action))
		{
			Intent data = new Intent();
			data.putExtra(EXTRA_REPLACE_KEY, result);
			setResult(RESULT_OK, data);
		}

		finish();
	}
}
