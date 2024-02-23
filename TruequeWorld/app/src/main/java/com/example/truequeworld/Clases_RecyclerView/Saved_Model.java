package com.example.truequeworld.Clases_RecyclerView;

import android.graphics.Bitmap;

public class Saved_Model {
    private Integer id;
    private String favProduct_name;
    private Bitmap favProduct_img;
    private String fav_Product_descripcion;

    public Saved_Model(Integer id, String favProduct_name, Bitmap favProduct_img, String fav_Product_descripcion) {
        this.id = id;
        this.favProduct_name = favProduct_name;
        this.favProduct_img = favProduct_img;
        this.fav_Product_descripcion = fav_Product_descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getFavProduct_name() {
        return favProduct_name;
    }

    public Bitmap getFavProduct_img() {
        return favProduct_img;
    }

    public String getFav_Product_descripcion() {
        return fav_Product_descripcion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFavProduct_name(String favProduct_name) {
        this.favProduct_name = favProduct_name;
    }

    public void setFavProduct_img(Bitmap favProduct_img) {
        this.favProduct_img = favProduct_img;
    }

    public void setFav_Product_descripcion(String fav_Product_descripcion) {
        this.fav_Product_descripcion = fav_Product_descripcion;
    }
}
//
/*
SI QUIERES QUE SE MUESTRE SUPONGO QUE SERA ALGO PARECIDO A ESTO
public class TuFragment extends Fragment {

    private RecyclerView recyclerView;
    private TuAdapter adapter;
    private List<TuObjeto> tuLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tu, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializa tu lista de objetos y el adaptador
        tuLista = obtenerTuLista(); // Método para obtener tus datos
        adapter = new TuAdapter(tuLista, getContext());

        // Configura el adaptador en el RecyclerView
        recyclerView.setAdapter(adapter);

        return view;
    }
}
 */