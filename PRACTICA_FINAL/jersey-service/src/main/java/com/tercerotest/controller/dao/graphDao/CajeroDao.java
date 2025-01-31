package com.tercerotest.controller.dao.graphDao;

import com.tercerotest.controller.dao.implement.AdapterDao;
import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Cajero;


public class CajeroDao extends AdapterDao<Cajero> {
    private Cajero cajero = new Cajero();
    private LinkedList listAll;

    public CajeroDao() {
        super(Cajero.class);
    }

    public Cajero getCajero() { // Obtiene el cajero
        if (cajero == null) {
            cajero = new Cajero(); // En caso de que el cajero sea nula, crea una nueva instancia de cajero
        }
        return this.cajero; // Devuelve el cajero
    }

    public void setCajero(Cajero cajero) { // Establece el cajero con un objeto cajero
        this.cajero = cajero; // Asigna el objeto cajero a la variable cajero
    }

    public LinkedList getListAll() { // Obtiene la lista de objetos
        if (listAll == null) { // Si la lista es nula
            this.listAll = listAll(); // Invoca el método listAll() para obtener la lista de objetos
        }
        return listAll; // Devuelve la lista de objetos de la variable listAll
    }

    public Boolean save() throws Exception { // Guarda la variable cajero en la lista de objetos
        Integer id = getListAll().getSize() + 1; // Obtiene el tamaño de la lista y le suma 1 para asignar un nuevo id
        cajero.setId(id); // Asigna el id a cajero
        this.persist(this.cajero); // Guarda el cajero en la lista de objetos LinkedList y en el archivo JSON
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se guardó correctamente
    }

    public Boolean update() throws Exception { // Actualiza el nodo cajero en la lista de objetos
        this.merge(getCajero(), getCajero().getId() - 1); // Envia el cajero a actualizar con su index
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true;
    }

    public Boolean delete(int index) throws Exception { // Elimina un objeto cajero por su índice
        this.supreme(index);
        this.listAll = listAll(); // Actualiza la lista de objetos
        return true; // Retorna verdadero si se eliminó correctamente
    }  

}
