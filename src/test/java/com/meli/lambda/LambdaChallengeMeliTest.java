package com.meli.lambda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.meli.objetos.Request;
import com.meli.objetos.Response;
import com.meli.objetos.Satelite;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaChallengeMeliTest {

    private static Request input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
    	input = new Request();
    	List<Satelite> satelite = new ArrayList<Satelite>();
		satelite.add(new Satelite("kenobi", (float)100.0, Arrays.asList( new String[] { "este", "", "", "mensaje", "" })));
		satelite.add(new Satelite("skywalker", (float)115.5, Arrays.asList(new String[] { "", "", "", "", "secreto" })));
		satelite.add(new Satelite("sato", (float)142.7, Arrays.asList(new String[] { "este", "", "un", "", ""})));
		
        input.setSatelites(satelite); 
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaChallengeMeli() {
        LambdaChallengeMeli handler = new LambdaChallengeMeli();
        Context ctx = createContext();

        Response output = handler.handleRequest(input, ctx);
        
          System.out.println("mensaje de Salida "+output.getMessage());
        
        // TODO: validate output here if needed.
        //Assert.assertEquals("Hello from Lambda!", output);
    }
   
}
