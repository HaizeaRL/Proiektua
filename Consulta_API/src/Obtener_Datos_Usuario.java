import java.util.*;

public class Obtener_Datos_Usuario 
{
   private String path_entrada="";
   private String palabra_clave_busqueda ="";
   
   //constructores
   public Obtener_Datos_Usuario()
   {
	   
   }
   public Obtener_Datos_Usuario (String path_entrada,String palabra_clave_busqueda)
   {
	   this.path_entrada = path_entrada;
	   this.palabra_clave_busqueda=palabra_clave_busqueda;
   }
   
   public void setPath_Entrada(String path_entrada)
   {
	   this.path_entrada=path_entrada;
   }
   
   public String getPath_Entrada()
   {
	   return this.path_entrada;
   }
   
   public void setPalabra_clave_busqueda(String palabra_clave_busqueda)
   {
	   this.palabra_clave_busqueda=palabra_clave_busqueda;
   }
   
   public String getPalabra_clave_busqueda()
   {
	   return this.palabra_clave_busqueda;
   }
   
   public Hashtable<String,String> pedirDatos()
   {
	  Hashtable<String, String> ht = new Hashtable<String, String>();
	  String p_clave="",path_entrada="";
	  Scanner entradaEscaner =null;
	  try	 
	  {
		  System.out.println ("Por favor copia la URL: (Ejemp: http://example.com/path/to/api/ ) ");
		  entradaEscaner = new Scanner (System.in); 
		  path_entrada = entradaEscaner.nextLine (); 
		  System.out.println ("Por favor introduzca la palabra clave a buscar por teclado:");
		  p_clave = entradaEscaner.nextLine (); 
		  
		  ht.put("PATH_ENTRADA", path_entrada);
		  ht.put("P_CLAVE", p_clave.toLowerCase());
		  
		//  System.out.println("PATHA: "+ht.get("PATH_ENTRADA")+"\nPALABRA CLAVE: "+ht.get("P_CLAVE"));
		 
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  finally {
		    if(entradaEscaner!=null)
		    	entradaEscaner.close();
		}
	return ht;	  
   }
}         
