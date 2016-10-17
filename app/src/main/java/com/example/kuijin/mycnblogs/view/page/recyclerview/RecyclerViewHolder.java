package com.example.kuijin.mycnblogs.view.page.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuijin.mycnblogs.R;
import com.example.kuijin.mycnblogs.common.TimeUtil;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;
import com.example.kuijin.mycnblogs.presenter.page.itemoverview.IItemOverviewPresenter;
import com.example.kuijin.mycnblogs.presenter.page.itemoverview.ItemOverviewPresenterManager;
import com.example.kuijin.mycnblogs.presenter.page.pageFragmentPresenter.IPageFragmentPresenter;

/**
 * Created by kuijin on 2016/9/15.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    View itemView;
    TextView itemTitle;
    //	View itemSummaryLayout;
    ImageView writerImage;
    TextView itemSummary;
    //	View writerInfo;
    TextView writerName;
    TextView writeTime;
    //	TextView writerSpace;
//	View recommendLayout;
//	ImageView recommendImage;
    TextView recommendCount;
    //	View readedLayout;
//	ImageView readedImage;
    TextView readedCount;
    //	View replyLayout;
//	ImageView replyImage;
    TextView replyCount;

    String newsUrl;

    private IPageFragmentPresenter.OnItemClickListener onItemClickListener = null;

    private Context context = null;

    public RecyclerViewHolder(Context context, View view, IPageFragmentPresenter.OnItemClickListener listener) {
        super(view);

        this.context = context;

        itemView = view.findViewById(R.id.item);
        itemTitle = (TextView) view.findViewById(R.id.item_title);
        writerImage = (ImageView) view.findViewById(R.id.item_image);
        itemSummary = (TextView) view.findViewById(R.id.item_summary);
        writerName = (TextView) view.findViewById(R.id.item_writerinfo_name);
        writeTime = (TextView) view.findViewById(R.id.item_writerinfo_time);
//		writerSpace = (TextView) view.findViewById(R.id.item_writerinfo_space);
        recommendCount = (TextView) view.findViewById(R.id.item_writerinfo_recommend_count);
        readedCount = (TextView) view.findViewById(R.id.item_writerinfo_readed_count);
        replyCount = (TextView) view.findViewById(R.id.item_writerinfo_reply_count);

        onItemClickListener = listener;
    }

    public static View getView(LayoutInflater inflater, ViewGroup viewgroup, int viewType) {
        View view = inflater.inflate(R.layout.layout_recyclerview_item, null);
        return view;
    }

    public void bindViewHolder(IItemOverviewModel obj) {
        if (null == obj) {
            return;
        }

        ItemOverviewModel item = null;
        if (obj instanceof ItemOverviewModel) {
            item = (ItemOverviewModel) obj;
        } else {
            return;
        }

        itemTitle.setText(item.getTitle());
        itemSummary.setText(item.getSummary());
        writerName.setText(item.getWriterName());
        //writeTime.setText(item.getWriteTime());
        writeTime.setText(TimeUtil.DateToChineseString(TimeUtil.ParseUTCDate(item.getWriteTime())));
        recommendCount.setText("" + item.getRecommendCount());
        readedCount.setText("" + item.getReadedCount());
        replyCount.setText("" + item.getReplyCount());

        newsUrl = item.getNewsUrl();

        String imageUrl = item.getWriterImageSrc();
        IItemOverviewPresenter presenter = ItemOverviewPresenterManager.getItemOverviewPresenter(context);
        //presenter.setImage(writerImage, imageUrl, R.drawable.ic_hearder_default, R.drawable.ic_header_error);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(newsUrl);
                }
            }
        });
    }
}
