package ecnu.pb.wireless_order.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ecnu.pb.wireless_order.model.MenuModel;

public class MenuManager extends TableManager<MenuModel> {
    public static final String TABLE_NAME = "menu";

    private static final String COL_MENU_ID = "menu_id";
    private static final String COL_MENU_NAME = "menu_name";
    private static final String COL_MENU_PHOTO = "menu_photo";
    private static final String COL_MENU_PRICE = "menu_price";
    private static final String COL_MENU_CHOOSE = "choose";
    private static final String COL_MENU_INTRO = "intro";
    private static final String COL_MENU_NUM = "num";

    public static MenuManager createInstance() {
        return new MenuManager();
    }

    private MenuManager() {
        super(TABLE_NAME);
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        String sql;
        sql = "CREATE TABLE " + TABLE_NAME + "(_id int PRIMARY_KEY AUTO_INCREMENT," +
                COL_MENU_ID + " int UNIQUE," +
                COL_MENU_NAME + " varchar," +
                COL_MENU_PHOTO + " int," +
                COL_MENU_PRICE + " int," +
                COL_MENU_INTRO + " varchar," +
                COL_MENU_CHOOSE + " int," +
                COL_MENU_NUM + " int)";
        db.execSQL(sql);
    }

    @Override
    public ContentValues createContentValues(MenuModel menu) {
        ContentValues values = new ContentValues();
        values.put(COL_MENU_ID, menu.getId());
        values.put(COL_MENU_NAME, menu.getName());
        values.put(COL_MENU_PHOTO, menu.getPhoto());
        values.put(COL_MENU_PRICE, menu.getPrice());
        values.put(COL_MENU_CHOOSE, menu.getChoose());
        values.put(COL_MENU_INTRO, menu.getIntro());
        values.put(COL_MENU_NUM, menu.getNum());
        return values;
    }

    @Override
    public MenuModel createModel(SQLiteDatabase db, Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(COL_MENU_ID));
        String name = cursor.getString(cursor.getColumnIndex(COL_MENU_NAME));
        int price = cursor.getInt(cursor.getColumnIndex(COL_MENU_PRICE));
        int photo = cursor.getInt(cursor.getColumnIndex(COL_MENU_PHOTO));
        int choose = cursor.getInt(cursor.getColumnIndex(COL_MENU_CHOOSE));
        String intro = cursor.getString(cursor.getColumnIndex(COL_MENU_INTRO));
        int num = cursor.getInt(cursor.getColumnIndex(COL_MENU_NUM));
        return new MenuModel(id, name, price, photo, choose, intro, num);
    }

    public MenuModel getmenuById(Context context, int menuId) {
        SQLiteDatabase db = DatabaseHelper.getDatabase(context);
        String where = COL_MENU_ID + "=" + menuId;
        Cursor cursor = db.query(TABLE_NAME, null, where, null, null, null, null);
        if (cursor.moveToFirst()) {
            return createModel(db, cursor);
        }
        return null;
    }
}

