package com.tercerotest.controller.tda.list;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.lang.reflect.Method;
import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.controller.excepcion.ListEmptyException;

public class LinkedList<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;
    public static Integer Ascendente = 1;
    public static Integer Descendente = 0;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    public Boolean isEmpty() {
        return (this.header == null || this.size == 0);
    }

    private void addHeader(E dato) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(dato);
            header = help;
            this.last = help;
        } else {
            Node<E> healpHeader = this.header;
            help = new Node<>(dato, healpHeader);
            this.header = help;

            // this.size++;
        }
        this.size++;
    }

    private void addLast(E info) {
        Node<E> help;
        if (isEmpty()) {
            addHeader(info);
        } else {
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
            this.size++;
        }
    }

    public void add(E info) {

        addLast(info);
    }

    //----------------GET------------// 
    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header;
        } else if (index.intValue() == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    private E getFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        }
        return header.getInfo();
    }

    private E getLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacia");
        }
        return last.getInfo();
    }

    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header.getInfo();
        } else if (index.intValue() == (this.size - 1)) {
            return last.getInfo();
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }

    }
    //----------------End Get------------//

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index.intValue() == 0) {
            addHeader(info);
        } else if (index.intValue() == this.size.intValue()) {
            addLast(info);
        } else {

            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List Data \n");
        try {

            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" -> ");
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }

        return sb.toString();
    }

    public Integer getSize() {
        return this.size;
    }

    public E[] toArray() {
        E[] matrix = null;
        if (!isEmpty()) {
            Class clazz = header.getInfo().getClass();
            matrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Node<E> aux = header;
            for (int i = 0; i < this.size; i++) {
                matrix[i] = aux.getInfo();
                aux = aux.getNext();
            }
        }
        return matrix;
    }
    
    public Node<E> getHeader(){
        return header;
    }

    public LinkedList<E> toList(E[] matrix) {
        reset();
        for (int i = 0; i < matrix.length; i++) {
            this.add(matrix[i]);
        }
        return this;
    }

    public void update(E data, Integer post) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, la lista esta vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites de la lista");
        } else if (post == 0) {
            header.setInfo(data);
        } else if (post == (size - 1)) {
            last.setInfo(data);
        } else {
            
            Node<E> search = header;
            Integer cont = 0;
            while (cont < post) {
                cont++;
                search = search.getNext();
            }
            search.setInfo(data);
        }
    }

    public E deleteFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista esta vacia");
        } else {
            E element = header.getInfo();
            Node<E> aux = header.getNext();
            header = aux;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;
            return element;
        }
    }

    public E deleteLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista se encuentra vacia");
        } else {
            E element = last.getInfo();
            Node<E> aux = getNode(size - 2);
            if (aux == null) {
                last = null;
                if (size == 2) {
                    last = header;
                } else {
                    header = null;
                }
            } else {
                last = null;
                last = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }

    public int getLength() {
        return this.size.intValue();
    }

    public E delete(Integer post) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, la lista esta vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites de la lista");
        } else if (post == 0) {
            return deleteFirst();
        } else if (post == (size - 1)) {
            return deleteLast();
        } else {
            Node<E> preview = getNode(post - 1);
            Node<E> actually = getNode(post);
            E element = preview.getInfo();
            Node<E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            return element;
        }
    }

    public LinkedList<E> order(String atributo, Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                for (int i = 1; i < lista.length; i++) {
                    E aux = lista[i]; 
                    int j = i - 1; 
                    while (j >= 0 && atributoComp(atributo, lista[j], aux, type)) {
                        lista[j + 1] = lista[j--]; 
                    }
                    lista[j + 1] = aux; 
                }
                this.toList(lista);
            }
        }
        return this;
    }
    //-----------Compare--------------/
    private Boolean compare(Object a, Object b, Integer type) {
        boolean isTypeZero = type == 0;
    
        if (a instanceof Number) {
            Number a1 = (Number) a;
            Number b1 = (Number) b;
            return isTypeZero ? a1.doubleValue() > b1.doubleValue() : a1.doubleValue() < b1.doubleValue();
        } else {
            int comparison = a.toString().compareTo(b.toString());
            return isTypeZero ? comparison > 0 : comparison < 0;
        }
    }
    

    private Boolean atributoComp(String atributo, E a, E b, Integer type) throws Exception {
        return compare(atributoExis(a, atributo), atributoExis(b, atributo), type);
    }

    private Object atributoExis(E a, String atributo) throws Exception {
        if (atributo == null || atributo.isEmpty()) {
            throw new IllegalArgumentException("El atributo no puede estar vacío o nulo.");
        }
    
        String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
        Method method = null;
    
        for (Method aux : a.getClass().getMethods()) {
            if (aux.getName().equals(metodoNombre)) {
                method = aux;
                break;
            }
        }
    
        if (method == null) {
            for (Method aux : a.getClass().getSuperclass().getMethods()) {
                if (aux.getName().equals(metodoNombre)) {
                    method = aux;
                    break;
                }
            }
        }
    
        if (method != null) {
            return method.invoke(a);
        }
    
        throw new NoSuchMethodException("El método " + metodoNombre + " no se encontró en la clase " + a.getClass().getName());
    }
    

    //Metodo de ordenacion QuikShor

    public LinkedList<E> QuikShor(int orderType) throws Exception {
        if (!isEmpty()) {
            E[] lista = this.toArray();
            reset();
            QuikShorHp(lista, 0, lista.length - 1, orderType);
            this.toList(lista);
        }
        return this;
    }

    private void QuikShorHp(E[] lista, int bj, int alt, int orderType) throws Exception {
        if (bj < alt) {
            int PI = particion(lista, bj, alt, orderType);
            QuikShorHp(lista, bj, PI - 1, orderType);
            QuikShorHp(lista, PI + 1, alt, orderType);
        }
    }

    private int particion(E[] lista, int bj, int alt, int orderType) throws Exception {
        E pibote = lista[alt];
        int i = (bj - 1); // Indice del elemento más pequeño

        for (int j = bj; j < alt; j++) {
            if (compare(lista[j], pibote, orderType)) {
                i++;
                E aux = lista[i];
                lista[i] = lista[j];
                lista[j] = aux;
            }
        }

        E aux = lista[i + 1];
        lista[i + 1] = lista[alt];
        lista[alt] = aux;

        return i + 1;
    }

    public LinkedList<E> QuikShor(String atributo, Integer type) throws Exception {
        if (!isEmpty()) {
            E[] lista = this.toArray();
            reset();
            QuikShorHpAtb(lista, 0, lista.length - 1, atributo, type);
            this.toList(lista);
        }
        return this;
    }

    private void QuikShorHpAtb(E[] lista, int bj, int alt, String atributo, Integer type)
            throws Exception {
        if (bj < alt) {
            int PI = partAtb(lista, bj, alt, atributo, type);
            QuikShorHpAtb(lista, bj, PI - 1, atributo, type);
            QuikShorHpAtb(lista, PI + 1, alt, atributo, type);
        }
    }

    private int partAtb(E[] lista, int bj, int alt, String atributo, Integer type) throws Exception {
        E pibote = lista[alt];
        int i = (bj - 1); // Indice del elemento más pequeño

        for (int j = bj; j < alt; j++) {
            if (atributoComp(atributo, lista[j], pibote, type)) {
                i++;
                E aux = lista[i];
                lista[i] = lista[j];
                lista[j] = aux;
            }
        }

        E aux = lista[i + 1];
        lista[i + 1] = lista[alt];
        lista[alt] = aux;

        return i + 1;
    }

    // Metodo de ordenamiento mergeSort
    public LinkedList<E> mergesort(int orderType) throws Exception {
        if (!isEmpty()) {
            E[] lista = this.toArray();
            reset();
            lista = mergesortHp(lista, orderType);
            this.toList(lista);
        }
        return this;
    }

    private E[] mergesortHp(E[] lista, int orderType) throws Exception {
        if (lista.length <= 1) {
            return lista;
        }

        int aux = lista.length / 2;
        E[] izq = Arrays.copyOfRange(lista, 0, aux);
        E[] der = Arrays.copyOfRange(lista, aux, lista.length);

        izq = mergesortHp(izq, orderType);
        der = mergesortHp(der, orderType);

        return merge(izq, der, orderType);
    }

    private E[] merge(E[] izq, E[] der, int orderType) throws Exception {
        E[] result = (E[]) new Object[izq.length + der.length];
        int i = 0, j = 0, k = 0;

        while (i < izq.length && j < der.length) {
            if (compare(izq[i], der[j], orderType)) {
                result[k++] = izq[i++];
            } else {
                result[k++] = der[j++];
            }
        }

        while (i < izq.length) {
            result[k++] = izq[i++];
        }

        while (j < der.length) {
            result[k++] = der[j++];
        }

        return result;
    }

    public LinkedList<E> mergesort(String atributo, Integer type) throws Exception {
        if (!isEmpty()) {
            E[] lista = this.toArray();
            reset();
            lista = mergesortHpAtb(lista, atributo, type);
            this.toList(lista);
        }
        return this;
    }

    private E[] mergesortHpAtb(E[] lista, String atributo, Integer type) throws Exception {
        if (lista.length <= 1) {
            return lista;
        }

        int aux = lista.length / 2;
        E[] izq = Arrays.copyOfRange(lista, 0, aux);
        E[] der = Arrays.copyOfRange(lista, aux, lista.length);

        izq = mergesortHpAtb(izq, atributo, type);
        der = mergesortHpAtb(der, atributo, type);

        return mergeAtb(izq, der, atributo, type);
    }

    private E[] mergeAtb(E[] izq, E[] der, String atributo, Integer type) throws Exception {
        E[] result = (E[]) new Object[izq.length + der.length];
        int i = 0, j = 0, k = 0;

        while (i < izq.length && j < der.length) {
            if (atributoComp(atributo, izq[i], der[j], type)) {
                result[k++] = izq[i++];
            } else {
                result[k++] = der[j++];
            }
        }

        while (i < izq.length) {
            result[k++] = izq[i++];
        }

        while (j < der.length) {
            result[k++] = der[j++];
        }

        return result;
    }

    // Metodo de ordenaamiento shell sort
    public LinkedList<E> shellSort(int orderType) throws Exception {
        if (!isEmpty()) {
            E[] lista = this.toArray();
            reset();
            int n = lista.length;
            for (int vid = n / 2; vid > 0; vid /= 2) {
                for (int i = vid; i < n; i++) {
                    E aux = lista[i];
                    int j;
                    for (j = i; j >= vid && compare(lista[j - vid], aux, orderType); j -= vid) {
                        lista[j] = lista[j - vid];
                    }
                    lista[j] = aux;
                }
            }
            this.toList(lista);
        }
        return this;
    }

    public LinkedList<E> shellSort(String atributo, Integer type) throws Exception {
        if (!isEmpty()) {
            E[] lista = this.toArray(); // Convertir la lista enlazada a un array
            int n = lista.length;

            // Inicialización del intervalo de Shell Sort
            for (int vid = n / 2; vid > 0; vid /= 2) {
                for (int i = vid; i < n; i++) {
                    E aux = lista[i];
                    int j;

                    // Comparar elementos en el intervalo
                    for (j = i; j >= vid && atributoComp(atributo, lista[j - vid], aux, type); j -= vid) {
                        lista[j] = lista[j - vid];
                    }
                    lista[j] = aux;
                }
            }

            this.toList(lista); // Convertir el array de vuelta a lista enlazada
        }
        return this;
    }

}
