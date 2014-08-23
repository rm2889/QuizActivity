package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	public static final String EXTRA_ANSWER_IS_TRUE =
			"com.example.geoquiz.answer_is_true";

	public static final String EXTRA_ANSWER_SHOWN =
			"com.bignerdranch.android.geoquiz.answer_shown";

	private static final String KEY_CHEAT = "cheater?";

	private boolean mAnswerIsTrue;
	private Button mShowAnswerButton;
	private TextView mAnswerTextView;
	private boolean mDidTheyCheat = false;

	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			mDidTheyCheat = savedInstanceState.getBoolean(KEY_CHEAT, false);
		}

		setContentView(R.layout.activity_cheat);

		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);
		mAnswerTextView = (TextView)findViewById(R.id.ansertTextView);
		
		setAnswerShownResult(mDidTheyCheat);

		mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDidTheyCheat = true;			
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);		
		savedInstanceState.putBoolean(KEY_CHEAT, mDidTheyCheat);
	}
}
