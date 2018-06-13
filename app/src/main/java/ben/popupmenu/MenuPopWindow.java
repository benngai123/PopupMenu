package ben.popupmenu;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import java.util.List;

public class MenuPopWindow extends PopupWindow {

	private Context mContext;
	private List<Integer> mItemNames;
	private AdapterView.OnItemClickListener mOnItemClickListener;
	private List<Integer> mItemIcons;
	private int dotPosition;
	private boolean dotEnable;

	private View contentView;
	private ListViewAdaptWidth mLvFunction;

	public static class Builder {

		//Required
		private Context context;
		private List<Integer> itemNames;
		private AdapterView.OnItemClickListener mOnItemClickListener;

		public Builder(Context context, List<Integer> itemNames, AdapterView.OnItemClickListener listener) {
			this.context = context;
			this.itemNames = itemNames;
			this.mOnItemClickListener = listener;
		}

		//Optional
		private List<Integer> itemIcons;
		private int dotPosition;
		private boolean dotEnable;

		public Builder setItemIcons(List<Integer> itemIcons) {
			this.itemIcons = itemIcons;
			return this;
		}

		public Builder enableDot(int dotPosition, boolean dotEnable) {
			this.dotPosition = dotPosition;
			this.dotEnable = dotEnable;
			return this;
		}

		public MenuPopWindow build() {
			return new MenuPopWindow(this);
		}
	}

	private MenuPopWindow(Builder builder) {
		this.mContext = builder.context;
		this.mItemNames = builder.itemNames;
		this.mItemIcons = builder.itemIcons;
		this.mOnItemClickListener = builder.mOnItemClickListener;
		this.dotPosition = builder.dotPosition;
		this.dotEnable = builder.dotEnable;
		initView();
	}

	private void initView() {
		contentView = LayoutInflater.from(mContext).inflate(R.layout.view_pop_more, null);
		mLvFunction = contentView.findViewById(R.id.lv_adapt_width);
		mLvFunction.setOnItemClickListener(mOnItemClickListener);
		LVSmallMenuAdapter adapter = new LVSmallMenuAdapter(mContext, mItemIcons, mItemNames);
		adapter.enableDot(dotPosition, dotEnable);
		mLvFunction.setAdapter(adapter);
		mLvFunction.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

		setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		setAnimationStyle(R.style.Animations_PopDownMenu_Right);
		setOutsideTouchable(true);
		setTouchable(true);
		setFocusable(true);

		setContentView(contentView);
		setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		update();
	}

	/**
	 * Show date picker popWindow
	 */
	public void showAsDropDown(final Activity activity, View view) {
		if (null != activity && null != view) {
			setOnDismissListener(new PopupWindow.OnDismissListener() {
				public void onDismiss() {
					WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
					lp.alpha = 1f;
					activity.getWindow().setAttributes(lp);
				}
			});


			view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
			showAsDropDown(view);

			//虚化背景
			WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
			lp.alpha = 0.7f;
			activity.getWindow().setAttributes(lp);
		}
	}


}
