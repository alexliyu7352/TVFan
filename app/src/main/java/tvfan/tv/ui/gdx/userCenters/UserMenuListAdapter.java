package tvfan.tv.ui.gdx.userCenters;

/**
 * 個人中心-左側用戶界面
 *
 * @author 孫文龍
 *
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.luxtone.lib.gdx.Page;
import com.luxtone.lib.widget.AbsListView.IListItem;
import com.luxtone.lib.widget.CullGroup;
import com.luxtone.lib.widget.Grid.GridAdapter;
import com.luxtone.lib.widget.Widgets;

import tvfan.tv.App;
import tvfan.tv.R;
import tvfan.tv.lib.Utils;

public class UserMenuListAdapter extends GridAdapter {

	private Page page;
	private String[] menuArray;
	private UserMenuListItem item = null;

	public UserMenuListAdapter(Page page) {
		this.page = page;
	}

	public void setMenulist(String[] menuArray) {
		this.menuArray = menuArray;
	}

	@Override
	public int getCount() {
		// 获取总长度
		int count = 0;
		if (menuArray != null) {
			count = menuArray.length;
		}
		return count;
	}

	@Override
	public Actor getActor(int position, Actor convertActor) {

		if (convertActor == null) {
			item = new UserMenuListItem(page, position);
		} else {
			item = (UserMenuListItem) convertActor;
		}
		item.setDotVisible(false);
		item.setText(menuArray[position]);
		if (item.getText().equalsIgnoreCase("我的消息") && App.MESSAGENUMBER > 0)
			item.setDotVisible(true);
		item.setScale(1f);
		return item;
	}

	public void setFocusImgbg(boolean display) {
		((UserMenuListItem) item).setFocusImgBg(display);
	}

	class UserMenuListItem extends Group implements IListItem {
		// 成员变量
		private Image imagenew;
		private Label label;
		private Image focusimgbg, dot;
		private String text;
		private CullGroup cullGroup;
		private int CULLHEIGHT = 160;
		private int LABELWIDTH = 200;
		private int LABELHEIGHT = 91;
		private int LABELTXTSIZE = 45;
		private int MenuListHeight = 90;
		private int FOCUSBGWIDTH = 260;
		private int FOCUSBGHEIGHT = 80;

		public UserMenuListItem(Page page, int pos) {
			super(page);
			// 设置item大小
			setSize(265, MenuListHeight);
			setFocusAble(true);
			// 设置item内的画布
			cullGroup = new CullGroup(getPage());
			cullGroup.setSize(265, 90);
			// 选中背景图
			focusimgbg = new Image(getPage());
			focusimgbg.setDrawableResource(R.mipmap.leaderboard_light);
			focusimgbg.setSize(295, 120);
			focusimgbg.setPosition(-30, -32);
			focusimgbg.setVisible(false);
			// 设置item内的文字
			label = new Label(getPage(), false);
			label.setPosition(30, 0);
			label.setSize(165, 45);
			label.setTextSize(42);
			label.setAlignment(Align.center);
			label.setTextColor(android.graphics.Color.parseColor("#2D94E8"));
			if (pos == 2) {
				imagenew = Widgets.image(getPage(), (R.drawable.reddot));
				imagenew.setSize(18, 18);
				imagenew.setPosition(5, 10);
				imagenew.setVisible(false);
				cullGroup.addActor(imagenew);
			}

			dot = new Image(page);
			dot.setDrawableResource(R.drawable.reddot);
			dot.setSize(15, 15);
			dot.setPosition(10, 12);
			dot.setVisible(false);
			addActor(dot);

			// 加载场景
			cullGroup.addActor(focusimgbg);
			cullGroup.addActor(label);
			cullGroup.addActor(dot);
			addActor(cullGroup);
		}

		public void setText(String text) {
			label.setText(text);
			this.text = text;
		}

		public String getText() {
			return label.getText().toString();
		}


		public void setIsNew() {
			imagenew.setVisible(true);
		}

		public void setDotVisible(boolean visible) {
			dot.setVisible(visible);
		}

		@Override
		public void onRecycle() {
			label.setText("");
		}

		@Override
		public void onResume() {
			Utils.resetImageSource(focusimgbg, R.drawable.listbj);
			Utils.resetImageSource(imagenew, R.drawable.reddot);
			label.setText(text);
			super.onResume();
		}

		@Override
		public void notifyFocusChanged(boolean getFocus) {
			super.notifyFocusChanged(getFocus);
		}

		public void setNewImgBgGone() {
			if (imagenew != null && imagenew.isVisible())
				imagenew.setVisible(false);
		}

		@Override
		public void onSelected(boolean isSelected) {
			focusimgbg.setVisible(isSelected);
		}

		public void setFocusImgBg(boolean display) {
			focusimgbg.setVisible(display);
			if(display){
				label.setTextColor(android.graphics.Color.parseColor("#FFFFFF"));
			}else{
				label.setTextColor(android.graphics.Color.parseColor("#2D94E8"));
			}

		}

	}
}
