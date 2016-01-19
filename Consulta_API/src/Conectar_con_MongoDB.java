import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.*;

public class Conectar_con_MongoDB 
{
	
	private MongoClient mongo =null;
	  /**
     * Clase para crear una conexión a MongoDB.
     * @return MongoClient conexión
     */
	
	public Conectar_con_MongoDB()
	{
		this.mongo =crearConexion();
	}
    private static MongoClient crearConexion() 
    {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return mongo;
    }
     
    /**
     */
    public void insertarDatos(MongoClient mongo,String nombre, String licencia)
    {
    	
    	
     if (mongo != null) 
     {
        //Si no existe la base de datos la crea
        MongoDatabase db = mongo.getDatabase("bd-ejemplo");

        //Crea una tabla si no existe y agrega datos
        MongoCollection<Document> table = db.getCollection("datasets");
        
      //Insertamos datos
        org.bson.Document document1 = new Document();
        document1.put("Nombre-Dataset", nombre);
        document1.put("Licencia", licencia);
        
        //Insertar tabla
        table.insertOne(document1);
     }
     else 
     {
        System.out.println("Error: Conexión no establecida");
     }
    }
    
    public MongoClient getMongo()
    {
    	return this.mongo;
    }
    
    public void setMongo(MongoClient mongo)
    {
    	this.mongo=mongo;
    }
    
    
  
}

