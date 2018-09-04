package game.dice.com.dicegameapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.dto.GameDTO;
import game.dice.com.dicegameapp.domain.Game;

public class GameAdapter extends BaseAdapter {

    static class ViewHolder{
        ImageView dice1;
        ImageView dice2;
        TextView result;

        ViewHolder(View view) {
            dice1 = view.findViewById(R.id.item_dice1);
            dice2 = view.findViewById(R.id.item_dice2);
            result = view.findViewById(R.id.item_result);
        }
    }

    private Context context;
    private List<GameDTO> items;
    private Resources res;
    private TypedArray icons;

    public GameAdapter (Context context, List<GameDTO> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public GameDTO getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        createLayout(holder, i);
        return view;
    }

    private void createLayout(ViewHolder holder, int i){
        // Get current item to be displayed
        GameDTO currentGame = getItem(i);

        //Get data
        int[] diceValues = currentGame.getValueDices();
        boolean hasWon = currentGame.hasWon();

        //And set the data
        setData(holder, diceValues, hasWon);
    }

    private void setData(ViewHolder holder, int[] diceValues, boolean hasWon){
        //Getting the array containing the drawables
        res = context.getResources();
        icons = res.obtainTypedArray(R.array.dice_numbers);
        //Setting the drawable to each Imageview
        holder.dice1.setImageDrawable(icons.getDrawable(diceValues[0]-1));
        holder.dice2.setImageDrawable(icons.getDrawable(diceValues[1]-1));

        if (hasWon){
            holder.result.setText(R.string.listview_won);
        }
        else{
            holder.result.setText(R.string.listview_lost);
        }
    }
}
