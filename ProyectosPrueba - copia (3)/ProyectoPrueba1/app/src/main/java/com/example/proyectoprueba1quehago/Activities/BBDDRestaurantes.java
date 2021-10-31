package com.example.proyectoprueba1quehago.Activities;

import java.util.ArrayList;

public class BBDDRestaurantes {
    ArrayList<Restaurante> listaRestaurantes;

    public BBDDRestaurantes() {
        listaRestaurantes=new ArrayList<>();
        llenarLista();
    }

    private void llenarLista() {
        //Restaurantes Centro
        Restaurante restaurante;
        restaurante=new Restaurante("Pandino","Justicia", "", "Centro","carne queso yogur frutas maracuya","9,3");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("El Tormo","Palacio", "30-45 euros", "Centro","gazpacho pisto codornices bacalao chocolate","9,3");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("Maridaje Divino","Palacio", "-20 euros", "Centro","tarta croquetas queso pescado","9,3");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("Livin'Japan","Embajadores", "", "Centro","ramen curry tarta pollo queso","9,2");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("Acuarela Bistró Bar","Palacio", "", "Centro","tartacevichequesocarne","9,2");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("La Esquina Del Real","Sol", "45-60 euros", "Centro","foieen salada pato solomillo tarta","9,2");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("Barganzo","Justicia", "", "Centro","pita ensalada setas berenjena queso","9,1");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("Chila","Palacio", "20-30 euros", "Centro","picante arroz panceta coliflor carne","9,1");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("Cebo","Cortes", "+100 euros", "Centro","arroz bonito carne callos gamba","9,1");
        listaRestaurantes.add(restaurante);
        restaurante=new Restaurante("Sake Bar Shuwa Shuwa","Justicia", "30-45 euros", "Centro","sake sushi carne pescado arroz","9,1");
        listaRestaurantes.add(restaurante);

        //Restaurantes
    }

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }
}
