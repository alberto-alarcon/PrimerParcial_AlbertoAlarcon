/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;
import com.emergentes.productos.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ALBERTO
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        
        if(ses.getAttribute("listaper")==null){
            ArrayList<Productos> listaux = new ArrayList<Productos>();
            ses.setAttribute("listaper", listaux);
        }
      
         ArrayList<Productos> lista =(ArrayList<Productos>) ses.getAttribute("listaper");
         
         String op = request.getParameter("op");
         String opcion = (op!=null)? request.getParameter("op"):"view";
         
         Productos pro1 = new Productos();
         
         int id,pos;
         
         switch(opcion){
         
             case "nuevo":
                 request.setAttribute("miPersona", pro1);
                 request.getRequestDispatcher("editar.jsp").forward(request, response);
                 break;
             case "editar":
                 id = Integer.parseInt(request.getParameter("id"));
                 pos = buscarIndice(request,id);
                 pro1 = lista.get(pos);
                 request.setAttribute("miPersona", pro1);
                 request.getRequestDispatcher("editar.jsp").forward(request, response);
                 break;
                 
            case "eliminar":
                 pos = buscarIndice(request, Integer.parseInt(request.getParameter("id")));
                 lista.remove(pos);
                 ses.setAttribute("listaper", lista);
                 response.sendRedirect("index.jsp");
                 break;
                 
            case "view":
                 response.sendRedirect("index.jsp");
         }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
                               
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listaper");
        
        Productos pro1 = new Productos();
        
        pro1.setId(Integer.parseInt(request.getParameter("id")));
        pro1.setDescripcion(request.getParameter("descripcion"));
        pro1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        pro1.setPrecio(Integer.parseInt(request.getParameter("precio")));
        pro1.setCategoria(request.getParameter("categoria"));
        
        
        int idt = pro1.getId();
        if(idt == 0){
            int ultID;
            ultID = ultimoId(request);
            pro1.setId(ultID);
            lista.add(pro1);
            }
        else{
            lista.set(buscarIndice(request,idt),pro1);
            }
        
        ses.setAttribute("listaper",lista);
        response.sendRedirect("index.jsp");
    }
    
            private int ultimoId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listaper");
        int idaux =0;
        
        for(Productos item:lista){
            idaux =item.getId(); 
        }
        
        return idaux+1;
    }
    
    private int buscarIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listaper");
        int i=0;
        if(lista.size()>0){
            while(i<lista.size()){
                if(lista.get(i).getId()==id){
                    break;
                }
                else{
                    i++;
                }
            
            }
        }
        
    return i;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
