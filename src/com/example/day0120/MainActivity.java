package com.example.day0120;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//今日はリストビューのアダプタカスタムの復習
public class MainActivity extends Activity {
	private final String[] NAME = { "a", "b", "c", "d", "e", "f", "g", "h" };
	private final int[] GENDER = { 0, 0, 0, 0, 1, 1, 1, 1 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ListView listView = (ListView) findViewById(R.id.listview);
		final List<DTO> list = new ArrayList<DTO>();
		for (int i = 0; i < NAME.length; i++) {
			DTO dto = new DTO();
			dto.setName(NAME[i]);
			dto.setNumber(i + 1);
			dto.setGender(GENDER[i]);
			list.add(dto);
		}
		Log.i("Main list.size", list.size() + "");
		final MyCustomAdapter myAda = new MyCustomAdapter(this,
				R.layout.list_item, list);

		Button btn = (Button) findViewById(R.id.btn);

		//ボタンをclickしたら、リストを表示
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listView.setAdapter(myAda);
			}
		});

		//リストに表示されたアイテムをクリックするとトースト表示
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long arg3) {
				DTO dto = list.get(position);
				Toast.makeText(getApplicationContext(),
						dto.getNumber() + " " + dto.getName(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

class MyCustomAdapter extends ArrayAdapter<DTO> {
	private int resourceId;

	public MyCustomAdapter(Context context, int resource, List<DTO> list) {
		super(context, resource, list);
		this.resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = View.inflate(getContext(), resourceId, null);

		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView number = (TextView) convertView.findViewById(R.id.number);
		TextView gender = (TextView) convertView.findViewById(R.id.gender);

		DTO item = getItem(position);

		String nameStr = item.getName();
		int numInt = item.getNumber();
		String genderStr;
		if (0 == item.getGender()) {
			genderStr = "男";
		} else {
			genderStr = "女";
		}
		number.setText(numInt + "");
		name.setText(nameStr);
		gender.setText(genderStr);

		return convertView;
	}

}