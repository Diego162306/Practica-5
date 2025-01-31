package com.tercerotest.controller.dao.services;

import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Cajero;
import com.tercerotest.controller.dao.graphDao.CajeroDao;

public class CajeroServicies {
    private CajeroDao obj;
    
    public CajeroServicies(){
        obj = new CajeroDao(); //Instancia un objeto de la clase cajeroDao
    }
    
    public Boolean update() throws Exception{ //Guarda la variable cajero en la lista de objetos
        return obj.update(); //Invoca el método save() de la clase cajeroDao
    }

    public Boolean save() throws Exception{ //Guarda la variable cajero en la lista de objetos
        return obj.save(); //Invoca el método save() de la clase cajeroDao
    }

    public LinkedList listAll(){ //Obtiene la lista de objetos LinkedList<T>
        return obj.getListAll(); //Invoca el método getlistAll() de la clase cajeroDao

    }

    public Cajero getcajero(){ //Obtiene la cajero
        return obj.getCajero(); //Invoca el método getcajero() de la clase cajeroDao
    }

    public void setcajero( Cajero cajero){ //Recibe un objeto cajero
        obj.setCajero(cajero); //Invoca el método setcajero() de la clase cajeroDao y envía el objeto cajero
    }

    public Cajero get(Integer id) throws Exception{ //Obtiene un objeto cajero por su id
        return obj.get(id); //Invoca el método get() de la clase cajeroDao y envía el id
    }

    public Boolean delete(int index) throws Exception{ //Elimina un objeto cajero por su índice
        return obj.delete(index); //Invoca el método delete() de la clase cajeroDao y envía el índice
    }
}
