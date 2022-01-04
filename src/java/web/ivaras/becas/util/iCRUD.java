/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.util;

import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface iCRUD<Objeto> {
    public ArrayList<Objeto> listarTodo();
    public int agregar(Objeto o);
    public Objeto buscarPorId(int id);
    public int modificar(Objeto o);
    public int eliminar(Objeto o);
}
