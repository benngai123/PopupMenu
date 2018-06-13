package ben.popupmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

	private MenuPopWindow mMenuPopWindow;
	private ArrayList<Integer> lvIcon = new ArrayList<>(Arrays.asList(R.drawable.ic_home_normal,R.drawable.ic_home_normal,R.drawable.ic_home_normal,R.drawable.ic_home_normal));
	private ArrayList<Integer> lvIconName =  new ArrayList<>(Arrays.asList(R.string.app_name,R.string.app_name,R.string.app_name,R.string.app_name));
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button btn = findViewById(R.id.btn_menu);
		initMenu();
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mMenuPopWindow != null){
					mMenuPopWindow.showAsDropDown(MainActivity.this, btn);
				}else {
					Toast.makeText(MainActivity.this, "null", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void initMenu() {
			try {
				mMenuPopWindow = new MenuPopWindow.Builder(this, lvIconName, new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						int resourceId = lvIcon.get(position);
						Intent it;
						switch (resourceId) {
							case R.drawable.ic_home_normal:
								Toast.makeText(MainActivity.this, "home", Toast.LENGTH_LONG).show();
								break;
						}
						mMenuPopWindow.dismiss();
					}
				}).setItemIcons(lvIcon).enableDot(lvIconName.indexOf(R.string.app_name), true).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	protected void onDestroy() {
		if (mMenuPopWindow != null) {
			mMenuPopWindow.dismiss();
		}
		super.onDestroy();
	}
}
