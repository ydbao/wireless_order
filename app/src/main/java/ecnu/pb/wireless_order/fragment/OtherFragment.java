package ecnu.pb.wireless_order.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ecnu.pb.wireless_order.R;
import ecnu.pb.wireless_order.adapter.MenuFragmentAdapter;

public class OtherFragment extends Fragment {

    private int[] pics = {
            R.mipmap.img10, R.mipmap.img9, R.mipmap.img8,
            R.mipmap.img0, R.mipmap.img1, R.mipmap.img2,
            R.mipmap.img3, R.mipmap.img4, R.mipmap.img5};

    private MenuFragmentAdapter mAdapter;

    public OtherFragment() {}

    @Bind(R.id.gv_menu)
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mAdapter = new MenuFragmentAdapter(getActivity(), pics);
        gridView.setAdapter(mAdapter);
    }
}
