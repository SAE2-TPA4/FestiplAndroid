package but2.s4.festiplandroid.adaptater;

import android.content.Context;
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
import but2.s4.festiplandroid.FavoritesActivity;
import but2.s4.festiplandroid.R;
import but2.s4.festiplandroid.ScheduledActivity;
import but2.s4.festiplandroid.api.ApiResponse;
import but2.s4.festiplandroid.api.FestiplanApi;
import but2.s4.festiplandroid.festivals.Festival;
import but2.s4.festiplandroid.navigation.Navigator;
import but2.s4.festiplandroid.session.User;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.FestivalViewHolder> {

    private final List<Festival> festivalList;

    public FestivalAdapter(List<Festival> festivalList) {
        this.festivalList = festivalList;
    }

    @NonNull
    @Override
    public FestivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_festival, parent, false);
        return new FestivalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalViewHolder holder, int position) {
        Festival festival = festivalList.get(position);
        holder.nomFestival.setText(festival.getNomFestival());
        holder.categorie.setText(festival.getCategorieFestival());
        holder.dateDebut.setText(festival.getDateDebutFestival());
        holder.dateFin.setText(festival.getDateFinFestival());
        holder.buttonAddToFavorites.setId(festival.getIdFestival());
        holder.buttonShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("ID_EXTRA", festival.getDateFinFestival());
                v.getContext().startActivity(intent);
            }
        });
        holder.buttonAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imageButton = (ImageButton) v;
                int idUser = User.getInstance().getIdUser();
                if (festival.getFavorite()) {
                    imageButton.setImageResource(R.drawable.favorites_deselected);
                    final String[] apiResponse = new String[1];
                    ApiResponse response = new ApiResponse() {
                        @Override
                        public void onResponse(String response) {
                            apiResponse[0] = response;
                            System.out.println(apiResponse[0]);
                        }
                    };
                    FestiplanApi.deleteFavoritesFestivalsDeleteListener(idUser, festival.getIdFestival(), response);

                } else {
                    imageButton.setImageResource(R.drawable.favorites_selected);
                    final String[] apiResponse = new String[1];
                    ApiResponse response = new ApiResponse() {
                        @Override
                        public void onResponse(String response) {
                            apiResponse[0] = response;
                            System.out.println(apiResponse[0]);
                        }
                    };
                    FestiplanApi.createFavoritesFestivalsPostListener(idUser, festival.getIdFestival(), response);
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return festivalList.size();
    }

    /**
     * Association des fetivals à la vue du recyclerView
     */
    public static class FestivalViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageFestival;
        public TextView nomFestival;
        public TextView categorie;
        public TextView dateDebut;
        public TextView dateFin;
        public Button buttonShowDetails;
        public ImageButton buttonAddToFavorites;

        public FestivalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFestival = itemView.findViewById(R.id.image_festival);
            nomFestival = itemView.findViewById(R.id.nom_festival);
            categorie = itemView.findViewById(R.id.categorie_festival);
            dateDebut = itemView.findViewById(R.id.date_debut_festival);
            dateFin = itemView.findViewById(R.id.date_fin_festival);
            buttonShowDetails = itemView.findViewById(R.id.buttonShowDetails);
            buttonAddToFavorites = itemView.findViewById(R.id.button_add_to_favorites);
        }
    }
}
