package com.revature.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Pokemon;
import com.revature.service.PokemonService;

public class PokemonWebService {
	
	public static ObjectMapper om = new ObjectMapper();

	public static void getPokemon(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		//System.out.println(id);
		Pokemon p = PokemonService.getPokemon(id);
		
		//some code/ some logic;
		
		
		try {
			String pokemonJSON = om.writeValueAsString(p);
			response.getWriter().append(pokemonJSON).close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void addPokemon(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		System.out.println(id + " " + name + " " + type);
		
		Pokemon p = new Pokemon(id, name, type);
		PokemonService.addPokemon(p);
		try {
			response.getWriter().append("Pokemon added to the database.").close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void getAllPokemon(HttpServletRequest request, HttpServletResponse response) {
		
		List<Pokemon> polkamans = PokemonService.getAllPokemon();
		try {
			response.getWriter().append(om.writeValueAsString(polkamans));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
