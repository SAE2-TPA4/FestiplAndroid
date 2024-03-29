package but2.s4.festiplandroid.adaptater;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import but2.s4.festiplandroid.DetailsActivity;
import but2.s4.festiplandroid.R;
import but2.s4.festiplandroid.api.ApiResponse;
import but2.s4.festiplandroid.api.FestiplanApi;
import but2.s4.festiplandroid.festivals.Festival;
import but2.s4.festiplandroid.session.User;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.FestivalViewHolder> {

    private List<Festival> festivalList;

    public FestivalAdapter(List<Festival> festivalList) {
        this.festivalList = festivalList;
    }

    @NonNull
    @Override
    public FestivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_festival, parent, false);
        return new FestivalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalViewHolder holder, int position) {
        Festival festival = festivalList.get(position);
        holder.nomFestival.setText(festival.getNomFestival());
        holder.dateDebutFestival.setText(festival.getDateDebutFestival());
        holder.dateFinFestival.setText(festival.getDateFinFestival());
        holder.buttonShowDetails.setId(festival.getIdFestival());
        holder.buttonShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra("ID_EXTRA", festival.getIdFestival());
                    v.getContext().startActivity(intent);
                }
            });
    }
    @Override
    public int getItemCount() {
        return festivalList.size();
    }

    public static class FestivalViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFestival;
        TextView nomFestival;
        TextView categorieFestival;
        TextView dateDebutFestival;
        TextView dateFinFestival;
        Button buttonShowDetails;
        ImageButton buttonAddToFavorites;



        public FestivalViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFestival = itemView.findViewById(R.id.image_festival);
            nomFestival = itemView.findViewById(R.id.nom_festival);
            categorieFestival = itemView.findViewById(R.id.categorie_festival);
            dateDebutFestival = itemView.findViewById(R.id.date_debut_festival);
            dateFinFestival = itemView.findViewById(R.id.date_fin_festival);
            buttonShowDetails = itemView.findViewById(R.id.button_show_details);
        }
    }
}
