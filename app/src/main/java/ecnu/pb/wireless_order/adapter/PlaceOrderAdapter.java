package ecnu.pb.wireless_order.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ecnu.pb.wireless_order.R;
import ecnu.pb.wireless_order.model.MenuModel;

public class PlaceOrderAdapter extends BaseAdapter {

    private Context context;
    private List<MenuModel> list;

    public PlaceOrderAdapter(Context context, List<MenuModel> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.place_list_item, null);
        }
        final MenuModel menu = list.get(position);
        final ViewHolder holder = new ViewHolder(view);
        holder.name.setText(menu.getName());
        holder.number.setText("x " + menu.getNum());
        holder.price.setText("$ " + menu.getPrice());
        holder.photo.setImageResource(menu.getPhoto());
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setNum(list.get(position).getNum() + 1);
                holder.number.setText("x " + menu.getNum());
            }
        });

        holder.minBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getNum() > 1) {
                    menu.setNum(list.get(position).getNum() - 1);
                    holder.number.setText("x " + menu.getNum());
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("是否删除")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", null)
                            .show();
                }
            }
        });

        return view;
    }

    class ViewHolder {
        @Bind(R.id.iv_photo)
        ImageView photo;
        @Bind(R.id.txt_menu_name)
        TextView name;
        @Bind(R.id.txt_menu_number)
        TextView number;
        @Bind(R.id.txt_menu_price)
        TextView price;
        @Bind(R.id.btn_add)
        Button addBtn;
        @Bind(R.id.btn_minus)
        Button minBtn;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
