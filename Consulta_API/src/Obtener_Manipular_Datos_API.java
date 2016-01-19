import java.io.*;
import java.net.*;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Obtener_Manipular_Datos_API
{
	private final String USER_AGENT = "Mozilla/5.0";	
	
	// constructor
	public Obtener_Manipular_Datos_API()
	{
		
	}
	public String transformarURLaConsultar (String url_entrada,String clave_sust, int type)
	{
		String dt ="/dataset",strQuery="",strAux="",strVuelta="";
		int posInicio=0, posFinal=0;
		if(type == 1) // cambiar URL y ponerle el query ?q=water
		{
			strQuery=dt.concat("?q="+clave_sust);
			posInicio=url_entrada.indexOf(dt);
			posFinal=url_entrada.length();
			
			strAux=url_entrada;
			strVuelta=strAux.replaceAll(strAux.substring(posInicio, posFinal),strQuery);
		}
		else //cambiar URL path busqueda de licencias
		{
			posInicio=url_entrada.indexOf(dt);
			posFinal=url_entrada.length();
			
			strAux=url_entrada;
			strVuelta=strAux.replaceAll(strAux.substring(posInicio, posFinal),clave_sust);
		}
		return strVuelta;
	}
	
	// HTTP GET request
	public void sendGet(String url_a_consultar) 
	{
		URL obj =null;
		HttpURLConnection con = null;
		BufferedReader in=null;
		String inputLine ="";
		StringBuffer response =null;
		
		try
		{
		  obj = new URL(url_a_consultar);
		  con = (HttpURLConnection) obj.openConnection();

		  // optional default is GET
		  con.setRequestMethod("GET");

		  //add request header
		  con.setRequestProperty("User-Agent", USER_AGENT);

		 // int responseCode=con.getResponseCode();
		//  System.out.println("\nSending 'GET' request to URL : " + url_a_consultar);
		//  System.out.println("Response Code : " + responseCode);

		  in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		  response = new StringBuffer();

		  while ((inputLine = in.readLine()) != null) {
			  response.append(inputLine);
		  }
		  in.close();
		 
		//print result
		System.out.println(response.toString());
		}
		catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	}
	
	public String devolverTexto(String url_transformado, String patron)
	{
		Document doc =null;
		String linkTextVuelta ="";
		try
		{
			doc = Jsoup.connect(url_transformado).get();
			Elements links = doc.select(patron);
			for (Element link : links) 
			 {
			   linkTextVuelta = link.text();
			 //System.out.println("\nLicencia:"+linkTextVuelta);
			 }
		}
		catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		return linkTextVuelta;
       
	}
	
	public ArrayList<String> parsearParaObtenerDatos(String url_transformado,String patron,int type)
	{
	    ArrayList<String> lista = new ArrayList<String>();
		Document doc =null;
	  try
	  {
		
		if(type==1) // obtenemos lista de nombre_dataset
		{
		 doc = Jsoup.connect(url_transformado).get();
		 Elements links = doc.select(patron);
				
		 for (Element link : links) 
		 {
		    String linkText = link.text();			  	    
		
		    lista.add(linkText);
			//System.out.println("\nNombre:"+linkText);
		 } 
		}
		else if(type==2)// obtenemos urls para rebisar licencias
		{
			doc = Jsoup.connect(url_transformado).get();
			Elements links = doc.select(patron);
					
			 for (Element link : links) 
			 {
			    String linkHref = link.attr("href");
			   //.out.println("\nURL:"+linkHref);
			    lista.add(linkHref);
		     }	
		}
		else
		{			 
			
		}
			
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
			
		return lista;
		
	}
		
	
}
