package tvfan.tv.ui.gdx.userCenter;

/**
 * 個人中心-播放歷史界面
 * 
 * @author 孫文龍
 * 
 */
import java.util.ArrayList;
import java.util.List;

import tvfan.tv.dal.models.MPlayRecordInfo;
import tvfan.tv.lib.Utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.luxtone.lib.gdx.Page;
import com.luxtone.lib.widget.Grid.GridAdapter;

public class PlayHistoryGridAdapter extends GridAdapter {
	private Page page;
	private List<MPlayRecordInfo> listdata = new ArrayList<MPlayRecordInfo>();

	public PlayHistoryGridAdapter(Page page) {
		this.page = page;
	}

	@Override
	public Actor getActor(int position, Actor convertActor) {
		PlayHistoryGridItem item = null;

		if (convertActor == null) {
			item = new PlayHistoryGridItem(page, 0, 7);
		} else {
			item = (PlayHistoryGridItem) convertActor;
		}
		item.update(listdata.get(position).getPicUrl());
		item.setLeftCornerImage(listdata.get(position).getCornerPrice());
		item.setRightCornerImage(listdata.get(position).getCornerType());
		if (listdata.get(position).getPlayerpos() == -1) {
			item.setText(listdata.get(position).getPlayerName(),
					Utils.millisToString(listdata.get(position).getPonitime()));
		} else {
			item.setText(listdata.get(position).getPlayerName(),
					String.valueOf(listdata.get(position).getPlayerpos() + 1));
		}

		return item;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listdata.size();
	}

	public void setData(ArrayList<MPlayRecordInfo> items) {
		this.listdata = items;
	}
}
