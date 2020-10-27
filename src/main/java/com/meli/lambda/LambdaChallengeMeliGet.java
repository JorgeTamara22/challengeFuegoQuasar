package com.meli.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.meli.exception.ChallengeMeliException;
import com.meli.objetos.Position;
import com.meli.objetos.Request;
import com.meli.objetos.Response;
import com.meli.servicios.TopSecretService;
import static com.meli.constantes.Constants.NOT_ENOUGH_INFORMATION;

public class LambdaChallengeMeliGet implements RequestHandler<Request, Response> {

    @Override
    public Response handleRequest(Request request, Context context) {
       
    	TopSecretService topSecret = new TopSecretService();
    	 try {
        return topSecret.topSeretService(request);
    	 } catch (ChallengeMeliException exception) {
    		 return new Response(new Position(0.0,0.0), NOT_ENOUGH_INFORMATION);          		
         }
       
    }

}
