package ben.popupmenu;


import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LVSmallMenuAdapter extends BaseAdapter {
	private Context mContext;
	private List<Integer> mMenuDrawable;
	private List<Integer> mMenuTitle;
	private SparseArrayCompat<Boolean> mArrayCompat;

	public LVSmallMenuAdapter(Context context, List<Integer> menuDrawable, List<Integer> menuTitle) {
		this.mContext = context;
		this.mMenuDrawable = menuDrawable;
		this.mMenuTitle = menuTitle;
		this.mArrayCompat = new SparseArrayCompat<>();
	}

	public void enableDot(int position, boolean enable) {
		mArrayCompat.put(position, enable);
	}

	public void enableDot(int[] positions, boolean[] enables) {
		if (positions != null && enables != null && positions.length > 0 && enables.length > 0 && positions.length == enables.length) {
			for (int i = 0; i < positions.length; i++) {
				mArrayCompat.put(positions[i], enables[i]);
			}
		}
	}

	@Override
	public int getCount() {
		return mMenuTitle.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.view_lv_menu_small, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (mMenuDrawable != null) {
			holder.imageView.setImageResource(mMenuDrawable.get(position));
		} else {
			holder.imageView.setVisibility(View.GONE);
		}
		holder.textView.setText(mContext.getResources().getString(mMenuTitle.get(position)));
		holder.vDot.setVisibility(mArrayCompat.get(position) != null && mArrayCompat.get(position) ? View.VISIBLE : View.GONE);
		return convertView;
	}

	public class ViewHolder {
		AppCompatImageView imageView;
		View vDot;
		TextView textView;

		ViewHolder(View view) {
			imageView = view.findViewById(R.id.iv_menu);
			vDot = view.findViewById(R.id.v_dot);
			textView = view.findViewById(R.id.tv_menu_title);
		}
	}
}
