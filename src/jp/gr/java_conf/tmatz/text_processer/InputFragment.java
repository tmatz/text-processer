package jp.gr.java_conf.tmatz.text_processer;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;

public class InputFragment extends StageFragment
{
	@Override
	public String getStageName()
	{
		return getResources().getString(R.string.input);
	}

	@Override
	public void invalidateStage()
	{
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.input_fragment, null);
	}
}
