package com.example.kuijin.mycnblogs.view.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kuijin.mycnblogs.R;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;
import com.example.kuijin.mycnblogs.presenter.page.recyclerview.RecyclerViewAdapter;
import com.example.kuijin.mycnblogs.view.page.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuijin on 2016/9/29.
 */
public class TestFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<ItemOverviewModel> listModels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initRecyclerView(inflater);

//        TextView view = new TextView(getActivity());
//        view.setText("aaaaa");
        return view;
    }

    @NonNull
    private View initRecyclerView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.layout_fragment, null);

        initModels();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), listModels, null);
        recyclerView.setAdapter(adapter);

//        recyclerView.setAdapter(new RecyclerView.Adapter() {
//
//
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new MyViewHolder(getActivity().getLayoutInflater().inflate(R.layout.layout_recyclerview_item, null));
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                ((MyViewHolder)holder).bind(listModels.get(position));
//            }
//
//            @Override
//            public int getItemCount() {
//                return listModels.size();
//            }
//        });

        return view;
    }

    private void initModels() {
        if (null != listModels) {
            return;
        }

        listModels = new ArrayList<>();

        ItemOverviewModel model = new ItemOverviewModel();
        model.setTitle("aaa");
        model.setSummary("1111");
        model.setPublishTime("publishTime:aaa");

        ItemOverviewModel model1 = new ItemOverviewModel();
        model1.setTitle("bb");
        model1.setSummary("2222");
        model1.setPublishTime("publishTime:bbb");

        listModels.add(model);
        listModels.add(model1);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView summary;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            summary= (TextView) itemView.findViewById(R.id.item_summary);
        }

        public void bind(ItemOverviewModel item) {
            title.setText(item.getTitle());
            summary.setText(item.getSummary());
        }
    }


}
