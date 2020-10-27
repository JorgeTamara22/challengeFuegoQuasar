package com.meli.lambda;

import static com.meli.constantes.Constants.NOT_ENOUGH_INFORMATION;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.meli.exception.ChallengeMeliException;
import com.meli.objetos.Position;
import com.meli.objetos.Request;
import com.meli.objetos.RequestPost;
import com.meli.objetos.Response;
import com.meli.servicios.TopSecretService;


public class LambdaChallengeMeliPost implements RequestHandler<RequestPost, Response> {

    @Override
    public Response handleRequest(RequestPost request, Context context) {
      
    try{
    	TopSecretService topSecret = new TopSecretService();    	
     	
        return topSecret.topSecretSplitService(request);        
        
    } catch (ChallengeMeliException exception) {	
		
		return new Response(new Position(0.0,0.0), NOT_ENOUGH_INFORMATION);
	}
    }
}
