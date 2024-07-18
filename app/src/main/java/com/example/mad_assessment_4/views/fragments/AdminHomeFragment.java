package com.example.mad_assessment_4.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.adapters.PizzaAdapter;
import com.example.mad_assessment_4.controllers.Controller;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.views.AdminHomeActivity;
import com.example.mad_assessment_4.views.DashboardActivity;
import com.example.mad_assessment_4.views.LoginActivity;
import com.example.mad_assessment_4.views.PizzaHomeActivity;

import java.util.List;

public class AdminHomeFragment extends Fragment {

    private RecyclerView recyclerViewPizzas;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> pizzaList;

    private Controller controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        controller = new Controller(getActivity());

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AdminHomeActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Admin Home");

        ((AdminHomeActivity) getActivity()).getSupportActionBar().setTitle("Home");
        setHasOptionsMenu(true);
        Intent pizzaHome = new Intent(getActivity(), PizzaHomeActivity.class);
        Intent loginScreen = new Intent(getActivity(), LoginActivity.class);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btnLogout) {
                    controller.logout(getActivity());
                    startActivity(loginScreen);
                    getActivity().finish();
                    return true;
                }else if(item.getItemId() == R.id.pizza_button){
                    startActivity(pizzaHome);
                }
                return false;
            }
        });
        recyclerViewPizzas = view.findViewById(R.id.recyclerViewPizzas);
        recyclerViewPizzas.setLayoutManager(new LinearLayoutManager(getActivity()));

        pizzaList = controller.getAllPizza();
        pizzaAdapter = new PizzaAdapter(getActivity(), pizzaList, this::deletePizza);
        recyclerViewPizzas.setAdapter(pizzaAdapter);

        return view;
    }

    private void deletePizza(Pizza pizza) {
//        PizzaManager.getInstance().removePizza(pizza); // Implement this method to remove the pizza from the data source
        pizzaList.remove(pizza);
        pizzaAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Pizza deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
