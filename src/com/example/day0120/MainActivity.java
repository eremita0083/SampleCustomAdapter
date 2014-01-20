package com.example.day0120;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


//今日はリストビューのアダプタカスタムの復習
public class MainActivity extends Activity {
	private final String[] NAME = { "a", "b", "ニーはお", "ボンジュール", "a",
			"b", "c", "d" };
	private final int[] GENDER = {0,0,0,0,1,1,1,1};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ListView listView = (ListView) findViewById(R.id.listview);
		List<DTO> list = new ArrayList<DTO>();
		for (int i = 0; i < NAME.length; i++) {
			DTO dto = new DTO();
			dto.setName(NAME[i]);
			dto.setNumber(i+1);
			dto.setGender(GENDER[i]);
		}
		final MyCustomAdapter myAda = new MyCustomAdapter(this, R.layout.list_item, list);
		
		Button btn = (Button) findViewById(R.id.btn);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listView.setAdapter(myAda);
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

class MyCustomAdapter extends ArrayAdapter<DTO>{
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
		if( 0 == item.getGender()){
			genderStr = "男";
		}else{
			genderStr = "女";
		}
		number.setText(numInt+"");
		name.setText(nameStr);
		gender.setText(genderStr);
		
		return convertView;
	}
	
}