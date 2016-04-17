package ecnu.pb.wireless_order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ecnu.pb.wireless_order.R;
import ecnu.pb.wireless_order.model.MenuModel;

public class OrderDetailAdapter extends BaseAdapter{

    private Context context;
    private List<MenuModel> list;

    public OrderDetailAdapter(Context context, List<MenuModel> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.order_detail_list_item, null);
        }
        MenuModel menu = list.get(position);
        ViewHolder holder = new ViewHolder(view);

        holder.name.setText(menu.getName());
        holder.number.setText("x " + menu.getNum());
        holder.price.setText("$ " + menu.getPrice());
        return view;
    }

    class ViewHolder {
        @Bind(R.id.txt_menu_name)
        TextView name;
        @Bind(R.id.txt_menu_number)
        TextView number;
        @Bind(R.id.txt_menu_total)
        TextView price;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
