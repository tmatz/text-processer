package jp.gr.java_conf.tmatz.text_processer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.Activity;

public abstract class StageFragment extends Fragment
{
	public interface StageFragmentListener
	{
		void updateStage(StageFragment sf);
	}
	
	StageController mStageController;
	StageFragmentListener mStageFragmentListener;
	
	public abstract String getStageName();
	
	public abstract void invalidateStage();
	
	protected StageFragmentListener getStageFragmentListener()
	{
		return mStageFragmentListener;
	}

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		mStageFragmentListener = (StageFragmentListener) activity;
	}
}
