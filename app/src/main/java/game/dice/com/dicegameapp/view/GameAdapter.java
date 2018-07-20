package game.dice.com.dicegameapp.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.dto.GameDTO;
import game.dice.com.dicegameapp.domain.Game;

public class GameAdapter extends BaseAdapter {

    static class ViewHolder{
        TextView dice1;
        TextView dice2;
        TextView result;

        public ViewHolder(View view) {
            dice1 = view.findViewById(R.id.item_dice1);
            dice2 = view.findViewById(R.id.item_dice2);
            result = view.findViewById(R.id.item_result);
        }
    }

    private Context context;
    private List<GameDTO> items;

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
        // inflate the layout for each list row
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
        holder.dice1.setText(String.valueOf(diceValues[0]));
        holder.dice2.setText(String.valueOf(diceValues[1]));
        if (hasWon){
            holder.result.setText(R.string.listview_won);
        }
        else{
            holder.result.setText(R.string.listview_lost);
        }
    }
}
