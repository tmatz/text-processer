package jp.gr.java_conf.tmatz.text_processer;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;

public class SortFragment extends StageFragment
{
	@Override
	public String getStageName()
	{
		return getResources().getString(R.string.sort);
	}

	@Override
	public void invalidateStage()
	{
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.sort_fragment, null);
	}
}
