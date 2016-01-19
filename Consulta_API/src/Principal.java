import java.util.*;


public class Principal 
{
	//Etiquetas a buscar en el parseo
		public static String NAME_DATASET = "h3.dataset-heading >a";
		public static String DATASET_LICENSE ="a[rel=dc:rights]";
		public static String NUM_TOTAL_PAGES ="li[page=]";
		

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Obtener_Datos_Usuario odu =new Obtener_Datos_Usuario();
		Obtener_Manipular_Datos_API omdapi = new Obtener_Manipular_Datos_API();
		Conectar_con_MongoDB cmongo=new Conectar_con_MongoDB();
		Hashtable<String, String> ht = new Hashtable<String, String>();
		String url_a_consultar="",url_entrada="",licencia="",url_licencia="";
		ArrayList<String> lista_nombres = new ArrayList<String>();
		ArrayList<String> lista_url_consulta_licencia= new ArrayList<String>();
		ArrayList<String> lista_licencias = new ArrayList<String>();
			
		
		ht=odu.pedirDatos();
		odu.setPalabra_clave_busqueda(ht.get("P_CLAVE"));
		odu.setPath_Entrada(ht.get("PATH_ENTRADA"));
		
		url_entrada =odu.getPath_Entrada();
		//System.out.println("\nPRINCIPAL PATHA: "+odu.getPath_Entrada()+"\nPRINCIPAL PALABRA CLAVE: "+odu.getPalabra_clave_busqueda());
		url_a_consultar=omdapi.transformarURLaConsultar(url_entrada, odu.getPalabra_clave_busqueda(), 1);
		
		//System.out.println("URL A CONSULTAR: "+url_a_consultar);
		omdapi.sendGet(url_a_consultar);
		lista_nombres=omdapi.parsearParaObtenerDatos(url_a_consultar,NAME_DATASET,1);
		/*for(int i=0;i<lista_nombres.size();i++)
		{
			System.out.println("\nIZENAK:"+lista_nombres.get(i));
		}*/
		lista_url_consulta_licencia=omdapi.parsearParaObtenerDatos(url_a_consultar,NAME_DATASET,2);
				
		for(int i=0; i<lista_url_consulta_licencia.size();i++)
		{ 
			url_licencia= (String) lista_url_consulta_licencia.get(i);
			url_a_consultar=omdapi.transformarURLaConsultar(url_entrada,url_licencia, 2);
			licencia=omdapi.devolverTexto(url_a_consultar,DATASET_LICENSE);
			lista_licencias.add(licencia);
		}
		
		/*for(int i=0;i<lista_nombres.size();i++)
		{
			System.out.println("\nLIZENTZIAK:"+lista_licencias.get(i));
		}*/
		for(int i=0; i<lista_nombres.size();i++)
		{
		    //System.out.println("SARTU BEHARREKOA: "+lista_nombres.get(i));
			 //System.out.println("SARTU BEHARREKOA2: "+lista_licencias.get(i));
			cmongo.insertarDatos(cmongo.getMongo(), lista_nombres.get(i),lista_licencias.get(i));

			//System.out.println("DATUAK SARTUTA!!");
		}
		System.out.println("PROGRAMA BUKAERA!!");
	}
	
	

}
