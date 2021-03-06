package com.meli.servicios;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.meli.constantes.SatellitesPosition;
import com.meli.exception.ChallengeMeliException;

import static com.meli.constantes.Constants.MESSAGE_ERROR;
import static com.meli.constantes.Constants.NUMBER_SATELLITES_IN_SERVICE;
import static com.meli.constantes.Constants.POSITION_ERROR;
import static com.meli.constantes.SatellitesPosition.KENOBI;
import static com.meli.constantes.SatellitesPosition.SATO;
import static com.meli.constantes.SatellitesPosition.SKYWALKER;
import static com.meli.constantes.Constants.EPSILON;

import com.meli.objetos.Circle;
import com.meli.objetos.Position;
import com.meli.objetos.Request;
import com.meli.objetos.RequestPost;
import com.meli.objetos.Response;
import com.meli.objetos.Satelite;

public class TopSecretService {
	private List<Satelite> queue = new ArrayList<>();
	private List<String> salliteNames;

	public TopSecretService() {

		salliteNames = Stream.of(SatellitesPosition.values()).map(Enum::name).collect(Collectors.toList());
	}
	
	 public Response topSeretService(List<Satelite> satellites) throws ChallengeMeliException {
	        if (satellites == null || satellites.isEmpty() || satellites.size() != NUMBER_SATELLITES_IN_SERVICE ) {
	            throw new ChallengeMeliException(POSITION_ERROR);
	        }
	        Position position = getLocation(satellites);
	        String message = getMessage(satellites);
	       
	        return new Response(position, message);
	    }

	    private Position getLocation(List<Satelite> satellites) {
	        double a, dx, dy, d, h, rx, ry;
	        double point2_x, point2_y;

	        float distance_kenobi = satellites.stream().filter(satellite -> satellite.getName().equalsIgnoreCase(KENOBI.name())).findFirst().get().getDistance();
	        float distance_skywalker = satellites.stream().filter(satellite -> satellite.getName().equalsIgnoreCase(SKYWALKER.name())).findFirst().get().getDistance();
	        float distance_sato = satellites.stream().filter(satellite -> satellite.getName().equalsIgnoreCase(SATO.name())).findFirst().get().getDistance();

	        Circle circle0 = new Circle(KENOBI, distance_kenobi);
	        Circle circle1 = new Circle(SKYWALKER, distance_skywalker);
	        Circle circle2 = new Circle(SATO, distance_sato);

	        // dx y dy son las distancias vertical y horizontal entre los centros del círculo
	        dx = circle1.getCoordenadaX() - circle0.getCoordenadaX();
	        dy = circle1.getCoordenadaY() - circle0.getCoordenadaY();

	        d = Math.sqrt((dy * dy) + (dx * dx));

	        //Validar si los circulos se interceptan
	        if (d > (circle0.getRadio() + circle1.getRadio())) {
	            //sin solución. los círculos no se intercepta.
	            throw new ChallengeMeliException(POSITION_ERROR);
	        }
	        if (d < Math.abs(circle0.getRadio() - circle1.getRadio())) {
	            //sin solución. un círculo está contenido en el otro
	            throw new ChallengeMeliException(POSITION_ERROR);
	        }

	        //Determine la distancia del punto 0 al punto 2
	        a = ((circle0.getRadio() * circle0.getRadio()) - (circle1.getRadio() * circle1.getRadio()) + (d * d)) / (2.0 * d);

	        //Determine las coordenadas del punto 2
	        point2_x = circle0.getCoordenadaX() + (dx * a / d);
	        point2_y = circle0.getCoordenadaY() + (dy * a / d);

	        //Determine la distancia desde el punto 2 a cualquiera de los  puntos de intersección.
	        h = Math.sqrt((circle0.getRadio() * circle0.getRadio()) - (a * a));

	        rx = -dy * (h / d);//-h ( x1 - x0 ) / d
	        ry = dx * (h / d);// h ( x1 - x0 ) / d

	        //Determinar los puntos absolutos de intersección
	        //x3 = x2 + h ( y1 - y0 ) / d
	        double intersectionPoint1_x = point2_x + rx;
	        //x3 = x2 - h ( y1 - y0 ) / d
	        double intersectionPoint2_x = point2_x - rx;
	        //y3 = y2 + h ( x1 - x0 ) / d
	        double intersectionPoint1_y = point2_y + ry;
	        //y3 = y2 - h ( x1 - x0 ) / d
	        double intersectionPoint2_y = point2_y - ry;

	        /* Lets determine if circle 3 intersects at either of the above intersection points. */
	        dx = intersectionPoint1_x - circle2.getCoordenadaX();
	        dy = intersectionPoint1_y - circle2.getCoordenadaY();
	        double d1 = Math.sqrt((dy * dy) + (dx * dx));

	        dx = intersectionPoint2_x - circle2.getCoordenadaX();
	        dy = intersectionPoint2_y - circle2.getCoordenadaY();
	        double d2 = Math.sqrt((dy * dy) + (dx * dx));

	        if (Math.abs(d1 - circle2.getRadio()) < EPSILON) {
	        	return new Position(intersectionPoint1_x, intersectionPoint1_y);	           
	        } else if (Math.abs(d2 - circle2.getRadio()) < EPSILON) {
	            throw new ChallengeMeliException(POSITION_ERROR);
	        } else {
	            throw new ChallengeMeliException(POSITION_ERROR);
	        }
	    }

	    private String getMessage(List<Satelite> satellites) {
	        List<String> message0 = satellites.get(0).getMessage();
	        List<String> message1 = satellites.get(1).getMessage();
	        List<String> message2 = satellites.get(2).getMessage();
	        if (CollectionUtils.isEmpty(message0) || CollectionUtils.isEmpty(message1) || CollectionUtils.isEmpty(message2)) {
	            throw new ChallengeMeliException(MESSAGE_ERROR);
	        }
	        //Validar si la listas de mensajes tienen el mismo tamaño
	        if (message0.size() != message1.size() || message0.size() != message2.size()) {
	            throw new ChallengeMeliException(MESSAGE_ERROR);
	        }
	        StringBuilder messageBuilder = new StringBuilder();
	        Set<String> tempSet = new LinkedHashSet<>();

	        for (int i = 0; i < message0.size(); i++) {
	            //Validar si existen palabras en la posicion i de cada lista de mensajes
	            if (StringUtils.isBlank(message0.get(i))
	                    && StringUtils.isBlank(message1.get(i))
	                    && StringUtils.isBlank(message2.get(i))) {
	                throw new ChallengeMeliException(MESSAGE_ERROR);
	            } else {
	                tempSet.add(message0.get(i));
	                tempSet.add(message1.get(i));
	                tempSet.add(message2.get(i));
	            }
	        }
	        //Construir mensaje final
	        Iterator<String> tempSetIterator = tempSet.iterator();
	        while (tempSetIterator.hasNext()) {
	            String word = tempSetIterator.next();
	            if (!StringUtils.isBlank(word)) {
	                messageBuilder.append(word);
	                if (tempSetIterator.hasNext()) {
	                    messageBuilder.append(" ");
	                }
	            }
	        }

	        return messageBuilder.toString();
	    }
	    
	    public Response topSecretSplitService(RequestPost request) throws ChallengeMeliException{
	       
	    	Response response = new Response(null, "");
	     if(request.getSatellite_name()!=null){
	        //Validar que en la  cola no se inserten satelites con el mismo nombre
	        //Validar que en nombre del satelite este contenido dentro de los que actualmente  estan en servicio
	        if (!queue.contains(request.getSatelite()) && salliteNames.contains(request.getSatellite_name().toUpperCase())) {
	            queue.add(request.getSatelite());
	             response.setMessage("Mensaje Procesado para el satelite : "+request.getSatellite_name());
	        }
	     }else {
	    	 response.setMessage("El Parametro Nombre del Satelite es obligatorio ");
	     }
	      
	      return response; 
	    }
	    
	    public Response topSeretService(Request request) throws ChallengeMeliException {
	        Response response = this.topSeretService(request.getSatelites());
	        //Si no hubo errores limpiar cola	       
	        return response;
	    }
}
